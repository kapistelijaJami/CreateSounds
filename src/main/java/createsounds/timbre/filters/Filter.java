package createsounds.timbre.filters;

import createsounds.sound_creation.CreateSounds;

/**
 * See these:
 * implementation:
 * https://ethanwiner.com/eq-dsp.htm
 * https://webaudio.github.io/Audio-EQ-Cookbook/audio-eq-cookbook.html
 * 
 * information:
 * https://www.youtube.com/watch?v=46Ep4ecxG2I
 */
public abstract class Filter {
	protected double w0, alpha;
	protected double a0, a1, a2, b0, b1, b2;
	
	/**
	 * Creates a EQ filter.
	 * @param cutOff Where the filter starts working in frequency. From 0 to 1.
	 * @param Q Resonance. How high of a peak before/at the cutoff point.
	 * @param sampleRate 
	 */
	protected Filter(double cutOff, double Q, double sampleRate) {
		w0 = 2 * Math.PI * cutOff / sampleRate;
		alpha = Math.sin(w0) / (2 * Q);
	}
	
	public void apply(int[] samples, float amplitude) {
		int[] prevSample = new int[3];
		
		prevSample[1] = samples[0];
		prevSample[2] = samples[1];
		
		for (int i = 2; i < samples.length; i++) {
			prevSample[0] = prevSample[1];
			prevSample[1] = prevSample[2];
			prevSample[2] = samples[i];
			
			applyToValue(prevSample, samples, i);
		}
		
		//CreateSounds.preventClipping(samples, 1); //TODO: see if this is even needed (I removed it for now) (there's prevent clipping at the end anyway), also changed to 1 instead of amplitude, see if that is fine. (at least some times seems fine, can go louder than amplitude, but does it matter, cause it's usually just some frequencies?)
	}
	
	private void applyToValue(int[] prevSample, int[] samples, int i) {
		samples[i] = (int) (b0 / a0 * prevSample[2]
				+ b1 / a0 * prevSample[1]
				+ b2 / a0 * prevSample[0]
				- a1 / a0 * samples[i - 1]
				- a2 / a0 * samples[i - 2]);
	}
}
