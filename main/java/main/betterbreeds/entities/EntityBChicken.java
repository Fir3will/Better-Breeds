package main.betterbreeds.entities;

import main.betterbreeds.Config;
import main.betterbreeds.animations.AnimationHandlerChicken;
import main.betterbreeds.api.Genderized;
import main.betterbreeds.entities.tasks.EntityAIBMate;
import main.betterbreeds.tiles.TileEntityNest;
import main.com.hk.bb.util.Rand;
import main.com.hk.bb.util.StackHelper;
import main.com.hk.bb.util.Vector3I;
import mca.library.common.IMCAnimatedEntity;
import mca.library.common.animation.AnimationHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityBChicken extends EntityChicken implements Genderized, IMCAnimatedEntity
{
	public final AnimationHandlerChicken animHandler = new AnimationHandlerChicken(this);
	public static final int MAX_TEXTURES = 10;
	public int realTimeUntilNextEgg, recentlyMated, getUpDelay;
	public Vector3I nest;
	public boolean isIncubating;

	public EntityBChicken(World world)
	{
		super(world);
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 1.4D));
		tasks.addTask(2, Config.addPregnancy ? new EntityAIBMate(this, 1.0D) : new EntityAIMate(this, 1.0D));
		tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.wheat_seeds, false));
		tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
		tasks.addTask(5, new EntityAIWander(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		setFemale(Rand.nextBoolean());
		setType(Rand.nextInt(MAX_TEXTURES));
		setPregnant(false);
		realTimeUntilNextEgg = Config.timeTillNextEgg;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		Gender.setObjects(this);
	}

	@Override
	public boolean isMovementCeased()
	{
		return isIncubating;
	}

	@Override
	public void onLivingUpdate()
	{
		if (Config.addPregnancy)
		{
			recentlyMated = recentlyMated > 0 ? recentlyMated - 1 : 0;
			if (recentlyMated > 0 && recentlyMated % 10 == 0)
			{
				worldObj.spawnParticle("heart", posX + rand.nextFloat() * width * 2.0F - width, posY + 0.5D + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D);
			}
			timeUntilNextEgg = 100;
			if (recentlyMated <= 0)
			{
				setPregnant(false);
			}
			if (nest != null && worldObj.getTileEntity(nest.x, nest.y, nest.z) instanceof TileEntityNest)
			{
				TileEntityNest n = (TileEntityNest) worldObj.getTileEntity(nest.x, nest.y, nest.z);
				getUpDelay--;
				n.chicken = this;
				if (getUpDelay <= 0)
				{
					isIncubating = !isIncubating;
					getUpDelay = !isIncubating ? 240 : 24000;
				}

				if (isIncubating)
				{
					setPosition(nest.x + 0.5D, nest.y, nest.z + 0.5D);
					n.heatEggs();
				}
				if (n.eggs[0] == null && n.eggs[1] == null && n.eggs[2] == null && n.eggs[3] == null)
				{
					nest = null;
				}
			}
			else
			{
				nest = null;
			}

			if (!worldObj.isRemote && isFemale() && !isChild() && !func_152116_bZ() && --realTimeUntilNextEgg <= 0)
			{
				playSound("mob.chicken.plop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				ItemStack s = new ItemStack(Items.egg, 1, 0);
				StackHelper.newNBT(s).setInteger("Fertility", isPregnant() ? 1 : 0);
				nest = nest == null ? getNearbyUnoccupiedNest() : nest;
				if (nest != null)
				{
					isIncubating = true;
					getUpDelay = 24000;
					TileEntityNest tile = (TileEntityNest) worldObj.getTileEntity(nest.x, nest.y, nest.z);
					for (int i = 0; i < 4; i++)
					{
						if (tile.eggs[i] == null)
						{
							tile.eggs[i] = s;
							break;
						}
					}
					s = null;
				}
				if (s != null)
				{
					entityDropItem(s, 0.0F);
				}
				realTimeUntilNextEgg = Config.timeTillNextEgg;
			}
		}

		super.onLivingUpdate();
	}

	public Vector3I getNearbyUnoccupiedNest()
	{
		for (int i = -3; i < 4; i++)
		{
			for (int j = -3; j < 4; j++)
			{
				for (int k = -3; k < 4; k++)
				{
					Vector3I v = new Vector3I(this);
					TileEntity t = worldObj.getTileEntity(v.x + i, v.y + j, v.z + k);
					if (t instanceof TileEntityNest && ((TileEntityNest) t).isValidOwner(this)) return new Vector3I(t.xCoord, t.yCoord, t.zCoord);
				}
			}
		}
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Gender", isFemale());
		nbt.setInteger("Type", getType());
		nbt.setBoolean("Has Nest", nest != null);
		nbt.setInteger("Nest X", nest == null ? 0 : nest.x);
		nbt.setInteger("Nest Y", nest == null ? 0 : nest.y);
		nbt.setInteger("Nest Z", nest == null ? 0 : nest.z);
		nbt.setInteger("Next Egg", realTimeUntilNextEgg);
		nbt.setInteger("Recently Mated", recentlyMated);
		nbt.setInteger("Get Up Delay", getUpDelay);
		nbt.setBoolean("Is Incubating", isIncubating);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setFemale(nbt.getBoolean("Gender"));
		setType(nbt.getInteger("Type"));
		if (nbt.getBoolean("Has Nest"))
		{
			nest = new Vector3I(nbt.getInteger("Nest X"), nbt.getInteger("Nest Y"), nbt.getInteger("Nest Z"));
		}
		realTimeUntilNextEgg = nbt.getInteger("Next Egg");
		recentlyMated = nbt.getInteger("Recently Mated");
		getUpDelay = nbt.getInteger("Get Up Delay");
		isIncubating = nbt.getBoolean("Is Incubating");
	}

	@Override
	public float getSoundPitch()
	{
		return super.getSoundPitch() + (isFemale() ? 0.2F : -0.2F);
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
		recentlyMated = pregnant ? 24000 : 0;
		Gender.setPregnant(this, pregnant);
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
	public EntityChicken createChild(EntityAgeable a)
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