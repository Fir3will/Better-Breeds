package main.betterbreeds.render;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.entities.Gender;
import main.betterbreeds.entities.Gender.Genderized;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBWolf extends RenderLiving
{
	private static final ResourceLocation anrgyWolfTextures = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/wolf/wolf_angry.png");
	private static final ResourceLocation wolfCollarTextures = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
	private static final ResourceLocation female = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/wolf/female_wolf.png");
	private static final ResourceLocation male = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/wolf/male_wolf.png");

	public RenderBWolf()
	{
		super(new ModelWolf(), 0.5F);
		setRenderPassModel(new ModelWolf());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return Gender.getLocation("wolf", (Genderized) e);
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

	protected float handleRotationFloat(EntityWolf p_77044_1_, float p_77044_2_)
	{
		return p_77044_1_.getTailRotation();
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	protected int shouldRenderPass(EntityWolf p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		if (p_77032_2_ == 0 && p_77032_1_.getWolfShaking())
		{
			final float f1 = p_77032_1_.getBrightness(p_77032_3_) * p_77032_1_.getShadingWhileShaking(p_77032_3_);
			bindTexture(Gender.getLocation("wolf", (Genderized) p_77032_1_));
			GL11.glColor3f(f1, f1, f1);
			return 1;
		}
		else if (p_77032_2_ == 1 && p_77032_1_.isTamed())
		{
			bindTexture(wolfCollarTextures);
			final int j = p_77032_1_.getCollarColor();
			GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
			return 1;
		}
		else if (p_77032_2_ == 2 && p_77032_1_.isAngry())
		{
			bindTexture(anrgyWolfTextures);
			return 1;
		}
		else return -1;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		return this.shouldRenderPass((EntityWolf) p_77032_1_, p_77032_2_, p_77032_3_);
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_)
	{
		return this.handleRotationFloat((EntityWolf) p_77044_1_, p_77044_2_);
	}
}