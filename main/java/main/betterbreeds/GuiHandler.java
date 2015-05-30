package main.betterbreeds;

import main.betterbreeds.gui.ContainerNest;
import main.betterbreeds.gui.GuiNest;
import main.betterbreeds.tiles.TileEntityNest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		final TileEntity tile = world.getTileEntity(x, y, z);
		switch (ID)
		{
			case IDs.TILE_NEST:
				return tile instanceof TileEntityNest ? new ContainerNest(player.inventory, (TileEntityNest) tile) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		final TileEntity tile = world.getTileEntity(x, y, z);
		switch (ID)
		{
			case IDs.TILE_NEST:
				return tile instanceof TileEntityNest ? new GuiNest(player.inventory, (TileEntityNest) tile) : null;
		}
		return null;
	}

	public static interface IDs
	{
		public static final int TILE_NEST = 5;
	}
}
