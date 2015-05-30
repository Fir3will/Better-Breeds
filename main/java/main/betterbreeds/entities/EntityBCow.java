package main.betterbreeds.entities;

import main.betterbreeds.api.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBCow extends EntityCow implements Genderized
{
	public static final int MAX_TEXTURES = 10;

	public EntityBCow(World world)
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
	public boolean isPregnant()
	{
		return Gender.isPregnant(this);
	}

	@Override
	public void setPregnant(boolean pregnant)
	{
		Gender.setPregnant(this, pregnant);
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		final ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == Items.bucket && !p_70085_1_.capabilities.isCreativeMode)
		{
			if (!isFemale()) if (itemstack.stackSize-- == 1)
			{
				p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(Items.milk_bucket));
			}
			else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
			{
				p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
			}

			return true;
		}
		else return super.interact(p_70085_1_);
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
	public EntityBCow createChild(EntityAgeable a)
	{
		final EntityBCow p = new EntityBCow(worldObj);
		p.setType(Gender.getMixBetween(10, this, (EntityBCow) a));
		return p;
	}
}