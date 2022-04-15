package createsounds.songs;

import createsounds.notation.Note;
import static createsounds.notation.NoteName.*;
import createsounds.notation.SheetMusic;
import createsounds.timbre.Envelopes;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion;
import java.util.Collections;

public class TwinkleTwinkleLittleStar {
	private static final double BPM = 120;
	
	public static SheetMusic create() {
		SheetMusic s = new SheetMusic(BPM);
		
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(C4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(G4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(A4));
		
		s.addNotes2(Note.create2(G4));
		
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(F4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(D4));
		
		s.addNotes2(Note.create2(C4));
		
		
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 2; i++)
				s.addNotes4(Note.create4(G4));
			for (int i = 0; i < 2; i++)
				s.addNotes4(Note.create4(F4));
			for (int i = 0; i < 2; i++)
				s.addNotes4(Note.create4(E4));

			s.addNotes2(Note.create2(D4));
		}
		
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(C4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(G4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(A4));
		
		s.addNotes2(Note.create2(G4));
		
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(F4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(D4));
		
		s.addNotes2(Note.create2(C4));
		
		Collections.sort(s.noteStarts);
		
		s.defaultWaveType = WaveType.MY_OWN2; //pretty good
		/*s.defaultWaveType = WaveType.PIANO_APPROX3_WAVE; //ihme outo korkee ääni seas
		s.defaultWaveType = WaveType.PIANO_APPROX2_WAVE; //outo
		s.defaultWaveType = WaveType.PIANO_APPROX4_WAVE; //weird pehmee
		s.defaultWaveType = WaveType.TRIANGLE_WAVE; //ei paha*/
		
		s.setAllNotesEnvelope(Envelopes.createWithValuesWithoutLength(0.02, 0, 2, 0, 0.1));
		
		/*s.defaultWaveType = WaveType.TRIANGLE_WAVE;
		s.changeAllNotesDistortion(Distortion.DistortionType.HARMONIC_CLIPPING.create(3, 1, 1));*/
		
		return s;
	}
}
