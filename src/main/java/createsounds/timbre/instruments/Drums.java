package createsounds.timbre.instruments;

import createsounds.DrumsLoaded;
import java.util.Arrays;

public class Drums extends Instrument {
	public enum DrumType {
		BASS, HIHAT, HIHAT_OPEN, SNARE, CRASH, RIDE;
	}
	
	private final DrumType drumType;
	
	public Drums(DrumType type) {
		drumType = type;
	}
	
	public DrumType getDrumType() {
		return drumType;
	}
	
	@Override
	public boolean hasSamplesReady() {
		return true;
	}
	
	@Override
	public int[] getSamples() {
		switch (drumType) {
			case BASS:
				return Arrays.copyOf(DrumsLoaded.BASS, DrumsLoaded.BASS.length);
			case HIHAT:
				return Arrays.copyOf(DrumsLoaded.HIHAT, DrumsLoaded.HIHAT.length);
			case HIHAT_OPEN:
				return Arrays.copyOf(DrumsLoaded.HIHAT_OPEN, DrumsLoaded.HIHAT_OPEN.length);
			case SNARE:
				return Arrays.copyOf(DrumsLoaded.SNARE, DrumsLoaded.SNARE.length);
			case CRASH:
				return Arrays.copyOf(DrumsLoaded.CRASH, DrumsLoaded.CRASH.length);
			case RIDE:
				return Arrays.copyOf(DrumsLoaded.RIDE, DrumsLoaded.RIDE.length);
		}
		
		return null;
	}
}
