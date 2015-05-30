package main.anothermod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AnotherMod.MODID, name = "Another Mod", version = AnotherMod.VERSION)
public class AnotherMod
{
	public static final String MODID = "anothermod";
	public static final String VERSION = "1.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		FMLInterModComms.sendMessage("betterbreeds", "api-registry", "main.anothermod.BBAPIHandler.handleBBAPI");
	}
}
