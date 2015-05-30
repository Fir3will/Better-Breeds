package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

public interface AnimalUpdateHandler
{
	public void updateAnimal(EntityAnimal animal, AnimalType type);
}
