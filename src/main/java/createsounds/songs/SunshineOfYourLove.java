package createsounds.songs;

import createsounds.notation.Note;
import createsounds.notation.NoteName;
import createsounds.notation.SheetMusic;
import static createsounds.songs.Clocks.createBackground;
import static createsounds.songs.Clocks.createBass;
import static createsounds.songs.Clocks.createMain;
import createsounds.timbre.Envelope;
import createsounds.timbre.Envelopes;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion;

public class SunshineOfYourLove {
	private static final double BPM = 116;
	
	public static SheetMusic create() {
		SheetMusic s = createMain();
		
		return s;
	}
	
	public static SheetMusic createMain() {
		SheetMusic s = new SheetMusic(BPM);
		
		//both are good with harmonic distortion:
		Envelope e;// = Envelopes.createWithValuesWithoutLength(0.01, 0.5, 1.35, 0, 0.05);
		e = Envelopes.createWithValuesWithoutLength(0.01, 0.2, 1.35, 0.6, 0.05);
		
		/*ylennetyt:
		C -> C#
		F -> F#
		*/
		
		//808 bass testing:
		s.addNotes8(Note.create8(NoteName.D4));
		
		s.addNotes8(Note.create8(NoteName.D4));
		
		s.addNotes8(Note.create8(NoteName.C4));
		
		s.addNotes4(Note.create8(NoteName.D4));
		
		s.addNotes4(Note.create8(NoteName.A3));
		
		s.addNotes4(Note.create8(NoteName.GS3));
		
		s.addNotes4(Note.create8(NoteName.G3));
		
		s.addNotes8(Note.create8(NoteName.D3));
		
		s.addNotes4(Note.create4(NoteName.F3));
		
		s.addNotes4(Note.create16(NoteName.D3, 3));
		
		
		//lower octave
		s.addNotes8(Note.create8(NoteName.D3));
		
		s.addNotes8(Note.create8(NoteName.D3));
		
		s.addNotes8(Note.create8(NoteName.C3));
		
		s.addNotes4(Note.create8(NoteName.D3));
		
		s.addNotes4(Note.create8(NoteName.A2));
		
		s.addNotes4(Note.create8(NoteName.GS2));
		
		s.addNotes4(Note.create8(NoteName.G2));
		
		s.addNotes8(Note.create8(NoteName.D2));
		
		s.addNotes4(Note.create4(NoteName.F2));
		
		s.addNotes4(Note.create16(NoteName.D2, 3));
		
		
		
		//even lower octave
		s.addNotes8(Note.create8(NoteName.D2));
		
		s.addNotes8(Note.create8(NoteName.D2));
		
		s.addNotes8(Note.create8(NoteName.C2));
		
		s.addNotes4(Note.create8(NoteName.D2));
		
		s.addNotes4(Note.create8(NoteName.A1));
		
		s.addNotes4(Note.create8(NoteName.GS1));
		
		s.addNotes4(Note.create8(NoteName.G1));
		
		s.addNotes8(Note.create8(NoteName.D1));
		
		s.addNotes4(Note.create4(NoteName.F1));
		
		s.addNotes4(Note.create16(NoteName.D1, 3));
		
		/*ylennetyt:
		C -> C#
		F -> F#
		*/
		
		
		s.setAllNotesEnvelope(e);
		s.defaultWaveType = WaveType.SINE_WAVE;
		s.setAllNotesDistortion(Distortion.DistortionType.HARMONIC_CLIPPING.create(1.5, 1, 1));
		
		return s;
	}
}
