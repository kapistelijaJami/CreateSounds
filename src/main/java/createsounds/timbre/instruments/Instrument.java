package createsounds.timbre.instruments;

public abstract class Instrument {
	public abstract boolean hasSamplesReady();
	public int[] getSamples() { return null; } //if hasSamplesReady() returns true, this has to be implemented
}
