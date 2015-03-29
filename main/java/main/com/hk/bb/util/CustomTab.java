package main.com.hk.bb.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomTab extends CreativeTabs
{
	private static int ID = 1;
	private final int id;

	public CustomTab(String label)
	{
		super(label);
		id = ID++;
	}

	@Override
	public Item getTabIconItem()
	{
		return MPUtil.getRandomEBItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		String str = "Tab";
		switch (id)
		{
			case 1:
				str = "Main Blocks";
				break;
			case 2:
				str = "Main Items";
				break;
			case 3:
				str = "Tools";
				break;
			case 4:
				str = "Fake Floors";
				break;
			case 5:
				str = "Lighted Blocks";
				break;
		}
		return "EB " + str;
	}
}
