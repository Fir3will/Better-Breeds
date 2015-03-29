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

public class ModelFemaleChicken extends ModelBase
{
	public final int MCA_MIN_REQUESTED_VERSION = 3;
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

	MCAModelRenderer body;
	MCAModelRenderer leftLeg;
	MCAModelRenderer rightLeg;
	MCAModelRenderer chest;
	MCAModelRenderer neck;
	MCAModelRenderer leftWing;
	MCAModelRenderer rightWing;
	MCAModelRenderer leftFoot;
	MCAModelRenderer rightFoot;
	MCAModelRenderer head;
	MCAModelRenderer beak;
	MCAModelRenderer waddle;
	MCAModelRenderer comb;

	public ModelFemaleChicken()
	{
		MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

		textureWidth = 128;
		textureHeight = 64;

		body = new MCAModelRenderer(this, "body", 2, 9);
		body.mirror = false;
		body.addBox(-2.5F, 0.0F, -2.5F, 5, 5, 5);
		body.setInitialRotationPoint(0.0F, -28.0F, 3.0F);
		body.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.17364818F, 0.0F, 0.0F, 0.9848077F)).transpose());
		body.setTextureSize(128, 64);
		parts.put(body.boxName, body);

		leftLeg = new MCAModelRenderer(this, "leftLeg", 20, 0);
		leftLeg.mirror = false;
		leftLeg.addBox(0.0F, -5.0F, 0.0F, 1, 6, 1);
		leftLeg.setInitialRotationPoint(1.5F, 0.0F, -2.0F);
		leftLeg.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25881904F, 0.0F, 0.0F, 0.9659258F)).transpose());
		leftLeg.setTextureSize(128, 64);
		parts.put(leftLeg.boxName, leftLeg);
		body.addChild(leftLeg);

		rightLeg = new MCAModelRenderer(this, "rightLeg", 24, 0);
		rightLeg.mirror = false;
		rightLeg.addBox(0.0F, -5.0F, 0.0F, 1, 6, 1);
		rightLeg.setInitialRotationPoint(-2.5F, 0.0F, -2.0F);
		rightLeg.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25881904F, 0.0F, 0.0F, 0.9659258F)).transpose());
		rightLeg.setTextureSize(128, 64);
		parts.put(rightLeg.boxName, rightLeg);
		body.addChild(rightLeg);

		chest = new MCAModelRenderer(this, "chest", 18, 6);
		chest.mirror = false;
		chest.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
		chest.setInitialRotationPoint(-2.0F, 4.5F, -2.0F);
		chest.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		chest.setTextureSize(128, 64);
		parts.put(chest.boxName, chest);
		body.addChild(chest);

		neck = new MCAModelRenderer(this, "neck", 12, 0);
		neck.mirror = false;
		neck.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
		neck.setInitialRotationPoint(-1.0F, 5.0F, -1.0F);
		neck.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		neck.setTextureSize(128, 64);
		parts.put(neck.boxName, neck);
		body.addChild(neck);

		leftWing = new MCAModelRenderer(this, "leftWing", 32, 0);
		leftWing.mirror = false;
		leftWing.addBox(0.0F, -4.0F, 0.0F, 1, 4, 3);
		leftWing.setInitialRotationPoint(2.5F, 4.0F, -2.0F);
		leftWing.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		leftWing.setTextureSize(128, 64);
		parts.put(leftWing.boxName, leftWing);
		body.addChild(leftWing);

		rightWing = new MCAModelRenderer(this, "rightWing", 40, 0);
		rightWing.mirror = false;
		rightWing.addBox(-1.0F, -4.0F, 0.0F, 1, 4, 3);
		rightWing.setInitialRotationPoint(-2.5F, 4.0F, -2.0F);
		rightWing.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		rightWing.setTextureSize(128, 64);
		parts.put(rightWing.boxName, rightWing);
		body.addChild(rightWing);

		leftFoot = new MCAModelRenderer(this, "leftFoot", 0, 10);
		leftFoot.mirror = false;
		leftFoot.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		leftFoot.setInitialRotationPoint(0.0F, -5.0F, 0.0F);
		leftFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.08715574F, 0.0F, 0.0F, 0.9961947F)).transpose());
		leftFoot.setTextureSize(128, 64);
		parts.put(leftFoot.boxName, leftFoot);
		leftLeg.addChild(leftFoot);

		rightFoot = new MCAModelRenderer(this, "rightFoot", 0, 10);
		rightFoot.mirror = false;
		rightFoot.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		rightFoot.setInitialRotationPoint(0.0F, -5.0F, 0.0F);
		rightFoot.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.08715574F, 0.0F, 0.0F, 0.9961947F)).transpose());
		rightFoot.setTextureSize(128, 64);
		parts.put(rightFoot.boxName, rightFoot);
		rightLeg.addChild(rightFoot);

		head = new MCAModelRenderer(this, "head", 0, 0);
		head.mirror = false;
		head.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
		head.setInitialRotationPoint(1.0F, 0.0F, 1.0F);
		head.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F)).transpose());
		head.setTextureSize(128, 64);
		parts.put(head.boxName, head);
		neck.addChild(head);

		beak = new MCAModelRenderer(this, "beak", 12, 3);
		beak.mirror = false;
		beak.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
		beak.setInitialRotationPoint(-1.0F, 1.0F, 0.0F);
		beak.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		beak.setTextureSize(128, 64);
		parts.put(beak.boxName, beak);
		head.addChild(beak);

		waddle = new MCAModelRenderer(this, "waddle", 8, 0);
		waddle.mirror = false;
		waddle.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		waddle.setInitialRotationPoint(-0.5F, 0.79999995F, 0.5F);
		waddle.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		waddle.setTextureSize(128, 64);
		parts.put(waddle.boxName, waddle);
		head.addChild(waddle);

		comb = new MCAModelRenderer(this, "comb", 0, 8);
		comb.mirror = false;
		comb.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		comb.setInitialRotationPoint(0.0F, 2.5F, 0.0F);
		comb.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.38268346F, 0.9238795F)).transpose());
		comb.setTextureSize(128, 64);
		parts.put(comb.boxName, comb);
		head.addChild(comb);

	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		AnimationHandler.performAnimationInModel(parts, (EntityBChicken) par1Entity);
		body.render(par7);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		//head.rotateAngleX = par5 / (180F / (float) Math.PI);
		//head.rotateAngleY = par4 / (180F / (float) Math.PI);
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		rightWing.rotateAngleZ = par3;
		leftWing.rotateAngleZ = -par3;
		// ModelChicken
	}

	public MCAModelRenderer getModelRendererFromName(String name)
	{
		return parts.get(name);
	}
}