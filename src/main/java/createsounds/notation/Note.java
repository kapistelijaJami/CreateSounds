package createsounds.notation;

import static createsounds.Constants.MAX_AMPLITUDE_VALUE;
import static createsounds.sound_creation.CreateSounds.noteSampleCache;
import createsounds.timbre.Envelope;
import createsounds.timbre.Timbre;
import createsounds.timbre.WaveType;
import createsounds.timbre.Waves;
import createsounds.timbre.effects.Distortion;
import createsounds.timbre.instruments.Instrument;
import java.util.Arrays;
import java.util.Objects;

public class Note {
	private NoteName noteName;
	private double length; //how long the note is ON. Relative to BPM. 1 is full note, 1/4 is quarter note etc.
	private boolean minimalSilence = false;
	private Timbre timbre = new Timbre();
	
	private Note(NoteName noteName, double length, WaveType... waveType) {
		this.noteName = noteName;
		this.length = length;
		
		this.timbre.setWaveType(waveType.length == 0 ? null : waveType[0]);
	}
	
	public Note(Timbre timbre) {
		this.timbre = timbre;
	}
	
	public Note(Instrument instrument) {
		setInstrument(instrument);
	}
	
	public Note(Instrument instrument, Distortion distortion) {
		setInstrument(instrument);
		setDistortion(distortion);
	}
	
	public static Note clone(Note note) {
		return new Note(note.noteName, note.length, note.timbre.getWaveType());
	}
	
	public Note setWaveType(WaveType waveType) {
		this.timbre.setWaveType(waveType);
		return this;
	}
	
	public WaveType getWaveType() {
		return timbre.getWaveType();
	}
	
	public double getLetGoTime() {
		return minimalSilence ? 0.00023 : 0.069;
	}
	
	public boolean getMinimalSilence() {
		return minimalSilence;
	}
	
	public Note minimalSilence() {
		minimalSilence = true;
		return this;
	}
	
	public Note setEnvelope(Envelope e) {
		this.timbre.setEnvelope(e);
		return this;
	}
	
	public Note setInstrument(Instrument instrument) {
		this.timbre.setInstrument(instrument);
		return this;
	}
	
	public Note setDistortion(Distortion distortion) {
		this.timbre.setDistortion(distortion);
		if (distortion != null && noteName != null)
			this.timbre.getDistortion().frequency = noteName.getHertz();
		return this;
	}
	
	public Note setVolume(double v) {
		timbre.setVolume(v);
		return this;
	}
	
	public Timbre getTimbre() {
		return timbre;
	}
	
	/**
	 * Creates a note with length of how many full notes.
	 * @param note How many full notes it lasts.
	 * @param length 
	 * @param waveType 
	 * @return  
	 */
	public static Note create(NoteName note, double length, WaveType... waveType) {
		return new Note(note, length, waveType);
	}
	public static Note create(NoteName note, WaveType... waveType) {
		return create(note, 1, waveType);
	}
	
	public static Note create2(NoteName note, int length, WaveType... waveType) {
		return new Note(note, length / 2.0, waveType);
	}
	public static Note create2(NoteName note, WaveType... waveType) {
		return create2(note, 1, waveType);
	}
	
	/**
	 * Creates a note with length of how many quarter notes.
	 * @param note How many quarter notes it lasts.
	 * @param length 
	 * @param waveType 
	 * @return  
	 */
	public static Note create4(NoteName note, int length, WaveType... waveType) {
		return new Note(note, length / 4.0, waveType);
	}
	public static Note create4(NoteName note, WaveType... waveType) {
		return create4(note, 1, waveType);
	}
	
	public static Note create8(NoteName note, int length, WaveType... waveType) {
		return new Note(note, length / 8.0, waveType);
	}
	public static Note create8(NoteName note, WaveType... waveType) {
		return create8(note, 1, waveType);
	}
	
	public static Note create16(NoteName note, int length, WaveType... waveType) {
		return new Note(note, length / 16.0, waveType);
	}
	public static Note create16(NoteName note, WaveType... waveType) {
		return create16(note, 1, waveType);
	}
	
	public NoteName getNoteName() {
		return noteName;
	}
	
	public double getHertz() {
		return noteName.getHertz();
	}
	
	public double getLength() {
		return length;
	}
	
	public double getFullLengthInSeconds(double BPM) {
		return convertNoteLengthToSeconds(BPM, length);
	}
	
	public double getLengthInSecondsRemoveLetGo(double BPM) {
		return convertNoteLengthToSeconds(BPM, length) - getLetGoTime();
	}
	
	/**
	 * Converts note length relative to BPM (1 is full note, 1/4 is quarter note) to seconds.
	 * @param BPM
	 * @param seconds
	 * @return 
	 */
	public static double convertNoteLengthToSeconds(double BPM, double seconds) {
		return 60.0 / BPM * 4.0 * seconds;
	}
	
	public static double convertSecondsToNoteLength(double BPM, double seconds) {
		return seconds / (60.0 / BPM * 4.0);
	}
	
	public int[] createSamples(double BPM, int sampleRate) {
		Instrument instrument = timbre.getInstrument();
		if (instrument != null && instrument.hasSamplesReady()) {
			int[] samples = instrument.getSamples();
			timbre.applyEffects(samples, sampleRate, this, BPM);
			return samples;
		}
		
		//check cache
		int[] cache = noteSampleCache.get(this, sampleRate, BPM);
		if (cache != null) {
			timbre.applyEffects(cache, sampleRate, this, BPM);
			return cache;
		}
		
		Envelope e = timbre.getEnvelope(this, BPM);
		int[] samples = createWaveSamples(e.getDuration(), sampleRate, getHertz(), getWaveType());
		
		noteSampleCache.addNote(this, sampleRate, BPM, samples); //TODO: cache is still big for clocks, but if you include all effects in cache key it could be even more useful?
		timbre.applyEffects(samples, sampleRate, this, BPM);
		
		
		return samples;
	}
	
	/**
	 * Creates a wave samples with 16bit values and given duration, sample rate, frequency, amplitude and waveType.
	 * Take duration from envelope, so the release part is there as well.
	 * @param duration
	 * @param sampleRate Samples in second
	 * @param frequency In hertz
	 * @param waveType
	 * @return 
	 */
	public static int[] createWaveSamples(double duration, int sampleRate, double frequency, WaveType waveType) {
		int sampleCount = (int) (duration * sampleRate);
		int[] samples = new int[sampleCount];
		
		for (int i = 0; i < sampleCount; i++) {
			double time = i / (double) sampleRate;
			samples[i] = (int) (waveType.getValueByFrequencyAndTime(frequency, time) * MAX_AMPLITUDE_VALUE);
		}
		
		return samples;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 73 * hash + (int) (Double.doubleToLongBits(this.noteName.getHertz()) ^ (Double.doubleToLongBits(this.noteName.getHertz()) >>> 32));
		hash = 73 * hash + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
		hash = 73 * hash + Objects.hashCode(this.timbre.getWaveType());
		hash = 73 * hash + (this.minimalSilence ? 1 : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final Note other = (Note) obj;
		
		return this.noteName.getHertz() == other.noteName.getHertz() && this.length == other.length && this.timbre.getWaveType() == other.timbre.getWaveType() && this.minimalSilence == other.minimalSilence;
	}
}
