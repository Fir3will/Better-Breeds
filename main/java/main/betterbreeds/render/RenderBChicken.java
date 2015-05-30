package main.betterbreeds.render;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.render.models.ModelBChicken;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBChicken extends RenderLiving
{
	private final ModelBase maleModel, femaleModel;

	public RenderBChicken()
	{
		super(new ModelChicken(), 0.3F);
		maleModel = new ModelBChicken(true);
		femaleModel = new ModelBChicken(false);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e)
	{
		if (e instanceof EntityBChicken && ((EntityBChicken) e).isChild()) return new ResourceLocation(BetterBreeds.MODID + ":textures/entities/chicken/Baby_Chicken.png");
		return new ResourceLocation(BetterBreeds.MODID + ":textures/entities/chicken/Chicken.png");//Gender.getLocation("chicken", (Genderized) e);
	}

	@Override
	public void doRender(Entity _entity, double posX, double posY, double posZ, float var8, float var9)
	{
		final EntityBChicken chick = (EntityBChicken) _entity;

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		mainModel = chick.isFemale() ? femaleModel : maleModel;
		super.doRender(_entity, posX, posY, posZ, var8, var9);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GL11.glRotatef(180F, 0, 1F, 0F);
		GL11.glRotatef(180F, 0, 0, 1F);
		GL11.glTranslatef(0F, 3.5F, 0F);
	}

	@Override
	protected float handleRotationFloat(EntityLivingBase entity, float a)
	{
		final EntityChicken chicken = (EntityChicken) entity;
		final float f1 = chicken.field_70888_h + (chicken.field_70886_e - chicken.field_70888_h) * a;
		final float f2 = chicken.field_70884_g + (chicken.destPos - chicken.field_70884_g) * a;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}
}