package createsounds.timbre;

import static createsounds.Constants.*;


public class Envelopes {
	public static Envelope createNull(double length) {
		return Envelope.getByAHDRL(0, 0, 0, 1, 0, length);
	}
	
	public static Envelope createDefault(double length) {
		return Envelope.getByAHDRL(DEFAULT_ATTACK, DEFAULT_HOLD, DEFAULT_DECAY, DEFAULT_SUSTAIN, DEFAULT_RELEASE, length);
	}
	
	public static Envelope createDefaultWithRelease(double length, double release) {
		return Envelope.getByAHDRL(DEFAULT_ATTACK, DEFAULT_HOLD, DEFAULT_DECAY, DEFAULT_SUSTAIN, release, length);
	}
	
	//if notes go on top of each other because of release, the intersection will be loud. That's why we take min of release and letGo, so notes cant intersect.
	//shortens the length by letGo as well, so there's a gap between notes. You could just remove it from length before calling this method though.
	//NOT SURE IF THIS IS USEFUL, should just take the default release, otherwise it doesn't do anything
	public static Envelope createDefaultWithLetGoTime(double length, double letGo) {
		return Envelope.getByAHDRL(DEFAULT_ATTACK, DEFAULT_HOLD, DEFAULT_DECAY, DEFAULT_SUSTAIN, Math.min(DEFAULT_RELEASE, letGo), length - letGo);
	}
	
	public static Envelope createWithValues(double length, double attack, double hold, double decay, double sustainValue, double release) {
		return Envelope.getByAHDRL(attack, hold, decay, sustainValue, release, length);
	}
	
	public static Envelope createWithValuesWithoutLength(double attack, double hold, double decay, double sustainValue, double release) {
		return Envelope.getByAHDRWithoutLength(attack, hold, decay, sustainValue, release);
	}
	
	public static Envelope createFirstTest(double length) {
		return Envelope.getByAHDRL(0.25, 0, 0.2, 0.7, 0.2, length); //def: 0.25, 0.2, 0.7, 0.2, probably too slow for most situations
	}
}
