package main.betterbreeds.render;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.entities.Gender;
import main.betterbreeds.entities.Gender.Genderized;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBSheep extends RenderLiving
{
	private static final ResourceLocation sheepTextures = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
	private static final ResourceLocation female = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/sheep/female_sheep.png");
	private static final ResourceLocation male = new ResourceLocation(BetterBreeds.MODID + ":textures/entities/sheep/male_sheep.png");

	public RenderBSheep()
	{
		super(new ModelSheep2(), 0.7F);
		setRenderPassModel(new ModelSheep1());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		return Gender.getLocation("sheep", (Genderized) e);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase sheep, int f, float lol)
	{
		if (f == 0 && !((EntitySheep) sheep).getSheared())
		{
			bindTexture(sheepTextures);

			if (((EntitySheep) sheep).hasCustomNameTag() && "jeb_".equals(((EntitySheep) sheep).getCustomNameTag()))
			{
				final int k = ((EntitySheep) sheep).ticksExisted / 25 + ((EntitySheep) sheep).getEntityId();
				final int l = k % EntitySheep.fleeceColorTable.length;
				final int i1 = (k + 1) % EntitySheep.fleeceColorTable.length;
				final float f1 = (((EntitySheep) sheep).ticksExisted % 25 + lol) / 25.0F;
				GL11.glColor3f(EntitySheep.fleeceColorTable[l][0] * (1.0F - f1) + EntitySheep.fleeceColorTable[i1][0] * f1, EntitySheep.fleeceColorTable[l][1] * (1.0F - f1) + EntitySheep.fleeceColorTable[i1][1] * f1, EntitySheep.fleeceColorTable[l][2] * (1.0F - f1) + EntitySheep.fleeceColorTable[i1][2] * f1);
			}
			else
			{
				final int j = ((EntitySheep) sheep).getFleeceColor();
				GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
			}

			return 1;
		}
		else return -1;
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