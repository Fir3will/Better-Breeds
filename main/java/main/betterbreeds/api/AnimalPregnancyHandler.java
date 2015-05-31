package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface handles anything
 * that the animal does that involves
 * breeding. This includes whether the
 * animal can mate with the other, what
 * will happen after they mated, and also
 * allow you to update their pregnancy.
 * This will also be called when the animal
 * gives birth to the animal.
 * 
 * @author Fir3will
 */
public interface AnimalPregnancyHandler
{
	/**
	 * This method is called when two
	 * animals finish mating together.
	 * This can allow you to do your
	 * own thing during their pregnancy.
	 * Maybe add something to their
	 * NBTCompoundTag that you can pull
	 * data from later in the
	 * updatePregnancy method.
	 * 
	 * @param animal1 The base animal that it's being called from.
	 * @param animal2 The mate that the base animal is mating with
	 * @param type The type of the two animals.
	 */
	public void animalsMated(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);

	/**
	 * This method is called when an animal is
	 * looking around at nearby mates.
	 * This will allow you to allow them to
	 * mate or not.
	 * 
	 * @param animal1 The base animal that it's being called from.
	 * @param animal2 The mate that the base animal is looking for.
	 * @param defaultValue The default value that was set by the mod.
	 * @param type The type of the two animals.
	 * @return true if they can mate
	 * <p> false if they cannot mate
	 */
	public boolean canAnimalsMate(EntityAnimal animal1, EntityAnimal animal2, boolean defaultValue, AnimalType type);

	/**
	 * This is called every tick
	 * that the animal is pregnant.
	 * This will allow you to have
	 * your own type of pregnancy
	 * for the animal provided.
	 * 
	 * @param animal1 The base animal that it's being called from.
	 * @param animal2 The mate that the base animal has mated with
	 * @param type The type of the two animals.
	 */
	public void updatePregnancy(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);

	/**
	 * This is called when the animal's
	 * pregnancy ticker is finished, this
	 * will allow you to alter the baby
	 * any way you want. You can change
	 * the baby's gender or type.
	 * 
	 * @param animal1 The base animal that it's being called from.
	 * @param animal2 The mate that the base animal has mated with
	 * @param baby The child between the animals created.
	 * @param type The type of the two animals.
	 */
	public void animalGiveBirth(EntityAnimal animal1, EntityAnimal animal2, EntityAnimal baby, AnimalType type);
}
