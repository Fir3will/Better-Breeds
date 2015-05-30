package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

public interface AnimalTypeHandler
{
	public void setType(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type);
}
