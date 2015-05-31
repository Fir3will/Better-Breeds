package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface handles the update of an animal
 * <p>This is called whenever an animal is updated.</p>
 * 
 * @author Fir3will
 */
public interface AnimalUpdateHandler
{
	/**
	 * This method is called every time
	 * the animal is updated. This
	 * could be used to make something
	 * happen to the animal at a specific
	 * age or etc.
	 * 
	 * @param animal The animal that's being updated.
	 * @param type The type of animal
	 */
	public void updateAnimal(EntityAnimal animal, AnimalType type);
}
