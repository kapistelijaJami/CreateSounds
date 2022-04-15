package createsounds.sound_creation;

import createsounds.notation.Note;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NoteSampleCache {
	private class Key {
		private Note note;
		private int sampleRate;
		private double BPM;
		
		public Key(Note note, int sampleRate, double BPM) {
			this.note = note;
			this.sampleRate = sampleRate;
			this.BPM = BPM;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 53 * hash + Objects.hashCode(this.note);
			hash = 53 * hash + this.sampleRate;
			hash = 53 * hash + Objects.hashCode(this.BPM);
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
			
			final Key other = (Key) obj;
			
			return this.note.equals(other.note) && this.sampleRate == other.sampleRate && this.BPM == other.BPM;
		}
	}
	
	private final Map<Key, int[]> cache = new HashMap<>();
	
	public void addNote(Note note, int sampleRate, double BPM, int[] samples) {
		Key key = new Key(note, sampleRate, BPM);
		samples = Arrays.copyOf(samples, samples.length);
		cache.put(key, samples);
	}
	
	public int[] get(Note note, int sampleRate, double BPM) {
		Key key = new Key(note, sampleRate, BPM);
		
		int[] samples = cache.get(key);
		if (samples != null) {
			return Arrays.copyOf(samples, samples.length);
		}
		
		return null;
	}
}
