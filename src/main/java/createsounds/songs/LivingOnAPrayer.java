package createsounds.songs;

import createsounds.notation.Note;
import createsounds.notation.NoteName;
import createsounds.notation.SheetMusic;
import createsounds.timbre.Envelope;
import createsounds.timbre.Envelopes;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion;
import createsounds.timbre.effects.Distortion.DistortionType;

public class LivingOnAPrayer {
	private static final double BPM = 123;
	
	public static SheetMusic create() {
		SheetMusic s = createMain();
		
		return s;
	}
	
	public static SheetMusic createMain() {
		SheetMusic s = new SheetMusic(BPM);
		
		
		//808 bass testing:
		for (int i = 0; i < 16; i++) {
			//addBaseLoop(s, e, 1);
			addBassLoop(s, 0);
		}
		
		
		/*ylennetyt:
		F -> F#
		*/
		
		//both are good with harmonic distortion:
		Envelope e;// = Envelopes.createWithValuesWithoutLength(0.01, 0.5, 1.35, 0, 0.05);
		e = Envelopes.createWithValuesWithoutLength(0.01, 0.2, 1.35, 0.6, 0.05);
		s.setAllNotesEnvelope(e);
		
		s.defaultWaveType = WaveType.SINE_WAVE;
		s.setAllNotesDistortion(DistortionType.HARMONIC_CLIPPING.create(1.5, 1, 1));
		
		return s;
	}
	
	private static void addBassLoop(SheetMusic s, int offsetOctave) {
		s.addNotes8(Note.create8(NoteName.E1.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.E2.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.B1.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.D2.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.E1.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.E1.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.B1.getDifferentNoteByOctaveOffset(offsetOctave)));
		
		s.addNotes8(Note.create8(NoteName.D2.getDifferentNoteByOctaveOffset(offsetOctave)));
	}
}
