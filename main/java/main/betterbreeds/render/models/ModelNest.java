package main.betterbreeds.render.models;

import java.util.HashMap;
import main.betterbreeds.tiles.TileEntityNest;
import mca.library.client.MCAModelRenderer;
import mca.library.common.MCAVersionChecker;
import mca.library.common.math.Matrix4f;
import mca.library.common.math.Quaternion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelNest extends ModelBase
{
	public final int MCA_MIN_REQUESTED_VERSION = 3;
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

	MCAModelRenderer base;
	MCAModelRenderer top;
	MCAModelRenderer bottom;
	MCAModelRenderer left;
	MCAModelRenderer right;
	MCAModelRenderer topBase;
	MCAModelRenderer sticks;
	MCAModelRenderer insideBottom;
	MCAModelRenderer eggLT;
	MCAModelRenderer eggRT;
	MCAModelRenderer eggLB;
	MCAModelRenderer eggRB;
	MCAModelRenderer stick1;
	MCAModelRenderer stick2;
	MCAModelRenderer leftWall;
	MCAModelRenderer rightWall;
	MCAModelRenderer topWall;

	public ModelNest()
	{
		MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

		textureWidth = 128;
		textureHeight = 64;

		base = new MCAModelRenderer(this, "base", 0, 0);
		base.mirror = false;
		base.addBox(0.0F, 0.0F, 0.0F, 30, 2, 30);
		base.setInitialRotationPoint(-15.0F, -32.0F, -15.0F);
		base.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		base.setTextureSize(128, 64);
		parts.put(base.boxName, base);

		top = new MCAModelRenderer(this, "top", 0, 0);
		top.mirror = false;
		top.addBox(0.0F, 0.0F, 0.0F, 30, 26, 1);
		top.setInitialRotationPoint(-15.0F, -30.0F, -15.0F);
		top.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		top.setTextureSize(128, 64);
		parts.put(top.boxName, top);

		bottom = new MCAModelRenderer(this, "bottom", 0, 0);
		bottom.mirror = false;
		bottom.addBox(0.0F, 0.0F, 0.0F, 30, 8, 1);
		bottom.setInitialRotationPoint(-15.0F, -30.0F, 14.0F);
		bottom.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bottom.setTextureSize(128, 64);
		parts.put(bottom.boxName, bottom);

		left = new MCAModelRenderer(this, "left", 0, 0);
		left.mirror = false;
		left.addBox(0.0F, 0.0F, 0.0F, 1, 26, 30);
		left.setInitialRotationPoint(-15.0F, -30.0F, -15.0F);
		left.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		left.setTextureSize(128, 64);
		parts.put(left.boxName, left);

		right = new MCAModelRenderer(this, "right", 0, 0);
		right.mirror = false;
		right.addBox(0.0F, 0.0F, 0.0F, 1, 26, 30);
		right.setInitialRotationPoint(14.0F, -30.0F, -15.0F);
		right.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		right.setTextureSize(128, 64);
		parts.put(right.boxName, right);

		topBase = new MCAModelRenderer(this, "topBase", 0, 0);
		topBase.mirror = false;
		topBase.addBox(0.0F, 0.0F, 0.0F, 30, 2, 30);
		topBase.setInitialRotationPoint(-15.0F, -4.0F, -15.0F);
		topBase.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		topBase.setTextureSize(128, 64);
		parts.put(topBase.boxName, topBase);

		sticks = new MCAModelRenderer(this, "sticks", 0, 0);
		sticks.mirror = false;
		sticks.addBox(-5.0F, 0.0F, 4.5F, 10, 1, 1);
		sticks.setInitialRotationPoint(0.0F, -27.0F, 0.0F);
		sticks.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.17364818F, 0.0F, 0.9848077F)).transpose());
		sticks.setTextureSize(128, 64);
		parts.put(sticks.boxName, sticks);

		insideBottom = new MCAModelRenderer(this, "insideBottom", 0, 0);
		insideBottom.mirror = false;
		insideBottom.addBox(-7.0F, 0.0F, -7.0F, 14, 1, 21);
		insideBottom.setInitialRotationPoint(0.0F, -28.0F, 0.0F);
		insideBottom.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		insideBottom.setTextureSize(128, 64);
		parts.put(insideBottom.boxName, insideBottom);

		eggLT = new MCAModelRenderer(this, "eggLT", 70, 40);
		eggLT.mirror = false;
		eggLT.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
		eggLT.setInitialRotationPoint(-1.6F, -26.0F, -1.6F);
		eggLT.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		eggLT.setTextureSize(128, 64);
		parts.put(eggLT.boxName, eggLT);

		eggRT = new MCAModelRenderer(this, "eggRT", 70, 40);
		eggRT.mirror = false;
		eggRT.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
		eggRT.setInitialRotationPoint(1.6F, -26.0F, -1.6F);
		eggRT.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		eggRT.setTextureSize(128, 64);
		parts.put(eggRT.boxName, eggRT);

		eggLB = new MCAModelRenderer(this, "eggLB", 70, 40);
		eggLB.mirror = false;
		eggLB.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
		eggLB.setInitialRotationPoint(-1.6F, -26.0F, 1.6F);
		eggLB.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		eggLB.setTextureSize(128, 64);
		parts.put(eggLB.boxName, eggLB);

		eggRB = new MCAModelRenderer(this, "eggRB", 70, 40);
		eggRB.mirror = false;
		eggRB.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
		eggRB.setInitialRotationPoint(1.6F, -26.0F, 1.6F);
		eggRB.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		eggRB.setTextureSize(128, 64);
		parts.put(eggRB.boxName, eggRB);

		stick1 = new MCAModelRenderer(this, "stick1", 0, 0);
		stick1.mirror = false;
		stick1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		stick1.setInitialRotationPoint(-3.0F, 0.0F, -6.0F);
		stick1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, -0.62251467F, 0.0F, 0.78260815F)).transpose());
		stick1.setTextureSize(128, 64);
		parts.put(stick1.boxName, stick1);
		sticks.addChild(stick1);

		stick2 = new MCAModelRenderer(this, "stick2", 0, 0);
		stick2.mirror = false;
		stick2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
		stick2.setInitialRotationPoint(-2.0F, 0.0F, 6.0F);
		stick2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, -0.62251467F, 0.0F, 0.78260815F)).transpose());
		stick2.setTextureSize(128, 64);
		parts.put(stick2.boxName, stick2);
		sticks.addChild(stick2);

		leftWall = new MCAModelRenderer(this, "leftWall", 0, 0);
		leftWall.mirror = false;
		leftWall.addBox(-0.5F, 0.0F, 0.0F, 1, 11, 27);
		leftWall.setInitialRotationPoint(-7.0F, 0.0F, -13.0F);
		leftWall.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.38268346F, 0.9238795F)).transpose());
		leftWall.setTextureSize(128, 64);
		parts.put(leftWall.boxName, leftWall);
		insideBottom.addChild(leftWall);

		rightWall = new MCAModelRenderer(this, "rightWall", 0, 0);
		rightWall.mirror = false;
		rightWall.addBox(-0.5F, 0.0F, 0.0F, 1, 11, 27);
		rightWall.setInitialRotationPoint(7.0F, 0.0F, -13.0F);
		rightWall.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.38268346F, 0.9238795F)).transpose());
		rightWall.setTextureSize(128, 64);
		parts.put(rightWall.boxName, rightWall);
		insideBottom.addChild(rightWall);

		topWall = new MCAModelRenderer(this, "topWall", 0, 0);
		topWall.mirror = false;
		topWall.addBox(0.0F, 0.0F, -0.5F, 27, 11, 1);
		topWall.setInitialRotationPoint(-13.0F, 0.0F, -7.0F);
		topWall.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.38268346F, 0.0F, 0.0F, 0.9238795F)).transpose());
		topWall.setTextureSize(128, 64);
		parts.put(topWall.boxName, topWall);
		insideBottom.addChild(topWall);

	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		base.render(par7);
		top.render(par7);
		bottom.render(par7);
		left.render(par7);
		right.render(par7);
		topBase.render(par7);
		sticks.render(par7);
		insideBottom.render(par7);
	}

	public void renderTile(TileEntityNest tile, float f)
	{
		base.render(f);
		top.render(f);
		bottom.render(f);
		left.render(f);
		right.render(f);
		topBase.render(f);
		sticks.render(f);
		insideBottom.render(f);
		if (tile.eggs[0] != null)
		{
			eggLT.render(f);
		}
		if (tile.eggs[1] != null)
		{
			eggRT.render(f);
		}
		if (tile.eggs[2] != null)
		{
			eggLB.render(f);
		}
		if (tile.eggs[3] != null)
		{
			eggRB.render(f);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{}

	public MCAModelRenderer getModelRendererFromName(String name)
	{
		return parts.get(name);
	}
}