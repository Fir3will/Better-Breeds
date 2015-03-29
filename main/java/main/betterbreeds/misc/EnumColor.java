package main.betterbreeds.misc;

import java.awt.Color;
import main.com.hk.bb.util.JavaHelp;

public enum EnumColor
{
	Red(0xFF0000),
	Blue(0x0000FF),
	White(0xFFFFFF),
	Green(0x00FF00),
	Orange(0xFFCC00),
	Yellow(0xFFFF00),
	Black(0x000000),
	Purple(0xFF00FF);

	public final int colorMultiplier;
	public final int r, g, b;

	private EnumColor(int color)
	{
		final Color c = new Color(color);
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();

		colorMultiplier = color;
	}

	public static EnumColor randomColor()
	{
		return JavaHelp.getRandomElementFrom(values());
	}
}