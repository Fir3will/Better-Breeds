package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

public interface AnimalGenderHandler
{
	public void setGender(EntityAnimal animal, Genderized genderizedAnimal, AnimalType type);
}
