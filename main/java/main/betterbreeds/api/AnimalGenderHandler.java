package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface handles the gender of an animal
 * <p>This is called when an animal's gender is first set upon spawning.</p>
 * 
 * @author Fir3will
 */
public interface AnimalGenderHandler
{
	/**
	 * This method will be called when
	 * an animal's gender is set upon
	 * spawning. It is set prior so
	 * you can increase, decrease the
	 * chance to have a male, or female,
	 * etc.
	 * 
	 * @param animal The animal that's gender is being set.
	 * @param genderizedAnimal the same animal, just a Genderized instance for convenience.
	 * @param type The type of animal
	 */
	public void setGender(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type);
}
