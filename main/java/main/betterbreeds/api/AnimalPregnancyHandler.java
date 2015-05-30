package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

public interface AnimalPregnancyHandler
{
	public void animalsMated(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);

	public boolean canAnimalsMate(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);

	public void updatePregnancy(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);

	public void animalGiveBirth(EntityAnimal animal1, EntityAnimal animal2, AnimalType type);
}
