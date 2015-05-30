package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

public interface AnimalInitHandler
{
	public void initiateAnimal(EntityAnimal animal, AnimalType type);
}
