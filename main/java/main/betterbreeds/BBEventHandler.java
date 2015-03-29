package main.betterbreeds;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BBEventHandler
{
	@SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event)
	{
		if (BetterBreeds.MODID.equals(event.modID)) BetterBreeds.cfg.loadConfig();
	}
}
