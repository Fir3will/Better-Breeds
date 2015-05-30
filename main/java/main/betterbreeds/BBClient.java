package main.betterbreeds;

import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.entities.EntityBCow;
import main.betterbreeds.entities.EntityBPig;
import main.betterbreeds.entities.EntityBSheep;
import main.betterbreeds.entities.EntityBWolf;
import main.betterbreeds.render.RenderBChicken;
import main.betterbreeds.render.RenderBCow;
import main.betterbreeds.render.RenderBPig;
import main.betterbreeds.render.RenderBSheep;
import main.betterbreeds.render.RenderBWolf;
import main.betterbreeds.render.TileNestSpecialRenderer;
import main.betterbreeds.tiles.TileEntityNest;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BBClient extends BBCommon
{
	@Override
	public void registerRenderThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBPig.class, new RenderBPig());
		RenderingRegistry.registerEntityRenderingHandler(EntityBCow.class, new RenderBCow());
		RenderingRegistry.registerEntityRenderingHandler(EntityBChicken.class, new RenderBChicken());
		RenderingRegistry.registerEntityRenderingHandler(EntityBSheep.class, new RenderBSheep());
		RenderingRegistry.registerEntityRenderingHandler(EntityBWolf.class, new RenderBWolf());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileNestSpecialRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Init.nest), new TileNestSpecialRenderer());
		//RenderingRegistry.registerEntityRenderingHandler(EntityBHorse.class, new RenderBHorse());
		// BetterBreeds <-- command right click it
	}

	@Override
	public void registerSounds()
	{}
}
