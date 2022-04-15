package createsounds.songs;

import createsounds.notation.Note;
import static createsounds.notation.NoteName.*;
import createsounds.notation.SheetMusic;
import createsounds.timbre.WaveType;

public class Imagine {
	private static final double BPM = 70;
	
	public static SheetMusic create() {
		SheetMusic s = createMain();
		s.combineSheetMusic(createBackground());
		//s.combineSheetMusic(createBass());
		//s.combineSheetMusic(createBass());
		
		return s;
	}
	
	public static SheetMusic createMain() {
		SheetMusic s = new SheetMusic(BPM);
		
		
		s.defaultWaveType = WaveType.HARMONICS3;
		
		return s;
	}

	public static SheetMusic createBackground() {
		SheetMusic s = new SheetMusic(BPM);
		
		s.addNotes8(Note.create8(E3), Note.create8(G3));
		s.addNotes8(Note.create8(C3));
		s.addNotes8(Note.create8(E3), Note.create8(G3));
		s.addNotes8(Note.create8(C3));
		s.addNotes8(Note.create8(E3), Note.create8(G3));
		s.addNotes8(Note.create8(C3));
		s.addNotes8(Note.create8(E3), Note.create8(B3));
		s.addNotes8(Note.create8(C3));
		
		s.addNotes8(Note.create8(F3), Note.create8(A3));
		s.addNotes8(Note.create8(C3));
		s.addNotes8(Note.create8(F3), Note.create8(A3));
		s.addNotes8(Note.create8(C3));
		s.addNotes8(Note.create8(F3), Note.create8(A3));
		s.addNotes8(Note.create8(C3));
		s.addNotes16(Note.create16(A3));
		s.addNotes16(Note.create16(AS3));
		s.addNotes8(Note.create8(B3));
		
		//s.defaultWaveType = WaveType.CLARINET;
		//s.defaultWaveType = WaveType.GUITAR;
		s.defaultWaveType = WaveType.GUITAR2;
		//s.defaultWaveType = WaveType.TRIANGLE_WAVE;
		
		return s;
	}
}
