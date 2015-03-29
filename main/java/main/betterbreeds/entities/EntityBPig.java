package main.betterbreeds.entities;

import main.betterbreeds.entities.Gender.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBPig extends EntityPig implements Genderized
{
	public static final int MAX_TEXTURES = 10;

	public EntityBPig(World world)
	{
		super(world);
		setFemale(Rand.nextBoolean());
		setType(Rand.nextInt(EntityBPig.MAX_TEXTURES));
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
	public EntityPig createChild(EntityAgeable a)
	{
		final EntityBPig p = new EntityBPig(worldObj);
		p.setType(Gender.getMixBetween(MAX_TEXTURES, this, (Genderized) a));
		return p;
	}
}