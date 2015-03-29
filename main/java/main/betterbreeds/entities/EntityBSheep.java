package main.betterbreeds.entities;

import main.betterbreeds.entities.Gender.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBSheep extends EntitySheep implements Genderized
{
	public static final int MAX_TEXTURES = 1;
	private final InventoryCrafting crafter = new InventoryCrafting(new Container()
	{
		@Override
		public boolean canInteractWith(EntityPlayer p_75145_1_)
		{
			return false;
		}
	}, 2, 1);

	public EntityBSheep(World world)
	{
		super(world);
		setFemale(Rand.nextBoolean());
		setType(0);
		crafter.setInventorySlotContents(0, new ItemStack(Items.dye, 1, 0));
		crafter.setInventorySlotContents(1, new ItemStack(Items.dye, 1, 0));
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
	public EntitySheep createChild(EntityAgeable entity)
	{
		final EntityBSheep entitysheep = (EntityBSheep) entity;
		final EntityBSheep entitysheep1 = new EntityBSheep(worldObj);
		final int i = createMixedColor(this, entitysheep);
		entitysheep1.setFleeceColor(15 - i);
		return entitysheep1;
	}

	private int createMixedColor(EntityAnimal a, EntityAnimal b)
	{
		final int i = getColor(a);
		final int j = getColor(b);
		crafter.getStackInSlot(0).setItemDamage(i);
		crafter.getStackInSlot(1).setItemDamage(j);
		final ItemStack itemstack = CraftingManager.getInstance().findMatchingRecipe(crafter, ((EntityBSheep) a).worldObj);
		int k;

		if (itemstack != null && itemstack.getItem() == Items.dye)
		{
			k = itemstack.getItemDamage();
		}
		else
		{
			k = worldObj.rand.nextBoolean() ? i : j;
		}

		return k;
	}

	private int getColor(EntityAnimal entity)
	{
		return 15 - ((EntityBSheep) entity).getFleeceColor();
	}
}