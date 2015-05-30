package main.betterbreeds;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import main.betterbreeds.api.APIRegistry;
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
				try
				{
					String s = msg.getStringValue();
					int l = s.lastIndexOf(".");
					if (l < 0) throw new IllegalArgumentException("msg doesn't have a class or method!");
					String c = s.substring(0, l);
					String m = s.substring(l + 1);
					System.out.println(c + ", " + m);
					Class<?> clazz = Class.forName(c);
					Method met = clazz.getMethod(m, new Class[] { APIRegistry.class });
					if (Modifier.isPublic(met.getModifiers()) && Modifier.isStatic(met.getModifiers()) && met.getReturnType().equals(Void.TYPE))
					{
						APIRegistry registry = new APIRegistry();
						met.invoke(null, registry);
						BBAPI.addLists(registry);
					}
					else throw new IllegalArgumentException(c + "." + m + " must be public, static, and must be void!");
				}
				catch (Exception e)
				{
					throw new IllegalArgumentException("Error caught handling message from \"" + msg.getSender() + "\"", e);
				}
			}
			else throw new IllegalArgumentException("msg must have a string message to be processed!");
		}
	}
}