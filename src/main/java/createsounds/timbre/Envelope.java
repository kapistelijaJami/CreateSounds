package createsounds.timbre;

import createsounds.HelperFunctions;

/**
 * https://en.wikipedia.org/wiki/Envelope_(music)
 * Goes linearly to 1 during the attack phase.
 * Then goes linearly to sustainValue during decay.
 * Then stays there during sustain.
 * Then goes linearly to 0 during release.
 */
public class Envelope {
	//these are all durations in seconds (total length is their sum)
	private double attack;
	private double hold;
	private double decay;
	private double sustain = -1;
	private double release;
	private double sustainValue = 0.6;
	private double noteLength;
	
	private double releaseStartedValue = 0;
	
	private Envelope(double attack, double hold, double decay, double sustain, double release, double sustainValue) {
		this.attack = attack;
		this.hold = hold;
		this.decay = decay;
		this.sustain = sustain;
		this.release = release;
		this.sustainValue = sustainValue;
	}
	
	//without knowing the note length, it will be calculated after
	private Envelope(double attack, double hold, double decay, double release, double sustainValue) {
		this(attack, hold, decay, -1, release, sustainValue);
	}
	
	//copy constructor
	public Envelope(Envelope e) {
		this.attack = e.attack;
		this.hold = e.hold;
		this.decay = e.decay;
		this.sustain = e.sustain;
		this.release = e.release;
		this.sustainValue = e.sustainValue;
		this.noteLength = e.noteLength;
	}
	
	//if you want to force a sustain duration:
	/*protected static Envelope getByAHDSR(double attack, double hold, double decay, double sustain, double release, double sustainValue) {
		return new Envelope(attack, hold, decay, sustain, release, sustainValue);
	}*/
	
	protected static Envelope getByAHDRL(double attack, double hold, double decay, double sustainValue, double release, double length) {
		Envelope e = new Envelope(attack, hold, decay, release, sustainValue);
		e.changeLength(length);
		
		return e;
	}
	
	protected static Envelope getByAHDRWithoutLength(double attack, double hold, double decay, double sustainValue, double release) {
		return new Envelope(attack, hold, decay, release, sustainValue);
	}
	
	public void changeLength(double length) {
		sustain = calculateSustainLeft(attack, hold, decay, length);
		this.noteLength = length;
		
		if (sustain < 0) {
			sustain = 0;
		}
	}
	
	public void apply(int[] samples, int sampleRate) {
		for (int i = 0; i < samples.length; i++) {
			samples[i] *= getEnvelopeMultiplier(i / (double) sampleRate);
		}
	}
	
	/**
	 * Gets the envelope multiplier by time.
	 * @param time In seconds.
	 * @return 
	 */
	public double getEnvelopeMultiplier(double time) {
		if (time < 0) {
			return 0;
		}
		
		if (time >= noteLength) { //RELEASE if note was shorter than envelope
			double t = (time - noteLength) / release;
			if (t >= 1) {
				return 0;
			}
			return HelperFunctions.cosineInterpolation(t, releaseStartedValue, 0);
		}
		
		if (time < attack) { //ATTACK
			double t = time / attack;
			//double val = HelperFunctions.cosineInterpolation(t, 0, 1);
			double val = HelperFunctions.lerp(t, 0, 1); //attack might be better as normal lerp
			releaseStartedValue = val;
			return val;
		}
		time -= attack;
		if (time < hold) { //HOLD
			releaseStartedValue = 1;
			return 1;
		}
		time -= hold;
		if (time < decay) { //DECAY
			double t = time / decay;
			double val = HelperFunctions.cosineInterpolation(t, 1, sustainValue);
			//TODO: tried to get the curve going down first, but couldn't get it with just cosine, or cubic.
			//could just create another point somewhere between 1 and sustainValue, thought cosine for that, but it creates a leveling at the point.
			//double val = HelperFunctions.cubicInterpolation(t, 1, sustainValue, 2, 1.1);
			releaseStartedValue = val;
			return val;
		}
		time -= decay;
		if (time < sustain) { //SUSTAIN
			releaseStartedValue = sustainValue;
			return sustainValue;
		}
		time -= sustain;
		if (time < release) { //RELEASE because of envelope
			double t = time / release;
			if (t >= 1) {
				return 0;
			}
			return HelperFunctions.cosineInterpolation(t, releaseStartedValue, 0);
		}
		
		return 0;
	}
	
	private static double calculateSustainLeft(double attack, double hold, double decay, double length) {
		return length - (attack + hold + decay);
	}
	
	public double getNoteLength() {
		return noteLength;
	}
	
	public double getDuration() {
		return Math.min(noteLength + release, attack + hold + decay + sustain + release);
	}
}
