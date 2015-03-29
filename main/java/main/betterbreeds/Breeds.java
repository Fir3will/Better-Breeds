package main.betterbreeds;

import java.util.Map;
import main.com.hk.bb.util.JavaHelp;
import main.com.hk.bb.util.MPUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Breeds
{
	private static Map<Class<? extends EntityCreature>, Class<? extends EntityCreature>> mobToSwitch;
	private static Map<Class<? extends EntityCreature>, Boolean> switchIsAllowed;
	private static Map<Class<? extends EntityCreature>, Class<? extends EntityCreature>> switchToMob;

	static
	{
		mobToSwitch = JavaHelp.newHashMap();
		switchToMob = JavaHelp.newHashMap();
		switchIsAllowed = JavaHelp.newHashMap();
		final Breeds b = new Breeds();
		MinecraftForge.EVENT_BUS.register(b);
		MinecraftForge.ORE_GEN_BUS.register(b);
		MinecraftForge.TERRAIN_GEN_BUS.register(b);
		FMLCommonHandler.instance().bus().register(b);
	}

	public static void addSwitcher(Class<? extends EntityCreature> entityClass, Class<? extends EntityCreature> switchClass, String name)
	{
		switchToMob.put(switchClass, entityClass);
		mobToSwitch.put(entityClass, switchClass);
		BBCommon.registerEntity(switchClass, "other." + name.toLowerCase());
		final Property p = BetterBreeds.cfg.c.get("mobs", "switch" + name, true);
		p.comment = "Enable the mods " + name.toLowerCase() + " instead of the Vanilla " + name.toLowerCase() + ". If set to false, the Vanilla " + name.toLowerCase() + " will not be switched.";
		switchIsAllowed.put(switchClass, p.getBoolean(true));
	}

	private Breeds()
	{}

	private void doStuff(EntityCreature entity)
	{
		if (mobToSwitch.containsKey(entity.getClass())) try
		{
			final Class<? extends EntityCreature> c = mobToSwitch.get(entity.getClass());
			final EntityCreature switched = c.getConstructor(World.class).newInstance(entity.worldObj);
			if (switched != null && switchIsAllowed.get(c)) switchMobs(entity, switched);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityCreature) doStuff((EntityCreature) event.entity);
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityCreature) doStuff((EntityCreature) event.entity);
	}

	private void switchMobs(EntityCreature entity, EntityCreature entity2)
	{
		entity2.copyLocationAndAnglesFrom(entity);
		if (MPUtil.isServerSide()) entity.worldObj.spawnEntityInWorld(entity2);
		entity.setDead();
		entity2.onSpawnWithEgg(null);
	}
}
