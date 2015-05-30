package main.betterbreeds.gui;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.tiles.TileEntityNest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiNest extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation(BetterBreeds.MODID + ":textures/gui/nest.png");

	public GuiNest(InventoryPlayer inv, TileEntityNest tile)
	{
		super(new ContainerNest(inv, tile));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		fontRendererObj.drawString("Chicken Nest", xSize / 2 - fontRendererObj.getStringWidth("Chicken Nest") / 2, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		final int k = (width - xSize) / 2;
		final int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

	}
}
