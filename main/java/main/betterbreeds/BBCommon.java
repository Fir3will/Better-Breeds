package main.betterbreeds;

import java.awt.Color;
import main.betterbreeds.misc.SpawnDetail;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import cpw.mods.fml.common.registry.EntityRegistry;

public class BBCommon
{
	public void registerRenderThings()
	{}

	public void registerSounds()
	{}

	public static void registerThrowable(Class<? extends Entity> entityClass, String mobName)
	{
		EntityRegistry.registerModEntity(entityClass, mobName, nextID(), BetterBreeds.instance, 80, 3, true);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName)
	{
		final int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id);
		EntityRegistry.registerModEntity(entityClass, mobName, id, BetterBreeds.instance, 80, 3, true);
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, Color foreground, Color background, SpawnDetail... spawns)
	{
		final int id = nextID();
		EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, foreground.getRGB(), background.getRGB());
		EntityRegistry.registerModEntity(entityClass, mobName, id, BetterBreeds.instance, 80, 3, true);
		if (spawns != null && spawns.length > 0)
		{
			for (final SpawnDetail spawn : spawns)
			{
				if (spawn != null)
				{
					EntityRegistry.addSpawn(entityClass, spawn.chanceToSpawn, spawn.minAmountToSpawn, spawn.maxAmountToSpawn, creature, spawn.biome);
				}
			}
		}
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, String mobName, EnumCreatureType creature, SpawnDetail... spawns)
	{
		final Color color = new Color(Rand.nextInt(256), Rand.nextInt(256), Rand.nextInt(256));
		switch (creature)
		{
			case ambient:
				BBCommon.registerEntity(entityClass, mobName, creature, color, color, spawns);
				break;
			case creature:
				BBCommon.registerEntity(entityClass, mobName, creature, Color.WHITE, Color.BLACK, spawns);
				break;
			case monster:
				BBCommon.registerEntity(entityClass, mobName, creature, Color.BLACK, Color.WHITE, spawns);
				break;
			case waterCreature:
				BBCommon.registerEntity(entityClass, mobName, creature, Color.BLUE.brighter(), Color.BLUE.darker(), spawns);
				break;
		}
	}

	private static int nextID()
	{
		final int i = entityID++;
		if (EntityList.getStringFromID(i) != null) return nextID();
		return i;
	}

	private static int entityID = 1;
}
