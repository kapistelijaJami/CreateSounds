package createsounds.notation;

import createsounds.timbre.WaveType;
import static createsounds.notation.NoteName.*;
import createsounds.sound_creation.CreateSounds;
import createsounds.timbre.Envelope;
import createsounds.timbre.Envelopes;
import createsounds.timbre.effects.Distortion;
import createsounds.timbre.effects.Distortion.DistortionType;
import java.util.ArrayList;
import java.util.Arrays;

public class SheetMusic {
	public class NoteStart implements Comparable<NoteStart> {
		private final double time; //Relative to BPM, 1 is full note. 1/4 is quarter note away from start etc.
		private final ArrayList<Note> notes;
		
		public NoteStart(double time) {
			notes = new ArrayList<>();
			this.time = time;
		}
		
		public NoteStart add(Note note) {
			notes.add(note);
			return this;
		}
		
		public NoteStart addAll(Note[] notes) {
			this.notes.addAll(Arrays.asList(notes));
			return this;
		}
		
		public NoteStart addAll(ArrayList<Note> notes) {
			this.notes.addAll(notes);
			return this;
		}
		
		public double getTime() {
			return time;
		}
		
		public double getTimeInSeconds(double BPM) {
			return Note.convertNoteLengthToSeconds(BPM, time);
		}
		
		public ArrayList<Note> getNotes() {
			return notes;
		}
		
		@Override
		public int compareTo(NoteStart o) {
			return (time < o.time) ? -1 : ((time == o.time) ? 0 : 1);
		}
	}
	
	public ArrayList<NoteStart> noteStarts;
	public double BPM = 90;
	public int currentWritingTime = 0; //in 16th notes
	public WaveType defaultWaveType = WaveType.SINE_WAVE;
	
	public SheetMusic(double BPM) {
		noteStarts = new ArrayList<>();
		this.BPM = BPM;
	}
	
	public void setAllNotesWaveType(WaveType waveType) {
		for (NoteStart start : noteStarts) {
			for (Note note : start.notes) {
				note.setWaveType(waveType);
			}
		}
	}
	
	public void duplicateAllNotesAsAWaveType(WaveType waveType) {
		duplicateAllNotesAsAWaveType(waveType, 1);
	}
	
	public void duplicateAllNotesAsAWaveType(WaveType waveType, int howMany) {
		ArrayList<Note> newNotes = new ArrayList<>();
		for (NoteStart start : noteStarts) {
			newNotes.clear();
			for (Note note : start.notes) {
				for (int i = 0; i < howMany; i++) {
					newNotes.add(Note.clone(note).setWaveType(waveType));
				}
			}
			start.addAll(newNotes);
		}
	}
	
	public void setAllNotesEnvelope(Envelope e) {
		setAllNotesEnvelope(e, false);
	}
	
	public void setAllNotesEnvelope(Envelope e, boolean force) {
		for (NoteStart start : noteStarts) {
			for (Note note : start.notes) {
				if (force || !note.getTimbre().hasEnvelope()) {
					note.getTimbre().setEnvelopeByCopyAndLength(e, note.getLengthInSecondsRemoveLetGo(BPM));
				}
			}
		}
	}
	
	public void setAllNotesDistortion(Distortion d) {
		setAllNotesDistortion(d, false);
	}
	
	public void setAllNotesDistortion(Distortion d, boolean force) {
		for (NoteStart start : noteStarts) {
			for (Note note : start.notes) {
				if (force || note.getTimbre().getDistortion() == null) {
					note.setDistortion(Distortion.clone(d));
				}
			}
		}
	}
	
	public void setAllNotesVolume(double volume) {
		for (NoteStart start : noteStarts) {
			for (Note note : start.notes) {
				note.setVolume(volume);
			}
		}
	}
	
