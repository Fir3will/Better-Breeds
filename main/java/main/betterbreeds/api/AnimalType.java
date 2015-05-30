package main.betterbreeds.api;

import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.entities.EntityBCow;
import main.betterbreeds.entities.EntityBHorse;
import main.betterbreeds.entities.EntityBPig;
import main.betterbreeds.entities.EntityBSheep;
import main.betterbreeds.entities.EntityBWolf;

/**
 * This class is used to specify what animal you are going to be dealing with. 
 * <p>It's in every handler method that exists, for convenience</p>
 * 
 * @author Fir3will
 */
public enum AnimalType
{
	/** The Sheep Replacement */
	BBSHEEP(EntityBSheep.class, true),
	/** The Cow Replacement */
	BBCOW(EntityBCow.class, true),
	/** The Chicken Replacement */
	BBCHICKEN(EntityBChicken.class, true),
	/** The Pig Replacement */
	BBPIG(EntityBPig.class, true),
	/** The Horse Replacement */
	BBHORSE(EntityBHorse.class, true),
	/** The Wolf Replacement */
	BBWOLF(EntityBWolf.class, true);

	/**
	 * The class for the given animal
	 * Just a simple way for the API user to use the class as a backup
	 */
	public final Class<? extends Genderized> animalClass;
	/**
	 * The name of the animal. This will be the name that is on the spawn egg, so BBCOW would be "Cow"
	 * Helper field, just for convenience
	 */
	public final String animalName;
	/**
	 * This will be true if this animal was added by this mod, Better Breeds
	 */
	public final boolean originalBBAnimal;

	private AnimalType(Class<? extends Genderized> animalClass, boolean originalBBAnimal)
	{
		this.animalClass = animalClass;
		this.originalBBAnimal = originalBBAnimal;
		animalName = name().replaceAll("BB", "").substring(0, 1).toUpperCase() + name().replaceAll("BB", "").substring(1).toLowerCase();
	}
}
