package main.betterbreeds.entities.tasks;

import java.util.List;
import main.betterbreeds.api.Genderized;
import main.com.hk.bb.util.Rand;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class EntityAIBMate extends EntityAIBase
{
	public final Genderized gender;
	public final EntityAnimal owner;
	public final World theWorld;
	private EntityAnimal targetMate;
	public int spawnBabyDelay;
	public double moveSpeed;

	public EntityAIBMate(EntityAnimal theAnimal, double moveSpeed)
	{
		owner = theAnimal;
		gender = (Genderized) theAnimal;
		this.moveSpeed = moveSpeed;
		theWorld = theAnimal.worldObj;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (owner.isInLove())
		{
			targetMate = getNearbyMate();
			return targetMate != null;
		}
		return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return targetMate.isEntityAlive() && targetMate.isInLove() && spawnBabyDelay < 60;
	}

	@Override
	public void resetTask()
	{
		targetMate = null;
		spawnBabyDelay = 0;
	}

	@Override
	public void updateTask()
	{
		owner.getLookHelper().setLookPositionWithEntity(targetMate, 10.0F, owner.getVerticalFaceSpeed());
		if (owner.getDistanceSqToEntity(targetMate) < 9.0D)
		{
			if (gender.isFemale())
			{
				owner.riddenByEntity = targetMate;
				targetMate.ridingEntity = owner;
			}
			else
			{
				targetMate.riddenByEntity = owner;
				owner.ridingEntity = targetMate;
			}
			++spawnBabyDelay;

			if (spawnBabyDelay >= 60)
			{
				targetMate.riddenByEntity = owner.riddenByEntity = targetMate.ridingEntity = owner.ridingEntity = null;
				setPregnant();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private EntityAnimal getNearbyMate()
	{
		List<EntityAnimal> list = theWorld.getEntitiesWithinAABB(owner.getClass(), owner.boundingBox.expand(8.0F, 8.0F, 8.0F));
		double d0 = Double.MAX_VALUE;
		EntityAnimal entityanimal = null;

		for (EntityAnimal entityanimal1 : list)
		{
			if (owner.canMateWith(entityanimal1) && owner.getDistanceSqToEntity(entityanimal1) < d0)
			{
				entityanimal = entityanimal1;
				d0 = owner.getDistanceSqToEntity(entityanimal1);
			}
		}

		return entityanimal;
	}

	private void setPregnant()
	{
		owner.setGrowingAge(6000);
		targetMate.setGrowingAge(6000);
		owner.resetInLove();
		targetMate.resetInLove();
		if (gender.isFemale())
		{
			gender.setPregnant(true);
		}
		else
		{
			((Genderized) targetMate).setPregnant(true);
		}
		if (theWorld.getGameRules().getGameRuleBooleanValue("doMobLoot"))
		{
			theWorld.spawnEntityInWorld(new EntityXPOrb(theWorld, owner.posX, owner.posY, owner.posZ, Rand.nextInt(7) + 1));
		}
		EntityPlayer entityplayer = owner.func_146083_cb();

		if (entityplayer == null && targetMate.func_146083_cb() != null)
		{
			entityplayer = targetMate.func_146083_cb();
		}

		if (entityplayer != null)
		{
			entityplayer.triggerAchievement(StatList.field_151186_x);

			if (owner instanceof EntityCow)
			{
				entityplayer.triggerAchievement(AchievementList.field_150962_H);
			}
		}
	}
}
