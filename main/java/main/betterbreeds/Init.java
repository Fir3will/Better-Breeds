package main.betterbreeds;

import main.betterbreeds.blocks.BlockNest;
import main.com.hk.bb.util.MPUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Init
{
	public static CreativeTabs betterBreedsTab = new CreativeTabs("better.breeds.tab")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(nest);
		}
	};

	public static Block nest = new BlockNest();

	public Init()
	{}

	public static void addRecipes()
	{
		MPUtil.addRecipe(new ItemStack(nest), "WDW", "SWS", " S ", 'S', Items.stick, 'W', Items.wheat, 'D', Blocks.wool);
	}
}
