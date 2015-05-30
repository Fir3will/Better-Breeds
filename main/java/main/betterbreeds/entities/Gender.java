package main.betterbreeds.entities;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.Config;
import main.betterbreeds.api.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;

public class Gender
{
	public static boolean canMate(Genderized a, Genderized b)
	{
		if (!isAnimal(a) || !isAnimal(b) || a.isPregnant() || b.isPregnant()) return false;
		final EntityAnimal an = (EntityAnimal) a;
		final EntityAnimal bn = (EntityAnimal) b;
		final boolean inLove = an.isInLove() && bn.isInLove();
		final boolean sameSpecies = a.getClass() == b.getClass();
		boolean differentGender = Config.differentGender && a.isFemale() != b.isFemale();
		boolean goodHealth = Config.goodHealth && an.getHealth() == an.getMaxHealth() && bn.getHealth() == bn.getMaxHealth();
		differentGender = Config.differentGender ? differentGender : true;
		goodHealth = Config.goodHealth ? goodHealth : true;
		return inLove && sameSpecies && a != b && differentGender && goodHealth;
	}

	public static boolean isAnimal(Object o)
	{
		return o instanceof EntityAnimal;
	}

	public static void setFemale(EntityAnimal entity, boolean female)
	{
		entity.getDataWatcher().updateObject(25, female ? 0 : 1);
	}

	public static boolean isFemale(EntityAnimal entity)
	{
		return entity.getDataWatcher().getWatchableObjectInt(25) == 0;
	}

	public static void setPregnant(EntityAnimal entity, boolean pregnant)
	{
		if (((Genderized) entity).isFemale())
		{
			entity.getDataWatcher().updateObject(27, pregnant ? 0 : 1);
		}
	}

	public static boolean isPregnant(EntityAnimal entity)
	{
		return ((Genderized) entity).isFemale() ? entity.getDataWatcher().getWatchableObjectInt(27) == 0 : false;
	}

	public static void setType(EntityAnimal entity, int type)
	{
		entity.getDataWatcher().updateObject(26, type);
	}

	public static int getType(EntityAnimal entity)
	{
		return entity.getDataWatcher().getWatchableObjectInt(26);
	}

	public static void setObjects(EntityAnimal entity)
	{
		entity.getDataWatcher().addObject(25, 0);
		entity.getDataWatcher().addObject(26, 0);
		entity.getDataWatcher().addObject(27, 0);
	}

	public static int getMixBetween(int maxTextures, Genderized a, Genderized b)
	{
		final int aT = a.getType();
		final int bT = b.getType();
		return Rand.nextInt(3) == 0 ? Rand.nextInt(maxTextures) : Rand.isPercent(Config.differentTextureChance) ? aT : bT;
	}

	public static ResourceLocation getLocation(String mobName, Genderized e)
	{
		return new ResourceLocation(BetterBreeds.MODID + ":textures/entities/" + mobName + "/" + mobName + "_" + e.getType() + ".png");
	}
}
