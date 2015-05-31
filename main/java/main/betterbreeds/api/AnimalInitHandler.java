package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface handles the initialization
 * of an animal.
 * <p>This is called when an animal is initiated.
 * After everything is registered to DataWatcher,
 * this is called. But before the gender and type
 * of the animal are set.
 * @author owner
 *
 */
public interface AnimalInitHandler
{
	/**
	 * This method will allow mod makers
	 * to add objects to the DataWatcher
	 * and also set anything to the animal
	 * before hand. Maybe add an
	 * IExtendedEntityProperties perhaps?
	 * 
	 * @param animal The animal being initialized
	 * @param type The type of animal it is.
	 */
	public void initiateAnimal(EntityAnimal animal, AnimalType type);
}
