package main.betterbreeds.entities;

import main.betterbreeds.animations.AnimationHandlerChicken;
import main.betterbreeds.entities.Gender.Genderized;
import main.com.hk.bb.util.Rand;
import mca.library.common.IMCAnimatedEntity;
import mca.library.common.animation.AnimationHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBChicken extends EntityChicken implements Genderized, IMCAnimatedEntity
{
	public final AnimationHandlerChicken animHandler = new AnimationHandlerChicken(this);
	public static final int MAX_TEXTURES = 10;

	public EntityBChicken(World world)
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
	public EntityBChicken createChild(EntityAgeable a)
	{
		final EntityBChicken p = new EntityBChicken(worldObj);
		p.setType(Gender.getMixBetween(10, this, (Genderized) a));
		return p;
	}

	@Override
	public AnimationHandler getAnimationHandler()
	{
		return animHandler;
	}

	@Override
	public boolean isTile()
	{
		return false;
	}

	@Override
	public boolean isEntity()
	{
		return true;
	}
}