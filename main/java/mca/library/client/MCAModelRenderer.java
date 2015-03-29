package mca.library.client;

import java.nio.FloatBuffer;
import mca.library.common.Utils;
import mca.library.common.math.Matrix4f;
import mca.library.common.math.Quaternion;
import mca.library.common.math.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class MCAModelRenderer extends ModelRenderer
{
	/** Custom version, as parent variable is PRIVATE */
	private int DDStextureOffsetX;
	/** Custom version, as parent variable is PRIVATE */
	private int DDStextureOffsetY;
	/** Custom version, as parent variable is PRIVATE */
	private boolean DDScompiled;
	/** Custom version, as parent variable is PRIVATE */
	private int DDSdisplayList;
	private final Matrix4f rotationMatrix = new Matrix4f();
	/** Previous value of the matrix */
	private Matrix4f prevRotationMatrix = new Matrix4f();
	private float defaultRotationPointX;
	private float defaultRotationPointY;
	private float defaultRotationPointZ;
	private Matrix4f defaultRotationMatrix = new Matrix4f();
	private Quaternion defaultRotationAsQuaternion;

	public MCAModelRenderer(ModelBase par1ModelBase, String par2Str, int xTextureOffset, int yTextureOffset)
	{
		super(par1ModelBase, par2Str);
		setTextureSize(par1ModelBase.textureWidth, par1ModelBase.textureHeight);
		setTextureOffset(xTextureOffset, yTextureOffset);
	}

	@Override
	public ModelRenderer setTextureOffset(int par1, int par2)
	{
		DDStextureOffsetX = par1;
		DDStextureOffsetY = par2;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelRenderer addBox(String par1Str, float par2, float par3, float par4, int par5, int par6, int par7)
	{
		par1Str = boxName + "." + par1Str;
		cubeList.add(new MCAModelBox(this, DDStextureOffsetX, DDStextureOffsetY, par2, par3, par4, par5, par6, par7, 0.0F).func_78244_a(par1Str));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ModelRenderer addBox(float par1, float par2, float par3, int par4, int par5, int par6)
	{
		cubeList.add(new MCAModelBox(this, DDStextureOffsetX, DDStextureOffsetY, par1, par2, par3, par4, par5, par6, 0.0F));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addBox(float par1, float par2, float par3, int par4, int par5, int par6, float par7)
	{
		cubeList.add(new MCAModelBox(this, DDStextureOffsetX, DDStextureOffsetY, par1, par2, par3, par4, par5, par6, par7));
	}

	@Override
	public void render(float par1)
	{
		if (!isHidden)
		{
			if (showModel)
			{
				if (!DDScompiled)
				{
					DDScompileDisplayList(par1);
				}

				//GL11.glPushMatrix();
				GL11.glTranslatef(offsetX, offsetY, offsetZ);
				int i;

				if (rotationMatrix.isEmptyRotationMatrix())
				{
					if (rotationPointX == 0.0F && rotationPointY == 0.0F && rotationPointZ == 0.0F)
					{
						GL11.glCallList(DDSdisplayList);

						if (childModels != null)
						{
							for (i = 0; i < childModels.size(); ++i)
							{
								((ModelRenderer) childModels.get(i)).render(par1);
							}
						}
					}
					else
					{
						//GL11.glPushMatrix();
						GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
						GL11.glCallList(DDSdisplayList);

						if (childModels != null)
						{
							for (i = 0; i < childModels.size(); ++i)
							{
								((ModelRenderer) childModels.get(i)).render(par1);
							}
						}

						GL11.glTranslatef(-rotationPointX * par1, -rotationPointY * par1, -rotationPointZ * par1);
						//GL11.glPopMatrix();
					}
				}
				else
				{
					GL11.glPushMatrix();
					GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
					final FloatBuffer buf = Utils.makeFloatBuffer(rotationMatrix.intoArray());
					GL11.glMultMatrix(buf);

					GL11.glCallList(DDSdisplayList);

					if (childModels != null)
					{
						for (i = 0; i < childModels.size(); ++i)
						{
							((ModelRenderer) childModels.get(i)).render(par1);
						}
					}

					GL11.glPopMatrix();
				}

				GL11.glTranslatef(-offsetX, -offsetY, -offsetZ);
				//GL11.glPopMatrix();

				prevRotationMatrix = rotationMatrix;
			}
		}
	}

	@Override
	public void renderWithRotation(float par1)
	{
		//NOTHING AS WE MUSTN'T USE GL ROTATIONS METHODS
	}

	@Override
	public void postRender(float par1)
	{
		if (!isHidden)
		{
			if (showModel)
			{
				if (!DDScompiled)
				{
					DDScompileDisplayList(par1);
				}

				if (rotationMatrix.equals(prevRotationMatrix))
				{
					if (rotationPointX != 0.0F || rotationPointY != 0.0F || rotationPointZ != 0.0F)
					{
						GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);
					}
				}
				else
				{
					GL11.glTranslatef(rotationPointX * par1, rotationPointY * par1, rotationPointZ * par1);

					GL11.glMultMatrix(FloatBuffer.wrap(rotationMatrix.intoArray()));
				}
			}
		}
	}

	/** Set default rotation point (model with no animations) and set the current rotation point. */
	public void setInitialRotationPoint(float par1, float par2, float par3)
	{
		defaultRotationPointX = par1;
		defaultRotationPointY = par2;
		defaultRotationPointZ = par3;
		setRotationPoint(par1, par2, par3);
	}

	/** Set the rotation point*/
	@Override
	public void setRotationPoint(float par1, float par2, float par3)
	{
		rotationPointX = par1;
		rotationPointY = par2;
		rotationPointZ = par3;
	}

	/** Reset the rotation point to the default values. */
	public void resetRotationPoint()
	{
		rotationPointX = defaultRotationPointX;
		rotationPointY = defaultRotationPointY;
		rotationPointZ = defaultRotationPointZ;
	}

	public Vector3f getPositionAsVector()
	{
		return new Vector3f(rotationPointX, rotationPointY, rotationPointZ);
	}

	/** Set rotation matrix setting also an initial default value (model with no animations). */
	public void setInitialRotationMatrix(Matrix4f matrix)
	{
		defaultRotationMatrix = matrix;
		setRotationMatrix(matrix);
		defaultRotationAsQuaternion = Utils.getQuaternionFromMatrix(rotationMatrix);
	}

	/** Set the rotation matrix values based on the given matrix. */
	public void setRotationMatrix(Matrix4f matrix)
	{
		rotationMatrix.m00 = matrix.m00;
		rotationMatrix.m01 = matrix.m01;
		rotationMatrix.m02 = matrix.m02;
		rotationMatrix.m03 = matrix.m03;
		rotationMatrix.m10 = matrix.m10;
		rotationMatrix.m11 = matrix.m11;
		rotationMatrix.m12 = matrix.m12;
		rotationMatrix.m13 = matrix.m13;
		rotationMatrix.m20 = matrix.m20;
		rotationMatrix.m21 = matrix.m21;
		rotationMatrix.m22 = matrix.m22;
		rotationMatrix.m23 = matrix.m23;
		rotationMatrix.m30 = matrix.m30;
		rotationMatrix.m31 = matrix.m31;
		rotationMatrix.m32 = matrix.m32;
		rotationMatrix.m33 = matrix.m33;
	}

	/** Reset the rotation matrix to the default one. */
	public void resetRotationMatrix()
	{
		setRotationMatrix(defaultRotationMatrix);
	}

	public Matrix4f getRotationMatrix()
	{
		return rotationMatrix;
	}

	public Quaternion getDefaultRotationAsQuaternion()
	{
		return defaultRotationAsQuaternion;
	}

	/**
	 * Compiles a GL display list for this model.
	 * EDITED VERSION BECAUSE OF THE PRIVATE FIELDS
	 */
	public void DDScompileDisplayList(float par1)
	{
		DDSdisplayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(DDSdisplayList, GL11.GL_COMPILE);
		final Tessellator tessellator = Tessellator.instance;

		for (int i = 0; i < cubeList.size(); ++i)
		{
			((ModelBox) cubeList.get(i)).render(tessellator, par1);
		}

		GL11.glEndList();
		DDScompiled = true;
	}
}