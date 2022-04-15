package createsounds.timbre;

import static createsounds.timbre.LFO.LFOType.*;

//Low Frequency Oscillator
//See LFO tutorials for Serum or Vital for example
public class LFO {
	public enum LFOType {
		TRIG, ENV, OFF; //will it start over on trigger, start and play once through envelope or continue oscillating on its own so all notes it's connected to will be in sync.
	}
	
	private double freq;
	private LFOType type = TRIG;
	private WaveType waveType;
	private Envelope envelope;
	
	public LFO(double freq, WaveType waveType) {
		this.freq = freq;
		this.waveType = waveType;
	}
	
	public void apply(int[] samples, int sampleRate) { //TODO: needs to link somewhere, like amplitude or pitch, and then also say how much value 0 and 1 affect it.
		for (int i = 0; i < samples.length; i++) {
			double time = i / (double) sampleRate;
			samples[i] *= waveType.getValueByFrequencyAndTime(freq, time) / 2.0 + 0.5; //shifting it to from 0 to 1, instead of from -1 to 1.
		}
	}
}
