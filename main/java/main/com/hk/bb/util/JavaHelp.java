package main.com.hk.bb.util;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaHelp
{
	private JavaHelp()
	{}

	public static <T> ArrayList<T> newArrayList()
	{
		return new ArrayList<T>();
	}

	public static <T, V> HashMap<T, V> newHashMap()
	{
		return new HashMap<T, V>();
	}

	public static <T> T getRandomElementFrom(T... objs)
	{
		if (objs != null && objs.length > 0) return objs[Rand.nextInt(objs.length - 1)];
		return null;
	}

	public static boolean isVowel(char ch)
	{
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
	}

	public static boolean startsWithVowel(String string)
	{
		return string.length() > 0 && isVowel(string.charAt(0));
	}

	public static <T> String toString(T[] objects, StringExtractor<T> e)
	{
		String s = objects.getClass().getSimpleName() + "(";
		for (final T obj : objects)
		{
			s += e.toString(obj) + ", ";
		}
		s = s.endsWith(", ") ? s.substring(0, s.lastIndexOf(", ")) : s;
		s += ")";
		return s;
	}

	public static interface StringExtractor<T>
	{
		public String toString(T obj);
	}
}
