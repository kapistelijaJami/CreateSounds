package createsounds.timbre;

import static createsounds.Constants.MAX_AMPLITUDE_VALUE;
import createsounds.HelperFunctions;
import createsounds.sound_creation.CreateSounds;

/**
 * See: https://www.youtube.com/watch?v=yi0J9JsRdI4
 * https://blog.landr.com/how-to-use-a-compressor/
 * Pretty good: https://www.uaudio.com/blog/audio-compression-basics/
 */
public class Compressor {
	private double threshold = 0.5; //def: 0.5 (not sure if it's good. Somewhere said -10dB is an example, which would be about 0.32). At what amplitude the compressor starts working, from 0 to 1. 0 being quiet and 1 being max volume.
	private double ratio = 3; //2 is that value of 1 will be scaled to the half point between threshold and 1. 4 would be 1/4 point from threshold to 1. From 1 to infinity.
	private double attack = 1; //in milliseconds. Fast attack is from 20 to 800 microseconds. Slower are from 10 to 100 milliseconds.
	private double release = 50; //in milliseconds. Release times range from 40 milliseconds to couple seconds.
	
	public Compressor() {
		
	}
	
	public Compressor(double threshold, double ratio, double attack, double release) {
		this.threshold = threshold;
		this.ratio = ratio;
		this.attack = attack;
		this.release = release;
	}
	
	public void apply(int[] samples, int sampleRate, float amplitude) {
		double thresholdVal = threshold * MAX_AMPLITUDE_VALUE;
		
		double attackSpeed = (1000 / attack) / sampleRate; //how much will current effect increase per sample if it is over threshold.
		double releaseSpeed = (1000 / release) / sampleRate; //how much will current effect decrease per sample if it is under threshold.
		double currentEffect = 0; //this will keep track of how much the compressor is affecting it now. AttackSpeed and releaseSpeed will change this.
		
		
		for (int i = 0; i < samples.length; i++) {
			int val = samples[i];
			if (Math.abs(val) > thresholdVal) {
				double howMuch = Math.abs(val) - thresholdVal; //how much over threshold this val was
				samples[i] = (int) (thresholdVal + howMuch / adjustedRatio(currentEffect));
				if (val < 0) {
					samples[i] *= -1;
				}
				currentEffect = Math.min(1, currentEffect + attackSpeed);
			} else {
				currentEffect = Math.max(0, currentEffect - releaseSpeed);
			}
		}
		
		
		double extraMultiplier = 1 / (threshold + ((1 - threshold) / ratio));
		for (int i = 0; i < samples.length; i++) { //boosts the audio by the base value, so that input signal of aplitude 1 would after compression go back to 1, but also quieter sounds will be louder.
			samples[i] *= extraMultiplier;
		}
		
		CreateSounds.preventClipping(samples, amplitude);
	}
	
	private double adjustedRatio(double t) {
		return HelperFunctions.lerp(t, 1, ratio);
	}
}
