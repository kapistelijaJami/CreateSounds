package createsounds.timbre.effects;


import createsounds.Constants;
import static createsounds.Constants.MAX_AMPLITUDE_VALUE;
import createsounds.HelperFunctions;
import createsounds.timbre.WaveType;

public class Distortion {
	public enum DistortionType {
		CLIPPING, HARMONIC_CLIPPING;
		
		public Distortion create() {
			return new Distortion(this);
		}
		
		public Distortion create(double amount, double cap, double mix) {
			return new Distortion(this, amount, cap, mix);
		}
		
		public Distortion create(double amount, double cap, double mix, double frequency) {
			return new Distortion(this, amount, cap, mix, frequency);
		}
	}
	
	private DistortionType type;
	public double amount = 1.5; //Amount of distortion, here everything adds, so it's from 0 to wherever. If it's 0 it doesn't do anything (AKA Drive)
	public double cap = 1; //Where will the clipping happen in amplitude, from 0 to 1. It can be higher, but then it wont clip, and it's just adding harmonics, or volume etc.
	public double mix = 1; //Mix tells how much of the distorted signal is mixed in the original. From 0 to 1. (Also known as Dry/Wet)
	
	public double frequency = 0;
	
	private Distortion(DistortionType type) {
		this.type = type;
	}
	
	private Distortion(DistortionType type, double amount, double cap, double mix) {
		this(type);
		setValues(amount, cap, mix);
	}
	
	private Distortion(DistortionType type, double amount, double cap, double mix, double frequency) {
		this(type);
		setValues(amount, cap, mix);
		this.frequency = frequency;
	}
	
	public static Distortion clone(Distortion distortion) {
		if (distortion == null) {
			return null;
		}
		return new Distortion(distortion.type, distortion.amount, distortion.cap, distortion.mix, distortion.frequency);
	}
	
	public void apply(int[] samples, int sampleRate) {
		switch (type) {
			case CLIPPING:
				applyClippingDistortion(samples);
				break;
			case HARMONIC_CLIPPING:
				applyHarmonicClippingDistortion(samples, sampleRate);
				break;
		}
	}
	
	public void apply(int[] samples, int sampleRate, double amount, double cap, double mix) {
		setValues(amount, cap, mix);
		apply(samples, sampleRate);
	}
	
	private void setValues(double amount, double cap, double mix) {
		this.amount = amount;
		this.cap = cap;
		this.mix = mix;
	}
	
	/**
	 * Multiplies the samples by amount. What goes beyond cap, or max amplitude, will be clipped.
	 * @param samples
	 */
	public void applyClippingDistortion(int[] samples) {
		for (int i = 0; i < samples.length; i++) {
			int val = samples[i];
			
			double res = HelperFunctions.lerp(mix, val, val * (amount + 1)); //mix first, so if distortion went over cap it will still use that value for mix before clipping.
			res = HelperFunctions.clamp(res, (int) (-cap * Constants.MAX_AMPLITUDE_VALUE), (int) (cap * Constants.MAX_AMPLITUDE_VALUE));
			samples[i] = (int) res;
		}
	}
	
	/**
	 * Adds only harmonics to the samples, they are multiplied by amount.
	 * Distorted signal will be mixed with the original.
	 * What goes beyond cap, or max amplitude, will be clipped.
	 * @param samples
	 * @param sampleRate
	 */
	public void applyHarmonicClippingDistortion(int[] samples, int sampleRate) {
		for (int i = 0; i < samples.length; i++) {
			int val = samples[i];
			int distorted = val;
			for (int j = 1; j <= 3; j++) { //adds 3 harmonics, all multiplied by amount so that it probably will go over cap, this affects the sound a lot
				distorted += amount / j * (WaveType.SINE_WAVE.getValueByFrequencyAndTime(frequency * (j + 1), i / (double) sampleRate) * MAX_AMPLITUDE_VALUE);
			}
			
			double res = HelperFunctions.lerp(mix, val, distorted); //mix first, so if distortion went over cap it will still use that value for mix before clipping.
			res = HelperFunctions.clamp(res, (int) (-cap * Constants.MAX_AMPLITUDE_VALUE), (int) (cap * Constants.MAX_AMPLITUDE_VALUE));
			samples[i] = (int) res;
		}
	}
}
