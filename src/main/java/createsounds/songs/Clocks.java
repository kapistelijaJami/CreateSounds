package createsounds.songs;

import createsounds.notation.Note;
import static createsounds.notation.NoteName.*;
import createsounds.notation.SheetMusic;
import createsounds.timbre.Envelope;
import createsounds.timbre.Envelopes;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion;

public class Clocks {
	private static final double BPM = 130;
	
	public static SheetMusic create() {
		SheetMusic s = createMain();
		s.combineSheetMusic(createBackground());
		s.combineSheetMusic(createBass());
		/*s.combineSheetMusic(createBass());
		s.combineSheetMusic(createBass());
		s.combineSheetMusic(createBass());*/
		
		return s;
	}
	
	public static SheetMusic createMain() {
		SheetMusic s = new SheetMusic(BPM);
		
		/*alennetut:
		E -> D#
		D -> C#
		B -> A#
		A -> G#
		*/
		
		for (int i = 0; i < 2; i++) {
			//Eb
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(G4));
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(G4));
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			
			for (int j = 0; j < 2; j++) {
				//Bbm
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
				s.addNotes8(Note.create8(F4));
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
				s.addNotes8(Note.create8(F4));
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
			}
			
			//Fm
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
		}
		
		for (int i = 0; i < 2; i++) {
			//Eb
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(G4));
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(G4));
			s.addNotes8(Note.create8(DS5));
			s.addNotes8(Note.create8(AS4));
			
			for (int j = 0; j < 2; j++) {
				//Bbm
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
				s.addNotes8(Note.create8(F4));
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
				s.addNotes8(Note.create8(F4));
				s.addNotes8(Note.create8(CS5));
				s.addNotes8(Note.create8(AS4));
			}
			
			//Fm
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(C5));
			s.addNotes8(Note.create8(GS4));
		}
		
		//---------------------------------------------
		
		//Eb
		//Lights go out and I
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(C5));
		
		//Bbm
		//can't be saved,
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes8(3);
		
		//Bbm
		//tides that I tried to
		s.addNotes4(Note.create4(CS5));
		s.addNotes8(Note.create8(CS5));
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(CS5));
		
		//Fm
		//swim against
		s.addNotes8(Note.create8(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes4(Note.create4(GS4));
		s.addNotes8(3);
		
		//Eb
		//brought me down up
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes8();
		s.addNotes8(Note.create8(DS5));
		
		//Bbm
		//on my knees
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes8(3);
		
		//Bbm
		//Oh I beg, I
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(AS4));
		
		//Fm
		//beg and plead. Singin'
		s.addNotes8(Note.create8(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes4(Note.create4(GS4));
		s.addNotes8();
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(DS4));
		
		//Eb
		//come out of
		s.addNotes4();
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes8();
		s.addNotes8(Note.create8(DS5));
		
		//Bbm
		//things unsaid
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes8(3);
		
		//Bbm
		//Shoot an apple
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(AS4));
		
		//Fm
		//off my head. And a
		s.addNotes8(Note.create8(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes4(Note.create4(GS4));
		s.addNotes8();
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(DS4));
		
		//Eb
		//trouble that
		s.addNotes4();
		s.addNotes4(Note.create4(DS5));
		s.addNotes4(Note.create4(DS5));
		s.addNotes8();
		s.addNotes8(Note.create8(C5));
		
		//Bbm
		//can't be named. A
		s.addNotes8(Note.create8(CS5));
		s.addNotes4(Note.create4(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes4();
		s.addNotes8(Note.create8(AS4));
		
		//Bbm
		//tiger's waiting
		s.addNotes4(Note.create4(CS5));
		s.addNotes4(Note.create4(CS5));
		s.addNotes4(Note.create4(CS5));
		s.addNotes8();
		s.addNotes8(Note.create8(AS4));
		
		//Fm
		//to be tamed. Singin'
		s.addNotes8(Note.create8(C5));
		s.addNotes4(Note.create4(AS4));
		s.addNotes4(Note.create4(GS4));
		s.addNotes8();
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(DS4));
		
		
		for (int i = 0; i < 2; i++) {
			//Eb
			//you--
			s.addNotes2(Note.create8(DS4, 5).minimalSilence(), Note.create8(G3, 5).minimalSilence(), Note.create8(AS3, 5).minimalSilence());
			s.addNotes8();
			s.addNotes4(Note.create8(G5, 3).minimalSilence(), Note.create8(G3, 3).minimalSilence(), Note.create8(AS4, 3).minimalSilence(), Note.create8(DS5, 3).minimalSilence());
			s.addNotes8();
			
			//Bbm
			//--
			s.addNotes2(Note.create8(GS5, 5).minimalSilence(), Note.create(F3).minimalSilence(), Note.create8(AS4, 6).minimalSilence(), Note.create8(DS5, 6).minimalSilence());
			s.addNotes8();
			s.addNotes8(Note.create8(F5).minimalSilence());
			s.addNotes4(Note.create8(DS5, 3), Note.create4(AS4).minimalSilence());
			
			//Bbm
			//are
			s.addNotes2(Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence());
			s.addNotes8(3);
			s.addNotes8(Note.create(G5));
			
			//Fm
			//--
			s.addNotes1(Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence());
		}
		
		//Eb
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));
		s.addNotes8(Note.create8(G4));
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));
		s.addNotes8(Note.create8(G4));
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));

		for (int j = 0; j < 2; j++) {
			//Bbm
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
		}

		//Fm
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		
		
		//Eb
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));
		s.addNotes8(Note.create8(G4));
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));
		s.addNotes8(Note.create8(G4));
		s.addNotes8(Note.create8(DS5));
		s.addNotes8(Note.create8(AS4));

		for (int j = 0; j < 2; j++) {
			//Bbm
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
			s.addNotes8(Note.create8(F4));
			s.addNotes8(Note.create8(CS5));
			s.addNotes8(Note.create8(AS4));
		}

		//Fm
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		s.addNotes8(Note.create8(F4));
		s.addNotes8(Note.create8(C5));
		s.addNotes8(Note.create8(GS4));
		
		/*alennetut:
		E -> D#
		D -> C#
		B -> A#
		A -> G#
		
		Chords:
		Eb = Note.create(DS4).minimalSilence(), Note.create(G3).minimalSilence(), Note.create(AS3).minimalSilence() //tää DS4 on parempi ku DS3 tähän biisiin
		Bbm = Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence() //tää F3 on parempi ku F4 tähän biisiin
		Fm = Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence()
		Ab = Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence(), Note.create(DS4).minimalSilence()
		Gb = Note.create(FS3).minimalSilence(), Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence()
		Db = Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence()
		Gbmaj7 = Note.create(FS3).minimalSilence(), Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F4).minimalSilence()
		*/
		
		//pretty good both individually:
		//s.defaultWaveType = WaveType.SAWTOOTH_WAVE;
		s.defaultWaveType = WaveType.HARMONICS3; //this is best
		//and they are pretty good together too (but too much buzzin'):
		//s.defaultWaveType = WaveType.HARMONICS3_AND_SAWTOOTH;
		
		
		return s;
	}
	
	public static SheetMusic createBass() {
		SheetMusic s = new SheetMusic(BPM);
		
		int offsetOctave = 0;
		
		s.addNotes1(8); //8 empty whole notes
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				s.addNotes8(Note.create8(DS2.getDifferentNoteByOctaveOffset(offsetOctave)));
			}
			for (int j = 0; j < 16; j++) {
				s.addNotes8(Note.create8(AS2.getDifferentNoteByOctaveOffset(offsetOctave)));
			}
			for (int j = 0; j < 8; j++) {
				s.addNotes8(Note.create8(F2.getDifferentNoteByOctaveOffset(offsetOctave)));
			}
		}
		
		s.defaultWaveType = WaveType.SQUARE_WAVE; //lot of buzzing, which could be good for bass, it's loud too, but maybe not the best
		s.defaultWaveType = WaveType.HARMONICS3; //good, but might need double volume
		s.defaultWaveType = WaveType.HARMONICS; //pretty nice for bass, seems better than the 808
		//s.defaultWaveType = WaveType.HARMONICS2; //pretty nice for bass too
		
		
		//808, this envelope works with above as well
		Envelope e;// = Envelopes.createWithValuesWithoutLength(0.01, 0.5, 1.35, 0, 0.05);
		e = Envelopes.createWithValuesWithoutLength(0.01, 0.2, 1.35, 0.6, 0.05);
		s.setAllNotesEnvelope(e);
		/*s.defaultWaveType = WaveType.SINE_WAVE;
		s.changeAllNotesDistortion(DistortionType.HARMONIC_CLIPPING.create(5, 1, 1)); //lot of distortion*/
		
		return s;
	}
	
	public static SheetMusic createBackground() {
		SheetMusic s = new SheetMusic(BPM);
		
		
		/*Chords:
		Eb = Note.create(DS4).minimalSilence(), Note.create(G3).minimalSilence(), Note.create(AS3).minimalSilence() //tää DS4 on parempi ku DS3 tähän biisiin
		Bbm = Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence() //tää F3 on parempi ku F4 tähän biisiin
		Fm = Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence()
		Ab = Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence(), Note.create(DS4).minimalSilence()
		Gb = Note.create(FS3).minimalSilence(), Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence()
		Db = Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence()
		Gbmaj7 = Note.create(FS3).minimalSilence(), Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F4).minimalSilence()
		*/
		
		for (int i = 0; i < 8; i++) {
			//Eb
			s.addNotes1(Note.create(DS4).minimalSilence(), Note.create(G3).minimalSilence(), Note.create(AS3).minimalSilence());
			
			for (int j = 0; j < 2; j++) {
				//Bbm
				s.addNotes1(Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence());
			}
			
			//Fm
			s.addNotes1(Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence());
		}
		
		s.addNotes1(8); //this part is handled in createMain()
		
		//Eb
		s.addNotes1(Note.create(DS4).minimalSilence(), Note.create(G3).minimalSilence(), Note.create(AS3).minimalSilence());
		
		for (int j = 0; j < 2; j++) {
			//Bbm
			s.addNotes1(Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence());
		}
		
		//Fm
		s.addNotes1(Note.create(F3).minimalSilence(), Note.create(GS3).minimalSilence(), Note.create(C4).minimalSilence());
		
		//Eb
		s.addNotes1(Note.create(DS4).minimalSilence(), Note.create(G3).minimalSilence(), Note.create(AS3).minimalSilence());
		
		for (int j = 0; j < 2; j++) {
			//Bbm
			s.addNotes1(Note.create(AS3).minimalSilence(), Note.create(CS4).minimalSilence(), Note.create(F3).minimalSilence());
		}
		
		//Fm
		Envelope e = Envelopes.createDefaultWithRelease(Note.convertNoteLengthToSeconds(s.BPM, 1), 2);
		s.addNotes1(Note.create(F3).setEnvelope(e), Note.create(GS3).setEnvelope(e), Note.create(C4).setEnvelope(e));
		
		
		s.defaultWaveType = WaveType.HARMONICS3;
		
		return s;
	}
}
