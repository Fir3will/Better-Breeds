/**
 * This package contains all
 * the necessary classes that
 * you will need to add compatibility
 * with your mod! If you feel that
 * there's anything missing, feel
 * free to let me know! It's very
 * important that you tell me what's
 * missing so I can fix it! 
 * 
 * <p>Thanks for using my modding
 * API, it's my first API I created!</p>
 * 
 * <p>To start modding with the API
 * send a message to the "betterbreeds"
 * mod through this:</p>
 * <pre>
 * 
 * // Mod's Post Init method
 * public void postInit(FMLPostInitializationEvent event)
 * {
 * 	FMLInterModComms.sendMessage("betterbreeds", "api-registry", "your.mod.[class that implements BBAPIRegistryHandler]");
 * }
 * </pre>
 * This will send a message to Better Breeds
 * that will register the handlers to the mod's
 * respective actions!
 */
@API(apiVersion = "1.7.10-1.0", owner = "betterbreeds", provides = "BetterBreeds|API")
package main.betterbreeds.api;

import cpw.mods.fml.common.API;

