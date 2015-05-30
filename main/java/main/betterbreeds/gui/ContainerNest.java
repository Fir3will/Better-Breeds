package main.betterbreeds.gui;

import main.betterbreeds.tiles.TileEntityNest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNest extends Container
{
	public TileEntityNest tile;

	public ContainerNest(InventoryPlayer inv, TileEntityNest tile)
	{
		this.tile = tile;
		addSlotToContainer(new Slot(tile, 0, 51, 19));
		addSlotToContainer(new Slot(tile, 1, 109, 19));
		addSlotToContainer(new Slot(tile, 2, 51, 59));
		addSlotToContainer(new Slot(tile, 3, 109, 59));

		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i)
		{
			addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int s)
	{
		return null;
	}
}
