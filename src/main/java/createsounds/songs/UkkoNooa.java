package createsounds.songs;

import createsounds.notation.Note;
import static createsounds.notation.NoteName.*;
import createsounds.notation.SheetMusic;
import java.util.Collections;

public class UkkoNooa {
	public static int BPM = 120;
	public static SheetMusic create() {
		SheetMusic s = new SheetMusic(BPM);
		
		for (int i = 0; i < 3; i++)
			s.addNotes4(Note.create4(C4));
		s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 3; i++)
			s.addNotes4(Note.create4(D4));
		s.addNotes4(Note.create4(F4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(D4));
		
		s.addNotes1(Note.create(C4));
		
		s.addNotes4(Note.create4(E4));
		s.addNotes4(Note.create4(E4));
		s.addNotes4(Note.create4(E4));
		s.addNotes4(Note.create4(E4));
		s.addNotes2(Note.create2(G4));
		s.addNotes2(Note.create2(F4));
		s.addNotes4(Note.create4(D4));
		s.addNotes4(Note.create4(D4));
		s.addNotes4(Note.create4(D4));
		s.addNotes4(Note.create4(D4));
		s.addNotes2(Note.create2(F4));
		s.addNotes2(Note.create2(E4));
		
		
		for (int i = 0; i < 3; i++)
			s.addNotes4(Note.create4(C4));
		s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 3; i++)
			s.addNotes4(Note.create4(D4));
		s.addNotes4(Note.create4(F4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(E4));
		for (int i = 0; i < 2; i++)
			s.addNotes4(Note.create4(D4));
		
		s.addNotes1(Note.create(C4));
		
		Collections.sort(s.noteStarts);
		
		//s.changeAllNotesWaveType(WaveType.MY_OWN1);
		
		return s;
	}
}
