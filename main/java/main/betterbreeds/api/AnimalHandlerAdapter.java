package main.betterbreeds.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

/**
 * This class is created solely for
 * the purpose of convenience. The
 * mod maker can extend this class
 * and override it's methods.
 * This can be CPU intensive as it
 * is going to be run multiple times
 * because it is added to all the
 * handlers. 
 * 
 * @author Fir3will
 */
public abstract class AnimalHandlerAdapter implements AnimalDropHandler, AnimalDeathHandler, AnimalInitHandler, AnimalPregnancyHandler, AnimalGenderHandler, AnimalTypeHandler, AnimalUpdateHandler
{
	@Override
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
	public void updateAnimal(EntityAnimal animal, AnimalType type)
	{}

	@Override
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
	public void setType(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type)
	{}

	@Override
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
	public void setGender(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type)
	{}

	@Override
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
	public void animalsMated(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{}

	@Override
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
	public boolean canAnimalsMate(EntityAnimal animal1, EntityAnimal animal2, boolean defaultValue, AnimalType type)
	{
		return true;
	}

	@Override
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
	public void updatePregnancy(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{}

	@Override
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
	public void animalGiveBirth(EntityAnimal animal1, EntityAnimal animal2, EntityAnimal baby, AnimalType type)
	{}

	@Override
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
	public void initiateAnimal(EntityAnimal animal, AnimalType type)
	{}

	@Override
	/**
	 * This method will change what happens with the animal when it dies!
	 * It's all up to you! You do have the animal right?
	 * 
	 * @param animal The animal that has died.
	 * @param damageSource The source of damage that this animal died to.
	 * @param type The type of animal it is.
	 */
	public void animalDied(EntityAnimal animal, DamageSource damageSource, AnimalType type)
	{}

	@Override
	/**
	 * This method will return the list of 
	 * items that will be added to the drops 
	 * of the specified animal. Sadly, you
	 * cannot remove from the list, a new
	 * list is given every time. <b>Returning
	 * null might cause a crash</b>, just use
	 * <code>Collections.EMPTY_LIST</code> if
	 * you're not adding anything to drop.
	 * 
	 * @param animal The animal that will do the drops
	 * @param type The type of animal it is
	 * @return A list of all the ItemStacks that should be dropped from this animal
	 */
	public List<ItemStack> itemStacksToDrop(EntityAnimal animal, AnimalType type)
	{
		return new ArrayList<ItemStack>(0);
	}
}
