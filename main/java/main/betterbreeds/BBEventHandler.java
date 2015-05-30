package main.betterbreeds;

import main.com.hk.bb.util.MPUtil;
import main.com.hk.bb.util.StackHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BBEventHandler
{
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event)
	{
		if (event.itemStack != null && event.itemStack.getItem() == Items.egg)
		{
			final int fertile = event.itemStack.stackTagCompound != null ? event.itemStack.stackTagCompound.getInteger("Fertility") : 0;
			event.toolTip.add("Fertile Egg: " + (fertile == 1 ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + (fertile == 1 ? "Is Fertile!" : fertile == 2 ? "Broken Egg!" : "Not Fertile!") + EnumChatFormatting.RESET);
			if (fertile == 1 && event.itemStack.stackTagCompound.getInteger("Hatching Time") > 0)
			{
				final int time = event.itemStack.stackTagCompound.getInteger("Hatching Time");
				float percent = time / Config.eggHatchingTime;
				event.toolTip.add("Incubation Process: " + time + "/" + Config.eggHatchingTime + "%");
				event.toolTip.add("Incubation Process: " + (int) (percent * 100) + "%");
			}
			if (MPUtil.isClientSide() && net.minecraft.client.gui.GuiScreen.isShiftKeyDown())
			{
				event.toolTip.add("A fertile egg is an egg that");
				event.toolTip.add("was laid by the hen after");
				event.toolTip.add("mating with a cock. If");
				event.toolTip.add("the hen laid this egg without");
				event.toolTip.add("recent mating, it will be");
				event.toolTip.add("infertile and won't hatch!");
				event.toolTip.add("Throwing the egg will cause");
				event.toolTip.add("the egg to break!");
			}
			else if (MPUtil.isClientSide() && !net.minecraft.client.gui.GuiScreen.isShiftKeyDown())
			{
				event.toolTip.add(EnumChatFormatting.GRAY + "<Hold Shift>" + EnumChatFormatting.RESET);
			}
		}
	}

	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event)
	{
		if (BetterBreeds.MODID.equals(event.modID))
		{
			BetterBreeds.cfg.loadConfig();
			BetterBreeds.cfg.c.save();
		}
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityItem)
		{
			final ItemStack stack = ((EntityItem) event.entity).getEntityItem();
			if (stack.getItem() == Items.egg)
			{
				StackHelper.newNBT(stack).setInteger("Fertility", 2);
			}
		}
	}
}
