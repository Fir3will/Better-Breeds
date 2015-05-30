package main.betterbreeds;

import java.io.File;
import java.util.List;
import main.com.hk.bb.util.JavaHelp;
import main.com.hk.bb.util.MathHelp;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
	public static boolean addPregnancy;
	public static boolean differentGender;
	public static boolean goodHealth;

	public static int timeTillNextEgg;
	public static int eggHatchingTime;
	public static int differentTextureChance;

	public final Configuration c;
	public final File file;

	public Config(File file)
	{
		this.file = file;
		c = new Configuration(file);
		c.load();
		loadConfig();
		c.save();
	}

	public void loadConfig()
	{
		final List<String> props = JavaHelp.newArrayList();
		Property p;

		p = c.get(Configuration.CATEGORY_GENERAL, "goodHealth", true);
		p.comment = "Do the animals need full health to breed. If set to false, an animal doesn't have to be full health to breed.";
		goodHealth = p.getBoolean(true);
		props.add(p.getName());

		p = c.get(Configuration.CATEGORY_GENERAL, "differentGender", true);
		p.comment = "Do the animals need to be different genders to breed. If set to false, males can breed with males, and vice versa for females.";
		differentGender = p.getBoolean(true);
		props.add(p.getName());

		p = c.get(Configuration.CATEGORY_GENERAL, "differentTextureChance", 50);
		p.comment = "The chance that the child of an animal will have a different texture than it's parents. If set to 100, there's a 100 percent chance the child will have a diffent texture.";
		differentTextureChance = MathHelp.clamp(p.getInt(50), 0, 100);
		props.add(p.getName());

		p = c.get(Configuration.CATEGORY_GENERAL, "addPregnancy", true);
		p.comment = "This will enable the pregnancy aspect of breeding animals. This means that mammals will become pregnant and not immediately have a child. And other animals, such as the chicken will lay an egg and sit on it till it hatches.";
		addPregnancy = p.getBoolean(true);
		props.add(p.getName());

		p = c.get(Configuration.CATEGORY_GENERAL, "eggHatchingTime", 24000, "The amount of ticks a chicken has to incubate the egg before it hatches. 24000 ticks is one Minecraft day. 20 ticks is one second", 240, 2400000);
		eggHatchingTime = p.getInt(24000);
		props.add(p.getName());

		p = c.get(Configuration.CATEGORY_GENERAL, "timeTillNextEgg", 12000, "The amount of ticks till a chicken lays the next egg. 24000 ticks is one Minecraft day. 20 ticks is one second", 120, 1200000);
		p.comment = "The chance that the child of an animal will have a different texture than it's parents. If set to 100, there's a 100 percent chance the child will have a diffent texture.";
		timeTillNextEgg = p.getInt(12000);
		props.add(p.getName());

		c.setCategoryPropertyOrder(Configuration.CATEGORY_GENERAL, props);
	}
}
