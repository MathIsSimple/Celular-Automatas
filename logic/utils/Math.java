package logic.utils;

import java.awt.Color;
import java.util.Random;

public class Math {
	private static Random random = new Random();
	
	public static float randomNumber(int min, int max) {
		return min + random.nextFloat() * (max - min);
	}
	
	public static Color pixelToRGB(int pixel) {
	    int r = (pixel >> 16) & 0xff;
	    int g = (pixel >> 8) & 0xff;
	    int b = (pixel) & 0xff;
	    return new Color(r, g, b);
	}
	
	public static long map(long x, long in_min, long in_max, long out_min, long out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
