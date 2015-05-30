package main.betterbreeds.blocks;

import main.betterbreeds.BetterBreeds;
import main.betterbreeds.GuiHandler.IDs;
import main.betterbreeds.Init;
import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.tiles.TileEntityNest;
import main.com.hk.bb.util.MPUtil;
import main.com.hk.bb.util.Rand;
import main.com.hk.bb.util.Vector3I;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockNest extends BlockContainer implements IDs
{
	public BlockNest()
	{
		super(Material.wood);
		setBlockName("nest");
		setBlockTextureName("planks_oak");
		setBlockBounds(0.05F, 0, 0.05F, 0.95F, 1, 0.95F);
		setCreativeTab(Init.betterBreedsTab);
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sideX, float sideY, float sideZ)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() == Items.arrow)
		{
			final TileEntityNest nest = (TileEntityNest) world.getTileEntity(x, y, z);
			if (nest.chicken == null)
			{
				final EntityBChicken chicken = new EntityBChicken(world);
				chicken.onSpawnWithEgg(null);
				chicken.setFemale(true);
				chicken.setPosition(x + 0.5D, y, z + 0.5D);
				chicken.nest = new Vector3I(x, y, z);
				if (MPUtil.isServerSide())
				{
					world.spawnEntityInWorld(chicken);
				}
				nest.chicken = chicken;
			}
		}
		else if (!player.isSneaking())
		{
			player.openGui(BetterBreeds.instance, TILE_NEST, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		setOrientation(world, x, y, z);
	}

	private void setOrientation(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			final Block block = world.getBlock(x, y, z - 1);
			final Block block1 = world.getBlock(x, y, z + 1);
			final Block block2 = world.getBlock(x - 1, y, z);
			final Block block3 = world.getBlock(x + 1, y, z);
			byte b0 = 2;

			if (block.func_149730_j() && !block1.func_149730_j())
			{
				b0 = 2;
			}
			if (block1.func_149730_j() && !block.func_149730_j())
			{
				b0 = 3;
			}
			if (block2.func_149730_j() && !block3.func_149730_j())
			{
				b0 = 0;
			}
			if (block3.func_149730_j() && !block2.func_149730_j())
			{
				b0 = 1;
			}
			if (MPUtil.isServerSide())
			{
				System.out.println("b0 == " + b0);
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (MPUtil.isServerSide())
		{
			System.out.println("1, l == " + l);
		}
		l = l == 0 ? 2 : l == 2 ? 0 : l;
		if (MPUtil.isServerSide())
		{
			System.out.println("2, l == " + l);
		}
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int stuff)
	{
		final TileEntityNest nest = (TileEntityNest) world.getTileEntity(x, y, z);

		if (nest != null)
		{
			nest.broke();
			for (int i1 = 0; i1 < nest.getSizeInventory(); ++i1)
			{
				final ItemStack itemstack = nest.getStackInSlot(i1);

				if (itemstack != null)
				{
					final float f = Rand.nextFloat() * 0.8F + 0.1F;
					final float f1 = Rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (final float f2 = Rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
					{
						int j1 = Rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize)
						{
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						final float f3 = 0.05F;
						entityitem.motionX = (float) Rand.nextGaussian() * f3;
						entityitem.motionY = (float) Rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) Rand.nextGaussian() * f3;

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, stuff);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityNest();
	}
}
