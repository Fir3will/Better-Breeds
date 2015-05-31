package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;

/**
 * This interface handles the death of an animal
 * <p>This is called when an animal dies. You
 * can do multiple things with this. Maybe bring
 * them back to life? A resurrection mod!</p>
 * 
 * @author Fir3will
 */
public interface AnimalDeathHandler
{
	/**
	 * This method will change what happens with the animal when it dies!
	 * It's all up to you! You do have the animal right?
	 * 
	 * @param animal The animal that has died.
	 * @param damageSource The source of damage that this animal died to.
	 * @param type The type of animal it is.
	 */
	public void animalDied(EntityAnimal animal, DamageSource damageSource, AnimalType type);
}
