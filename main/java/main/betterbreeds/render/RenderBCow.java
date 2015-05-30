package main.betterbreeds.render;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.api.Genderized;
import main.betterbreeds.entities.Gender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBCow extends RenderLiving
{
	private static final ResourceLocation female = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/cow/female_cow.png");
	private static final ResourceLocation male = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/cow/male_cow.png");

	public RenderBCow()
	{
		super(new ModelCow(), 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return Gender.getLocation("cow", (Genderized) e);
	}

	@Override
	protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
	{
		super.renderModel(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
		bindTexture(((Genderized) p_77036_1_).isFemale() ? female : male);

		if (!p_77036_1_.isInvisible())
		{
			mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
		}
		else if (!p_77036_1_.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
		{
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
			GL11.glDepthMask(false);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
			mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glPopMatrix();
			GL11.glDepthMask(true);
		}
		else
		{
			mainModel.setRotationAngles(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, p_77036_1_);
		}
	}
}