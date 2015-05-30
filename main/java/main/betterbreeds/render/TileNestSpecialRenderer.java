package main.betterbreeds.render;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.render.models.ModelNest;
import main.betterbreeds.tiles.TileEntityNest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class TileNestSpecialRenderer extends TileEntitySpecialRenderer implements IItemRenderer
{
	private static final ResourceLocation texture = new ResourceLocation(BetterBreeds.MODID + ":textures/models/nest.png");
	private final ModelNest model;

	public TileNestSpecialRenderer()
	{
		model = new ModelNest();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		//GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if (te != null)
		{
			GL11.glRotatef(te.getBlockMetadata() * 90.0F, 0.0F, 1.0F, 0.0F);
		}
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		if (te != null)
		{
			model.renderTile((TileEntityNest) te, 0.0625F);
		}
		else
		{
			model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		renderTileEntityAt(null, 0.0D, 0.0D, 0.0D, 0.0F);
	}
}
