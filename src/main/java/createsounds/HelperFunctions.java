package createsounds;

import java.util.ArrayList;
import java.util.List;

public class HelperFunctions {
	
	public static double lerp(double t, double a, double b) {
		return (1 - t) * a + t * b;
	}
	
	public static double cosineInterpolation(double t, double a, double b) {
		double t2 = (1 - Math.cos(t * Math.PI)) / 2;
		return(a * (1 - t2) + b * t2);
	}
	
	//see for more (this is easier): http://paulbourke.net/miscellaneous/interpolation/
	//also: https://www.paulinternet.nl/?page=bicubic
	//cubic interpolation requires two extra points, one for each side
	//here's my desmos, url changes on update: https://www.desmos.com/calculator/vtzzu2vhk7
	public static double cubicInterpolation(double t, double a, double b, double extraLeft, double extraRight) {
		double t2 = t * t;
		double a0 = extraRight - b - extraLeft + a;
		double a1 = extraLeft - a - a0;
		double a2 = b - extraLeft;
		double a3 = a;
		
		return (a0 * t * t2 + a1 * t2 + a2 * t + a3); //a0 * t^3 + a1 * t^2 + a2 * t + a3
	}
	
	public static double clamp(double val, int min, int max) {
		return Math.max(Math.min(val, max), min);
	}
	
	public static <T> List<T> getListFilled(int size, T value) {
		List<T> list = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			list.add(value);
		}
		return list;
	}
	
	public static int[] arrayListToIntArray(ArrayList<Integer> samples) {
		int[] s = new int[samples.size()];
		for (int i = 0; i < samples.size(); i++) {
			s[i] = samples.get(i);
		}
		return s;
	}
	
	public static int[] arrayListIntToShortArray(ArrayList<Integer> samples) {
		int[] s = new int[samples.size()];
		for (int i = 0; i < samples.size(); i++) {
			s[i] = samples.get(i);
		}
		return s;
	}
	
	public static short[] intArrayToShortArray(int[] samples) {
		short[] s = new short[samples.length];
		for (int i = 0; i < samples.length; i++) {
			s[i] = (short) samples[i];
		}
		return s;
	}
	
	public static int[] shortArrayToIntArray(short[] samples) {
		int[] s = new int[samples.length];
		for (int i = 0; i < samples.length; i++) {
			s[i] = samples[i];
		}
		return s;
	}
	
	public static <T> T[] arrayListToArray(ArrayList<T> samples) {
		return (T[]) samples.toArray();
	}
	
	/**
	 * Converts deciBels to volume.
	 * Decibels are in negatives, because 0dB is full volume.
	 * Volume is from 0 to 1.
	 * @param decibel
	 * @return 
	 */
	public static double deciBelToVol(double decibel) {
		return Math.pow(10, decibel / 20.0);
	}
	
	/**
	 * Converts volume to deciBels.
	 * Volume is in percentage (1 = 100%).
	 * From 0 to 1, deciBels will be negative, and above 1 they are positive.
	 * @param vol
	 * @return 
	 */
	public static double volToDeciBel(double vol) {
		return 20f * (float) Math.log10(vol);
	}
}
