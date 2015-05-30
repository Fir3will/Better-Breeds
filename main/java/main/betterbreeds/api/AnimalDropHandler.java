package main.betterbreeds.api;

import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;

/**
 * This interface handles the drops of an animal
 * <p>This is called when an animal begins it's drops. You can add to the list of items being dropped. </p>
 * 
 * @author Fir3will
 */
public interface AnimalDropHandler
{
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
	public List<ItemStack> itemStacksToDrop(EntityAnimal animal, AnimalType type);
}
