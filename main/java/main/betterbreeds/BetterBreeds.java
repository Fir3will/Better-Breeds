package main.betterbreeds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.betterbreeds.api.APIRegistry;
import main.betterbreeds.api.BBAPIRegistryHandler;
import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.entities.EntityBCow;
import main.betterbreeds.entities.EntityBPig;
import main.betterbreeds.entities.EntityBSheep;
import main.betterbreeds.entities.EntityBWolf;
import main.betterbreeds.tiles.TileEntityNest;
import main.com.hk.bb.util.JavaHelp;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BetterBreeds.MODID, name = "Better Breeds", version = BetterBreeds.VERSION, guiFactory = "main.betterbreeds.ConfigHandler")
public class BetterBreeds
{
	public static ArrayList<Block> blocks = JavaHelp.newArrayList();
	public static Config cfg;
	@Instance(BetterBreeds.MODID)
	public static BetterBreeds instance;
	public static ArrayList<Item> items = JavaHelp.newArrayList();
	public static final String MODID = "betterbreeds";
	@SidedProxy(clientSide = "main.betterbreeds.BBClient", serverSide = "main.betterbreeds.BBCommon")
	public static BBCommon proxy;
	public static final String VERSION = "0.3.1b";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		cfg = new Config(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new BBEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new BBEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new BBEventHandler());
		FMLCommonHandler.instance().bus().register(new BBEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		new Init();
		GameRegistry.registerBlock(Init.nest, "nest");
		GameRegistry.registerTileEntity(TileEntityNest.class, TileEntityNest.class.getName());
		Breeds.addSwitcher(EntitySheep.class, EntityBSheep.class, "Sheep");
		Breeds.addSwitcher(EntityCow.class, EntityBCow.class, "Cow");
		Breeds.addSwitcher(EntityChicken.class, EntityBChicken.class, "Chicken");
		Breeds.addSwitcher(EntityWolf.class, EntityBWolf.class, "Wolf");
		Breeds.addSwitcher(EntityPig.class, EntityBPig.class, "Pig");
		if (Config.addPregnancy)
		{
			Items.egg.setMaxStackSize(1);
		}
		cfg.c.save();
		// BBClient
		proxy.registerRenderThings();
		proxy.registerSounds();
	}

	@EventHandler
	public void receivedIMCMessage(FMLInterModComms.IMCEvent imc)
	{
		List<IMCMessage> list = imc.getMessages();
		for (int i = 0; i < list.size(); i++)
		{
			IMCMessage msg = list.get(i);
			if (msg.isStringMessage())
			{
				if (msg.key.equalsIgnoreCase("api-registry"))
				{
					try
					{
						try
						{
							Class<?> clazz = Class.forName(msg.getStringValue());
							List<Class<?>> interfaces = Arrays.asList(clazz.getInterfaces());
							if (interfaces.contains(BBAPIRegistryHandler.class))
							{
								BBAPIRegistryHandler handler = (BBAPIRegistryHandler) clazz.newInstance();
								APIRegistry registry = new APIRegistry();
								handler.handlerAPIRegistry(registry);
								BBAPI.addLists(registry);
							}
							else throw new IllegalArgumentException(clazz.getName() + " must implement BBAPIRegistryHandler!");
						}
						catch (ClassNotFoundException e)
						{
							throw new IllegalArgumentException(msg.getStringValue() + " doesn't exist!", e);
						}
					}
					catch (Exception e)
					{
						throw new RuntimeException("Error caught handling message from \"" + msg.getSender() + "\"", e);
					}
				}
				else throw new IllegalArgumentException("msg.key must equal \"api-registry\"");
			}
			else throw new IllegalArgumentException("msg must have a string message to be processed!");
		}
	}
}