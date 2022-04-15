package createsounds.timbre;

import createsounds.notation.Note;
import createsounds.timbre.effects.Distortion;
import createsounds.timbre.instruments.Instrument;
import java.util.Optional;


/**
 * See these for how to create different sounds
 * https://www.youtube.com/watch?v=NJLIS2MkFe4
 * https://www.youtube.com/watch?v=zuvbfYvwca4
 * Bass:
 * https://www.youtube.com/watch?v=H6i55lXAMh0
 **/
public class Timbre {
	private Envelope envelope; //Basic amplitude envelope for the note.
	private WaveType waveType; //if null, it uses the default wavetype from sheetmusic, otherwise this is used.
	private Distortion distortion;
	private Instrument instrument;
	private double volume = 1; //from 0 up. 1 is 100% volume, but it can be boosted over max amplitude, which effectively makes other sounds quieter, because of CreateSounds.preventClipping()
	
	public Timbre() {
	}
	
	public Timbre(Instrument i) {
		this.instrument = i;
	}
	
	public Timbre(Envelope e, WaveType w, Distortion d, Instrument i, double v) {
		this.envelope = e;
		this.waveType = w;
		this.distortion = d;
		this.instrument = i;
		this.volume = v;
	}
	
	public Optional<Envelope> getEnvelope() {
		return Optional.ofNullable(envelope);
	}
	
	public Envelope getEnvelope(Note note, double BPM) {
		if (envelope == null) {
			createDefaultEnvelope(note.getLengthInSecondsRemoveLetGo(BPM));
		}
		
		return envelope;
	}
	
	public Envelope getEnvelope(int samples, int sampleRate) { //gets duration from sample count.
		if (envelope == null) {
			envelope = Envelopes.createDefault(samples / (double) sampleRate);
		}
		
		return envelope;
	}
	
	public void createDefaultEnvelope(double duration) {
		envelope = Envelopes.createDefault(duration);
	}
	
	public Timbre setEnvelope(Envelope e) {
		this.envelope = e;
		return this;
	}
	
	public boolean hasEnvelope() {
		return envelope != null;
	}
	
	public Timbre setDistortion(Distortion d) {
		this.distortion = Distortion.clone(d);
		return this;
	}
	
	public Distortion getDistortion() {
		return distortion;
	}
	
	public Timbre setInstrument(Instrument i) {
		this.instrument = i; //TODO: should it be Instrument.clone(i)?
		return this;
	}
	
	public Instrument getInstrument() {
		return instrument;
	}
	
	public Timbre setEnvelopeByCopyAndLength(Envelope e, double length) {
		this.envelope = new Envelope(e);
		envelope.changeLength(length);
		return this;
	}
	
	public WaveType getWaveType() {
		return waveType;
	}
	
	public Timbre setWaveType(WaveType waveType) {
		this.waveType = waveType;
		return this;
	}
	
	public Timbre setVolume(double volume) {
		this.volume = volume;
		return this;
	}
	
	public void applyEffects(int[] samples, int sampleRate, Note note, double BPM) {
		//distortion or envelope first?
		if (getDistortion() != null) {
			getDistortion().apply(samples, sampleRate);
		}
		if (instrument != null && instrument.hasSamplesReady()) { //if instrument provided samples already, cant calculate length from note
			getEnvelope(samples.length, sampleRate).apply(samples, sampleRate);
		} else {
			getEnvelope(note, BPM).apply(samples, sampleRate);
		}
		
		applyVolume(samples);
	}
	
	private void applyVolume(int[] samples) {
		for (int i = 0; i < samples.length; i++) {
			samples[i] *= volume;
		}
	}
}
