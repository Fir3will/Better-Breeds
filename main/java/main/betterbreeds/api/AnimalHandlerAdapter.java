package main.betterbreeds.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;

public abstract class AnimalHandlerAdapter implements AnimalDropHandler, AnimalDeathHandler, AnimalInitHandler, AnimalPregnancyHandler, AnimalGenderHandler, AnimalTypeHandler, AnimalUpdateHandler
{
	@Override
	public void updateAnimal(EntityAnimal animal, AnimalType type)
	{}

	@Override
	public void setType(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type)
	{}

	@Override
	public void setGender(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type)
	{}

	@Override
	public void animalsMated(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{}

	@Override
	public boolean canAnimalsMate(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{
		return true;
	}

	@Override
	public void updatePregnancy(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{}

	@Override
	public void animalGiveBirth(EntityAnimal animal1, EntityAnimal animal2, AnimalType type)
	{}

	@Override
	public void initiateAnimal(EntityAnimal animal, AnimalType type)
	{}

	@Override
	public void animalDied(EntityAnimal animal, AnimalType type)
	{}

	@Override
	public List<ItemStack> itemStacksToDrop(EntityAnimal animal, AnimalType type)
	{
		return new ArrayList<ItemStack>(0);
	}
}
