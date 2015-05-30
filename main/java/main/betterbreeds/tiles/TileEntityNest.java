package main.betterbreeds.tiles;

import main.betterbreeds.Config;
import main.betterbreeds.entities.EntityBChicken;
import main.com.hk.bb.util.MPUtil;
import main.com.hk.bb.util.StackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;

public class TileEntityNest extends TileEntity implements IInventory
{
	public ItemStack[] eggs = new ItemStack[4];
	public EntityBChicken chicken;

	public void heatEggs()
	{
		for (int i = 0; i < 4; i++)
		{
			if (makeSure(i))
			{
				eggs[i].stackTagCompound.setInteger("Hatching Time", eggs[i].stackTagCompound.getInteger("Hatching Time") + 1);
				if (eggs[i].stackTagCompound.getInteger("Hatching Time") >= Config.eggHatchingTime)
				{
					eggs[i] = null;
					EntityBChicken child = new EntityBChicken(worldObj);
					child.setGrowingAge(-24000);
					child.setLocationAndAngles(xCoord + 0.5D, yCoord, zCoord + 0.5D, 0.0F, 0.0F);
					if (MPUtil.isServerSide())
					{
						worldObj.spawnEntityInWorld(child);
					}
				}
			}
		}
	}

	public boolean isValidOwner(EntityBChicken chicken)
	{
		return this.chicken == null || this.chicken.equals(chicken);
	}

	public boolean makeSure(int slot)
	{
		if (eggs[slot] != null)
		{
			StackHelper.newNBT(eggs[slot]);
			return eggs[slot].getItem() == Items.egg && eggs[slot].stackTagCompound.getInteger("Fertility") == 1;
		}
		return false;
	}

	public void broke()
	{
		if (chicken != null && chicken.isIncubating)
		{
			chicken.attackEntityFrom(DamageSource.outOfWorld, 1.5F);
		}
	}

	@Override
	public int getSizeInventory()
	{
		return eggs.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return eggs[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		if (eggs[slot] != null)
		{
			ItemStack itemstack;

			if (eggs[slot].stackSize <= amount)
			{
				itemstack = eggs[slot];
				eggs[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = eggs[slot].splitStack(amount);

				if (eggs[slot].stackSize == 0)
				{
					eggs[slot] = null;
				}

				return itemstack;
			}
		}
		else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (eggs[slot] != null)
		{
			final ItemStack itemstack = eggs[slot];
			eggs[slot] = null;
			return itemstack;
		}
		else return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		eggs[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return "Chicken Nest";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory()
	{}

	@Override
	public void closeInventory()
	{}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return stack != null && stack.getItem() == Items.egg;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		final NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		eggs = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			final NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			final byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 >= 0 && b0 < eggs.length)
			{
				eggs[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		final NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < eggs.length; ++i)
		{
			final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			if (eggs[i] != null)
			{
				nbttagcompound1.setByte("Slot", (byte) i);
				eggs[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}
}
