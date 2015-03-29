package main.com.hk.bb.util;

import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Rand
{
	private static final Random rand;
	static
	{
		rand = new Random();
	}

	public static ItemStack getRandomItemStackOf(Item item)
	{
		return new ItemStack(item, getRandomMetadataOf(item), 1);
	}

	public static int getRandomMetadataOf(Item item)
	{
		if (item == null) return 0;
		return nextInt(StackHelper.getMaxMetadataOf(item));
	}

	public static boolean isPercent(float percent)
	{
		return percent > nextInt(100);
	}

	public static boolean nextBoolean()
	{
		return rand.nextBoolean();
	}

	public static byte nextByte()
	{
		return nextBytes(1)[0];
	}

	public static void nextBytes(byte[] bytes)
	{
		rand.nextBytes(bytes);
	}

	public static byte[] nextBytes(int length)
	{
		final byte[] bytes = new byte[length];
		rand.nextBytes(bytes);
		return bytes;
	}

	public static double nextDouble()
	{
		return rand.nextDouble();
	}

	public static float nextFloat()
	{
		return rand.nextFloat();
	}

	public static synchronized double nextGaussian()
	{
		return rand.nextGaussian();
	}

	public static int nextInt()
	{
		return rand.nextInt();
	}

	public static int nextInt(int n)
	{
		return rand.nextInt(n);
	}

	public static ItemStack nextItemStack()
	{
		return getRandomItemStackOf(MPUtil.getRandomItem());
	}

	public static long nextLong()
	{
		return rand.nextLong();
	}

	public static String nextString()
	{
		return nextString(100);
	}

	public static String nextString(int maxLength)
	{
		String chars = "";
		final int len = nextInt(maxLength);
		for (int i = 0; i < len; i++)
		{
			chars += Character.forDigit(nextInt(Character.MAX_RADIX), Character.MAX_RADIX);
		}
		return chars;
	}

	public static void setSeed(long seed)
	{
		rand.setSeed(seed);
	}

	private Rand()
	{}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof Rand;
	}

	@Override
	public int hashCode()
	{
		return this.getClass().getName().length();
	}
}