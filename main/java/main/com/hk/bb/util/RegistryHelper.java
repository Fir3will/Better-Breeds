package main.com.hk.bb.util;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	private static final HashMap<String, String> replacements = JavaHelp.newHashMap();

	public static void addReplacement(String name, String replacement)
	{
		if (!replacements.containsKey(name))
		{
			replacements.put(name, replacement);
		}
	}

	public static void register(Object obj)
	{
		if (obj instanceof Block)
		{
			final Block block = (Block) obj;
			final String loc = block.getLocalizedName();
			final String oreDict = Character.toLowerCase(loc.charAt(0)) + loc.substring(1).replaceAll(" ", "");
			GameRegistry.registerBlock(block, block.getUnlocalizedName());
			setupOreRegistry(oreDict, obj);
		}
		else if (obj instanceof Item)
		{
			final Item item = (Item) obj;
			final String unLoc = item.getUnlocalizedName();
			final String oreDict = Character.toLowerCase(unLoc.charAt(0)) + unLoc.substring(1).replaceAll(" ", "");
			GameRegistry.registerItem(item, unLoc);
			setupOreRegistry(oreDict, obj);
		}
		else throw new IllegalArgumentException("Object isn't an Item or a Block... This is BAD! Object Class: " + obj.getClass().getName());
	}

	private static String checkString(String oreDict, Object obj)
	{
		if (oreDict.contains("Ingot"))
		{
			oreDict = ("ingot" + Character.toUpperCase(oreDict.charAt(0)) + oreDict.substring(1)).replaceAll("Ingot", "");
		}
		if (oreDict.contains("Ore"))
		{
			oreDict = ("ore" + Character.toUpperCase(oreDict.charAt(0)) + oreDict.substring(1)).replaceAll("Ore", "");
		}
		if (replacements.containsKey(oreDict))
		{
			oreDict = replacements.get(oreDict);
		}
		return oreDict;
	}

	private static void setupOreRegistry(String oreDict, Object ore)
	{
		if (ore.getClass().isAnnotationPresent(OreDict.class))
		{
			if (ore.getClass().getAnnotation(OreDict.class).bool())
			{
				register(oreDict, ore);
			}
			for (final String s : ore.getClass().getAnnotation(OreDict.class).values())
			{
				register(s, ore);
			}
		}
		else
		{
			register(oreDict, ore);
		}
	}

	private static void register(String name, Object ore)
	{
		name = checkString(name, ore);
		if (ore instanceof Item)
		{
			OreDictionary.registerOre(name, (Item) ore);
		}
		if (ore instanceof Block)
		{
			OreDictionary.registerOre(checkString(name, ore), (Block) ore);
		}
	}

	@Target(value = { CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface OreDict
	{
		String[] values();

		boolean bool() default false;
	}
}
