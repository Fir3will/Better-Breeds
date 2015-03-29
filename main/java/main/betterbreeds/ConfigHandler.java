package main.betterbreeds;

import java.util.List;
import java.util.Set;
import main.com.hk.bb.util.JavaHelp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class ConfigHandler implements IModGuiFactory
{
	@Override
	public void initialize(Minecraft minecraftInstance)
	{}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass()
	{
		return BBConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
	{
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static List<IConfigElement> getConfigElements()
	{
		final List<IConfigElement> list = JavaHelp.newArrayList();
		list.add(new DummyCategoryElement<Object>("General", "General", GeneralOptions.class));
		list.add(new DummyCategoryElement<Object>("Animal Options", "Animal Options", MobOptions.class));
		return list;
	}

	public static class BBConfig extends GuiConfig
	{
		public BBConfig(GuiScreen parentScreen)
		{
			super(parentScreen, getConfigElements(), BetterBreeds.MODID, false, true, "Better Breeds Config");
		}
	}

	public static class GeneralOptions extends CategoryEntry
	{
		public GeneralOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen()
		{
			return new GuiConfig(owningScreen, new ConfigElement<Object>(BetterBreeds.cfg.c.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), owningScreen.modID, Configuration.CATEGORY_GENERAL, configElement.requiresWorldRestart() || owningScreen.allRequireWorldRestart, configElement.requiresMcRestart() || owningScreen.allRequireMcRestart, GuiConfig.getAbridgedConfigPath(BetterBreeds.cfg.c.toString()));
		}
	}

	public static class MobOptions extends CategoryEntry
	{
		public MobOptions(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> prop)
		{
			super(owningScreen, owningEntryList, prop);
		}

		@Override
		protected GuiScreen buildChildScreen()
		{
			return new GuiConfig(owningScreen, new ConfigElement<Object>(BetterBreeds.cfg.c.getCategory("mobs")).getChildElements(), owningScreen.modID, "mobs", configElement.requiresWorldRestart() || owningScreen.allRequireWorldRestart, configElement.requiresMcRestart() || owningScreen.allRequireMcRestart, GuiConfig.getAbridgedConfigPath(BetterBreeds.cfg.c.toString()));
		}
	}
}