	public static SheetMusic createTest() {
		/*SheetMusic s = createClocks();
		s.defaultWaveType = WaveType.SINE_WAVE;
		return s;*/
		SheetMusic s = new SheetMusic(60);
		s.currentWritingTime = 0;
		
		//s.addNotes(2, Note.create(100, 2), Note.create(200, 2), Note.create(400, 2, WaveType.TRIANGLE_WAVE), Note.create(500, 2, WaveType.SQUARE_WAVE));
		
		int hz = 1;
		/*s.addNotes(2, Note.create(hz, 2, WaveType.SQUARE_WAVE), Note.create(hz, 2, WaveType.TRIANGLE_WAVE));
		s.addNotes(2, Note.create(hz, 2, WaveType.SQUARE_WAVE), Note.create(hz, 2, WaveType.TRIANGLE_WAVE), Note.create(hz, 2, WaveType.SINE_WAVE));
		s.addNotes(2, Note.create(hz, 2, WaveType.SINE_WAVE), Note.create(hz, 2, WaveType.TRIANGLE_WAVE));
		s.addNotes(2, Note.create(hz, 2, WaveType.SINE_WAVE));*/
		//s.addNotes(2, Note.create(hz, 2, WaveType.PIANO_APPROX3_WAVE));
		//s.addNotes(2, Note.create(hz, 2, WaveType.SINE_WAVE));
		//s.addNotes(2, Note.create(hz, 2, WaveType.TRIANGLE_WAVE), Note.create(hz, 2, WaveType.SQUARE_WAVE), Note.create(hz, 2, WaveType.SAWTOOTH_WAVE));
		
		/*s.addNotes(2, Note.create(C3, 2, WaveType.SINE_WAVE), Note.create(C3, 2, WaveType.SINE_WAVE), Note.create(C3, 2, WaveType.SINE_WAVE),
				Note.create(C4, 2, WaveType.SINE_WAVE), Note.create(E4, 2, WaveType.SINE_WAVE), Note.create(G4, 2, WaveType.SINE_WAVE));*/
		
		/*s.addNotes(Note.create(hz, 2, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(C, 1, WaveType.SINE_WAVE));*/
		
		//s.addNotes1(Note.create(hz, WaveType.SINE_WAVE));
		//s.addNotes1(Note.create(hz, WaveType.SINE_WAVE));
		//s.addNotes1(Note.create(C, WaveType.NOISE));
		/*s.addNotes1(2, Note.create(C, 2, WaveType.HARMONICS3));
		
		s.addNotes2(Note.create2(C, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(E, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(G, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(G, 3, WaveType.SINE_WAVE), Note.create(E, WaveType.SINE_WAVE), Note.create2(C, WaveType.SINE_WAVE));*/
		/*s.addNotes1(Note.create(C, 3, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(C, 2, WaveType.SINE_WAVE));
		s.addNotes1(Note.create(C, 1, WaveType.SINE_WAVE));*/
		
		//s.addNotes1(Note.create(C, 1, WaveType.RAMP_WAVE));
		//s.addNotes1(Note.create(C, 1, WaveType.GUITAR));
		
		//808 bass testing:
		/*Note n = Note.create(NoteName.G1, WaveType.SINE_WAVE);
		double seconds = n.getLengthInSeconds(s.BPM);
		n.timbre.setEnvelope(Envelopes.createWithValues(seconds, 0.01, 0.5, 1.35, 0, 0.015));
		s.addNotes1(n);
		
		n = Note.create2(NoteName.G1, WaveType.SINE_WAVE);
		seconds = n.getLengthInSeconds(s.BPM);
		n.timbre.setEnvelope(Envelopes.createWithValues(seconds, 0.01, 0.2, 1.35, 0.6, 1));
		s.addNotes1(n);
		
		//this should modify the master tune for 808 to create a kick sound at the start
		//(modify tune for the note I think, with higher envelope value meaning more higher pitch, 0 envelope should be default I think)
		n = Note.create8(NoteName.F3, WaveType.SINE_WAVE);
		seconds = n.getLengthInSeconds(s.BPM);
		n.timbre.setEnvelope(Envelopes.createWithValues(seconds, 0.0005, 0, 0.034, 0, 0.015));
		s.addNotes8(n);*/
		
		//Envelope testing:
		/*Note n = Note.create(NoteName.G3, WaveType.SINE_WAVE);
		double seconds = n.getLengthInSecondsRemoveLetGo(s.BPM);
		n.timbre.setEnvelope(Envelopes.createWithValues(seconds, 1, 0, 1, 0.6, 0.5));
		s.addNotes1(n);*/
		
		//guitar-ish:
		Note n = Note.create2(NoteName.E4, WaveType.TRIANGLE_WAVE);
		n.setDistortion(DistortionType.HARMONIC_CLIPPING.create(3, 1, 1));
		n.setEnvelope(Envelopes.createWithValues(n.getLengthInSecondsRemoveLetGo(s.BPM), 0.02, 0, 2, 0, 0.1));
		s.addNotes1(n);
		
		
		return s;
	}
	
	public void addNotes1(int moveTimeBy, Note... notes) {
		addNotes(moveTimeBy * 16, notes);
	}
	public void addNotes1(Note... notes) {
		addNotes1(1, notes); //one full note by default
	}
	
	public void addNotes2(int moveTimeBy, Note... notes) {
		addNotes(moveTimeBy * 8, notes);
	}
	public void addNotes2(Note... notes) {
		addNotes2(1, notes); //one half note by default
	}
	
	/**
	 * Add notes to currentWritingTime and move currentWritingTime by moveTimeBy amount of 1/4 notes forward.
	 * @param moveTimeBy
	 * @param notes 
	 */
	public void addNotes4(int moveTimeBy, Note... notes) {
		addNotes(moveTimeBy * 4, notes);
	}
	public void addNotes4(Note... notes) {
		addNotes4(1, notes); //one quarter note by default
	}
	
	public void addNotes8(int moveTimeBy, Note... notes) {
		addNotes(moveTimeBy * 2, notes);
	}
	public void addNotes8(Note... notes) {
		addNotes8(1, notes); //one 8th note by default
	}
	
	public void addNotes16(int moveTimeBy, Note... notes) {
		addNotes(moveTimeBy, notes);
	}
	public void addNotes16(Note... notes) {
		addNotes16(1, notes); //one 16th note by default
	}
	
	/**
	 * Adds notes to currentWritingTime (which is in 16th notes).
	 * @param moveTimeBy How much to move to write the next note (in 16th notes).
	 * @param notes 
	 */
	private void addNotes(double moveTimeBy, Note... notes) {
		if (notes.length != 0) {
			add(new NoteStart(currentWritingTime / 16.0).addAll(notes));
		}
		currentWritingTime += moveTimeBy;
	}
	
	public void add(NoteStart noteStart) {
		noteStarts.add(noteStart);
	}
	
	public ArrayList<NoteStart> getNoteStarts() {
		return noteStarts;
	}
	
	public void setDefaultWaveTypes() {
		for (NoteStart noteStart : noteStarts) {
			for (Note note : noteStart.getNotes()) {
				if (note.getWaveType() == null) {
					note.setWaveType(defaultWaveType);
				}
			}
		}
	}
	
	public void combineSheetMusic(SheetMusic other) {
		//these 2 save the default wavetype of the specific sheetmusic to all their notes without wavetype
		setDefaultWaveTypes();
		other.setDefaultWaveTypes();
		
		this.noteStarts.addAll(other.noteStarts);
	}
}
