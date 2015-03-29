package main.betterbreeds.entities;

import main.betterbreeds.entities.Gender.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBWolf extends EntityWolf implements Genderized
{
	public static final int MAX_TEXTURES = 6;

	public EntityBWolf(World world)
	{
		super(world);
		setFemale(Rand.nextBoolean());
		setType(Rand.nextInt(MAX_TEXTURES));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		Gender.setObjects(this);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Gender", isFemale());
		nbt.setInteger("Type", getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setFemale(nbt.getBoolean("Gender"));
		setType(nbt.getInteger("Type"));
	}

	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() + (isFemale() ? -0.2F : 0.2F);
	}

	@Override
	public boolean canMateWith(EntityAnimal entity)
	{
		return entity instanceof Genderized ? Gender.canMate(this, (Genderized) entity) : false;
	}

	@Override
	public boolean isFemale()
	{
		return Gender.isFemale(this);
	}

	@Override
	public void setFemale(boolean isFemale)
	{
		Gender.setFemale(this, isFemale);
	}

	@Override
	public int getType()
	{
		return Gender.getType(this);
	}

	@Override
	public void setType(int type)
	{
		Gender.setType(this, type);
	}

	@Override
	public EntityBWolf createChild(EntityAgeable a)
	{
		final EntityBWolf bwolf = new EntityBWolf(worldObj);
		bwolf.setType(Gender.getMixBetween(MAX_TEXTURES, this, (Genderized) a));
		return bwolf;
	}
}