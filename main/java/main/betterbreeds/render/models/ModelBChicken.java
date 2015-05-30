package main.betterbreeds.render.models;

import java.util.HashMap;
import main.betterbreeds.entities.EntityBChicken;
import mca.library.client.MCAModelRenderer;
import mca.library.common.MCAVersionChecker;
import mca.library.common.animation.AnimationHandler;
import mca.library.common.math.Matrix4f;
import mca.library.common.math.Quaternion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelBChicken extends ModelBase
{
	public final int MCA_MIN_REQUESTED_VERSION = 3;
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();
	public boolean isMale;

	MCAModelRenderer body;
	MCAModelRenderer rightWing;
	MCAModelRenderer leftWing;
	MCAModelRenderer head;
	MCAModelRenderer rightLeg;
	MCAModelRenderer leftLeg;
	MCAModelRenderer bill;
	MCAModelRenderer waddle;
	MCAModelRenderer comb;
	MCAModelRenderer ears;

	public ModelBChicken(boolean isMale)
	{
		this.isMale = isMale;
		MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

		textureWidth = 128;
		textureHeight = 64;

		body = new MCAModelRenderer(this, "body", 0, 9);
		body.mirror = false;
		body.addBox(-3.0F, -2.0F, -3.0F, 6, 6, 8);
		body.setInitialRotationPoint(0.0F, -24.0F, 0.0F);
		body.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		body.setTextureSize(128, 64);
		parts.put(body.boxName, body);

		rightWing = new MCAModelRenderer(this, "rightWing", 28, 13);
		rightWing.mirror = false;
		rightWing.addBox(-1.0F, -4.0F, -6.0F, 1, 4, 6);
		rightWing.setInitialRotationPoint(-3.0F, 4.0F, 4.0F);
		rightWing.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		rightWing.setTextureSize(128, 64);
		parts.put(rightWing.boxName, rightWing);
		body.addChild(rightWing);

		leftWing = new MCAModelRenderer(this, "leftWing", 28, 13);
		leftWing.mirror = false;
		leftWing.addBox(0.0F, -4.0F, -6.0F, 1, 4, 6);
		leftWing.setInitialRotationPoint(3.0F, 4.0F, 4.0F);
		leftWing.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		leftWing.setTextureSize(128, 64);
		parts.put(leftWing.boxName, leftWing);
		body.addChild(leftWing);

		head = new MCAModelRenderer(this, "head", 0, 0);
		head.mirror = false;
		head.addBox(-2.0F, -2.0F, -1.0F, 4, 6, 3);
		head.setInitialRotationPoint(0.0F, 4.0F, 5.0F);
		head.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		head.setTextureSize(128, 64);
		parts.put(head.boxName, head);
		body.addChild(head);

		rightLeg = new MCAModelRenderer(this, "rightLeg", 26, 0);
		rightLeg.mirror = false;
		rightLeg.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 3);
		rightLeg.setInitialRotationPoint(-1.5F, -2.0F, 0.0F);
		rightLeg.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		rightLeg.setTextureSize(128, 64);
		parts.put(rightLeg.boxName, rightLeg);
		body.addChild(rightLeg);

		leftLeg = new MCAModelRenderer(this, "leftLeg", 26, 0);
		leftLeg.mirror = false;
		leftLeg.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 3);
		leftLeg.setInitialRotationPoint(1.5F, -2.0F, 0.0F);
		leftLeg.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		leftLeg.setTextureSize(128, 64);
		parts.put(leftLeg.boxName, leftLeg);
		body.addChild(leftLeg);

		bill = new MCAModelRenderer(this, "bill", 14, 0);
		bill.mirror = false;
		bill.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 2);
		bill.setInitialRotationPoint(0.0F, 0.0F, 2.0F);
		bill.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bill.setTextureSize(128, 64);
		parts.put(bill.boxName, bill);
		head.addChild(bill);

		waddle = new MCAModelRenderer(this, "waddle", 14, 4);
		waddle.mirror = false;
		waddle.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 2);
		waddle.setInitialRotationPoint(0.0F, 0.0F, 2.0F);
		waddle.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		waddle.setTextureSize(128, 64);
		parts.put(waddle.boxName, waddle);
		head.addChild(waddle);

		if (isMale)
		{
			comb = new MCAModelRenderer(this, "comb", 0, 10);
			comb.mirror = false;
			comb.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3);
			comb.setInitialRotationPoint(0.0F, 4.0F, -1.0F);
			comb.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
			comb.setTextureSize(128, 64);
			parts.put(comb.boxName, comb);
			head.addChild(comb);

			ears = new MCAModelRenderer(this, "ears", 20, 12);
			ears.mirror = false;
			ears.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 1);
			ears.setInitialRotationPoint(0.0F, 2.0F, 0.0F);
			ears.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
			ears.setTextureSize(128, 64);
			parts.put(ears.boxName, ears);
			head.addChild(ears);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		final EntityBChicken entity = (EntityBChicken) par1Entity;
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		AnimationHandler.performAnimationInModel(parts, entity);

		if (isChild)
		{

			if (isMale)
			{
				head.childModels.remove(ears);
				head.childModels.remove(comb);
			}
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, -2.0F, 0.0F);
		}
		body.render(par7);
		if (isChild && isMale)
		{
			head.childModels.add(ears);
			head.childModels.add(comb);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		set(head, par5 / (180F / (float) Math.PI), par4 / (180F / (float) Math.PI), 0.0F);
		set(rightLeg, MathHelper.cos(par1 * 0.6662F) * 1.4F * par2, 0.0F, 0.0F);
		set(leftLeg, MathHelper.cos((float) (par1 * 0.6662F + Math.PI)) * 1.4F * par2, 0.0F, 0.0F);
		set(rightWing, 0.0F, 0.0F, -par3);
		set(leftWing, 0.0F, 0.0F, par3);
	}

	private void set(MCAModelRenderer model, float x, float y, float z)
	{
		model.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(x, y, z, 1.0F)).transpose());
	}

	public MCAModelRenderer getModelRendererFromName(String name)
	{
		return parts.get(name);
	}
}