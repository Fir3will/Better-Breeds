package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface handles the type of an animal
 * <p>This is called when an animal's type is first set upon spawning.</p>
 * 
 * @author Fir3will
 */
public interface AnimalTypeHandler
{
	/**
	 * This method will be called when
	 * an animal's type is set upon
	 * spawning. It is set prior so
	 * you can increase, decrease the
	 * chance to have a specific
	 * textured animal or anything else.
	 * 
	 * @param animal The animal that's type is being set.
	 * @param genderizedAnimal the same animal, just a Genderized instance for convenience.
	 * @param type The type of animal
	 */
	public void setType(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type);
}
