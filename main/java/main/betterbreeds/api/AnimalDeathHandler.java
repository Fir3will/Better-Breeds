package main.betterbreeds.api;

import net.minecraft.entity.passive.EntityAnimal;

/**
 * This interface
 * 
 * @author owner
 */
public interface AnimalDeathHandler
{
	public void animalDied(EntityAnimal animal, AnimalType type);
}
