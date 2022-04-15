package createsounds.songs;

import createsounds.notation.Note;
import createsounds.notation.NoteName;
import static createsounds.notation.NoteName.*;
import createsounds.notation.SheetMusic;
import createsounds.timbre.Envelopes;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion;
import java.util.Collections;

public class KissanPolkka {
	private static final double BPM = 220;
	
	//Source: https://www.youtube.com/watch?v=Uez_A7r0Klc
	public static SheetMusic create() {
		SheetMusic s = new SheetMusic(BPM);
		
		for (int i = 0; i < 2; i++) {
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(DS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(DS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			//----------------------------------------------
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(AS4));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(AS4));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(AS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(CS5));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(DS5));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(DS5));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(DS5));
			s.addNotes4(Note.create8(B3), Note.create8(F4));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(DS5));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(CS5));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(AS4));
			s.addNotes4(Note.create8(AS3), Note.create8(FS4));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			//----------------------------------------------
			
			s.addNotes8(Note.create8(DS4));
			s.addNotes8(Note.create8(CS4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(F3));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(G3));
			s.addNotes4(Note.create4(GS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes4(Note.create4(GS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(GS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			s.addNotes4(Note.create4(CS3));
			s.addNotes4(Note.create4(B3), Note.create4(F4));
			
			s.addNotes4(Note.create4(GS3));
			s.addNotes4(Note.create4(G3));
			s.addNotes4(Note.create4(GS3));
			s.addNotes4(Note.create4(F3));
			s.addNotes4(Note.create4(FS3));
			s.addNotes4(Note.create4(AS3), Note.create4(FS4));
			s.addNotes4(Note.create4(CS3));
		}
		
		s.addNotes4(Note.create4(AS3), Note.create4(FS4));
		s.addNotes4(Note.create4(FS3));
		s.addNotes4(Note.create8(AS3), Note.create8(FS4));
		s.addNotes2(Note.create2(AS3), Note.create2(FS4));
		
		
		Collections.sort(s.noteStarts);
		
		s.defaultWaveType = WaveType.MY_OWN2; //best out of these
		
		//s.defaultWaveType = WaveType.HARMONICS3;
		//s.duplicateAllNotesAsAWaveType(WaveType.SINE_WAVE, 1);
		//s.defaultWaveType = WaveType.HARMONICS3_AND_SAWTOOTH;
		
		
		//sounded quitar-ish elsewhere, doesn't anymore:
		/*s.defaultWaveType = WaveType.TRIANGLE_WAVE;
		s.changeAllNotesDistortion(Distortion.DistortionType.HARMONIC_CLIPPING.create(3, 1, 1));
		s.changeAllNotesEnvelope(Envelopes.createWithValuesWithoutLength(0.02, 0, 2, 0, 0.1));*/
		
		
		return s;
	}
}
