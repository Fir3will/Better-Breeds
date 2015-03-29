package main.betterbreeds.entities;

import main.betterbreeds.entities.Gender.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBHorse extends EntityHorse implements Genderized
{
	public static final int MAX_TEXTURES = 10;

	public EntityBHorse(World world)
	{
		super(world);
		setFemale(Rand.nextBoolean());
		setType(Rand.nextInt(MAX_TEXTURES));
		setCustomNameTag("Horse");
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
	public void setCustomNameTag(String tag)
	{
		super.setCustomNameTag("[" + (isFemale() ? "Female" : "Male") + "] " + tag);
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

	public IAttributeInstance getJumpStrengthAttribute()
	{
		return getAttributeMap().getAttributeInstanceByName("horse.jumpStrength");
	}

	@Override
	public EntityBHorse createChild(EntityAgeable a)
	{
		final EntityHorse horse = (EntityHorse) super.createChild(a);
		final EntityBHorse p = new EntityBHorse(worldObj);
		p.setHorseVariant(horse.getHorseVariant());
		p.setHorseType(horse.getHorseType());
		p.setType(Gender.getMixBetween(MAX_TEXTURES, this, (EntityBHorse) a));
		p.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(horse.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
		p.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(horse.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
		p.getJumpStrengthAttribute().setBaseValue(horse.getHorseJumpStrength());
		return p;
	}
}