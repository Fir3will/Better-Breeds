package main.betterbreeds;

import java.util.ArrayList;
import main.betterbreeds.entities.EntityBChicken;
import main.betterbreeds.entities.EntityBCow;
import main.betterbreeds.entities.EntityBPig;
import main.betterbreeds.entities.EntityBSheep;
import main.betterbreeds.entities.EntityBWolf;
import main.com.hk.bb.util.JavaHelp;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
	public static final String VERSION = "0.2b";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		cfg = new Config(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new BBEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new BBEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new BBEventHandler());
		FMLCommonHandler.instance().bus().register(new BBEventHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Breeds.addSwitcher(EntitySheep.class, EntityBSheep.class, "Sheep");
		Breeds.addSwitcher(EntityCow.class, EntityBCow.class, "Cow");
		Breeds.addSwitcher(EntityChicken.class, EntityBChicken.class, "Chicken");
		Breeds.addSwitcher(EntityWolf.class, EntityBWolf.class, "Wolf");
		Breeds.addSwitcher(EntityPig.class, EntityBPig.class, "Pig");
		cfg.c.save();
		//BBCommon.registerEntity(EntityBHorse.class, "Other Horse");
		// BBClient
		proxy.registerRenderThings();
		proxy.registerSounds();
	}
}