package createsounds.songs;

import createsounds.notation.Note;
import createsounds.notation.NoteName;
import createsounds.notation.SheetMusic;
import createsounds.timbre.Envelope;
import createsounds.timbre.Envelopes;
import createsounds.timbre.Timbre;
import createsounds.timbre.WaveType;
import createsounds.timbre.effects.Distortion.DistortionType;
import createsounds.timbre.instruments.Drums;
import static createsounds.timbre.instruments.Drums.DrumType.*;

public class BillieJean {
	private static final double BPM = 117;
	
	public static SheetMusic create() {
		SheetMusic s = new SheetMusic(BPM);
		s.combineSheetMusic(createMain());
		s.combineSheetMusic(createDrums());
		s.combineSheetMusic(createBass());
		s.combineSheetMusic(createLyrics());
		
		return s;
	}
	
	public static SheetMusic createMain() { //https://www.musicnotes.com/sheetmusic/mtd.asp?ppn=MN0041781
		SheetMusic s = new SheetMusic(BPM);
		
		s.addNotes1(10);
		
		double volume = 0.7;
		
		//Chords:
		Note[] FSm = { Note.create8(NoteName.A3), Note.create8(NoteName.CS4), Note.create8(NoteName.FS4) };
		Note[] GSm = { Note.create8(NoteName.B3), Note.create8(NoteName.DS4), Note.create8(NoteName.GS4) };
		Note[] FSm7 = { Note.create8(NoteName.CS4), Note.create8(NoteName.E4), Note.create8(NoteName.A4) };
		
		for (int i = 0; i < 4; i++) { //takes 2 bars
			s.addNotes8(3, FSm);
			s.addNotes8(5, GSm);
			
			s.addNotes8(3, FSm7);
			s.addNotes8(5, GSm);
		}
		
		
		/*ylennetyt:
		C -> C#
		F -> F#
		G -> G#
		*/
		
		s.defaultWaveType = WaveType.SINE_WAVE;
		
		Envelope e;// = Envelopes.createWithValuesWithoutLength(0.01, 0.5, 1.35, 0, 0.05);
		e = Envelopes.createWithValuesWithoutLength(0.05, 0, 0.4, 0.2, 0.38);
		s.setAllNotesEnvelope(e);
		
		s.setAllNotesDistortion(DistortionType.HARMONIC_CLIPPING.create(1.5, 1, 0.2));
		
		s.setAllNotesVolume(volume);
		return s;
	}
	
	public static SheetMusic createBass() {
		SheetMusic s = new SheetMusic(BPM);
		
		s.addNotes1(2);
		
		addBassLoop(s, 20, -1);
		
		//both are good with harmonic clipping distortion:
		Envelope e;// = Envelopes.createWithValuesWithoutLength(0.01, 0.5, 1.35, 0, 0.05);
		e = Envelopes.createWithValuesWithoutLength(0.01, 0.2, 1.35, 0.6, 0.05);
		s.setAllNotesEnvelope(e);
		
		//s.defaultWaveType = WaveType.TRIANGLE_WAVE; //this is not bad either
		//s.defaultWaveType = WaveType.SAWTOOTH_WAVE; //very buzzy
		s.defaultWaveType = WaveType.SINE_WAVE;
		s.setAllNotesDistortion(DistortionType.HARMONIC_CLIPPING.create(1, 1, 1)); //smaller distortion
		s.setAllNotesVolume(1.2);
		return s;
	}
	
	private static void addBassLoop(SheetMusic s, int times, int offsetOctave) {
		for (int i = 0; i < times; i++) {
			Note n = Note.create8(NoteName.FS3.getDifferentNoteByOctaveOffset(offsetOctave - 1)); //damn, this actually works, the harmonics from the distortion make it sound like the same octave, just more BUZZ
			n.setDistortion(DistortionType.HARMONIC_CLIPPING.create(3, 1, 1));
			s.addNotes8(n);

			s.addNotes8(Note.create8(NoteName.CS3.getDifferentNoteByOctaveOffset(offsetOctave)));
			s.addNotes8(Note.create8(NoteName.E3.getDifferentNoteByOctaveOffset(offsetOctave)));

			n = Note.create8(NoteName.FS3.getDifferentNoteByOctaveOffset(offsetOctave - 1));
			n.setDistortion(DistortionType.HARMONIC_CLIPPING.create(3, 1, 1));
			s.addNotes8(n);

			s.addNotes8(Note.create8(NoteName.E3.getDifferentNoteByOctaveOffset(offsetOctave)));
			s.addNotes8(Note.create8(NoteName.CS3.getDifferentNoteByOctaveOffset(offsetOctave)));
			s.addNotes8(Note.create8(NoteName.B2.getDifferentNoteByOctaveOffset(offsetOctave)));
			s.addNotes8(Note.create8(NoteName.CS3.getDifferentNoteByOctaveOffset(offsetOctave)));
		}
		
		/*ylennetyt:
		C -> C#
		F -> F#
		G -> G#
		*/
	}
	
	private static SheetMusic createDrums() {
		SheetMusic s = new SheetMusic(BPM);
		
		Timbre bassTimbre = new Timbre(new Drums(BASS)).setDistortion(DistortionType.CLIPPING.create(10, 1, 1)).setVolume(1.5);
		
		Timbre snareTimbre = new Timbre(new Drums(SNARE)).setDistortion(DistortionType.CLIPPING.create(10, 1, 1)).setVolume(1.2);
		Timbre crashTimbre = new Timbre(new Drums(CRASH)).setDistortion(DistortionType.CLIPPING.create(10, 0.5, 1)).setVolume(0.3);
		Timbre hihatOpenTimbre = new Timbre(new Drums(HIHAT_OPEN));
		
		crashTimbre.setEnvelope(Envelopes.createWithValues(2, 0, 0, 2, 0, 0));
		
		addDrumLoop(s, 6, bassTimbre, snareTimbre, crashTimbre, hihatOpenTimbre, false, false);
		addDrumLoop(s, 1, bassTimbre, snareTimbre, crashTimbre, hihatOpenTimbre, false, true); //hihat open
		addDrumLoop(s, 15, bassTimbre, snareTimbre, crashTimbre, hihatOpenTimbre, false, false);
		
		
		return s;
	}
	
	private static void addDrumLoop(SheetMusic s, int times, Timbre bass, Timbre snare, Timbre crash, Timbre hihatOpen, boolean addCrashBeginning, boolean addHihatOpen) {
		
		for (int i = 0; i < times; i++) {
			if (addHihatOpen && addCrashBeginning) {
				s.addNotes8(new Note(hihatOpen), new Note(bass), new Note(crash));
			} else if (addHihatOpen) {
				s.addNotes8(new Note(hihatOpen), new Note(bass));
			} else if (addCrashBeginning) {
				s.addNotes8(new Note(new Drums(CRASH)), new Note(bass), new Note(crash));
			} else {
				s.addNotes8(new Note(new Drums(HIHAT)), new Note(bass));
			}
			s.addNotes8(new Note(new Drums(HIHAT)));
			s.addNotes8(new Note(new Drums(HIHAT)), new Note(snare));
			s.addNotes8(new Note(new Drums(HIHAT)));
			s.addNotes8(new Note(new Drums(HIHAT)), new Note(bass));
			s.addNotes8(new Note(new Drums(HIHAT)));
			s.addNotes8(new Note(new Drums(HIHAT)), new Note(snare));
			s.addNotes8(new Note(new Drums(HIHAT)));
		}
	}
	
	private static SheetMusic createLyrics() {  //https://www.musicnotes.com/sheetmusic/mtd.asp?ppn=MN0041781 or //https://www.musicnotes.com/sheetmusic/mtd.asp?ppn=MN0135383
		SheetMusic s = new SheetMusic(BPM);
		
		s.addNotes1(18);
		
		s.addNotes4();
		
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.B4));
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes4(Note.create4(NoteName.B4));
		
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes16(Note.create16(NoteName.B4));
		s.addNotes16(Note.create16(NoteName.A4));
		s.addNotes8(Note.create8(NoteName.B4));
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes4(Note.create4(NoteName.CS5));
		
		s.addNotes8();
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes8(Note.create8(NoteName.B4));
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes4(Note.create4(NoteName.B4));
		
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes8(Note.create8(NoteName.CS5));
		s.addNotes4(Note.create4(NoteName.B4));
		s.addNotes8(Note.create8(NoteName.A4));
		s.addNotes8(Note.create8(NoteName.GS4));
		s.addNotes4(Note.create4(NoteName.FS4));
		
		/*ylennetyt:
		C -> C#
		F -> F#
		G -> G#
		*/
		
		s.defaultWaveType = WaveType.HARMONICS3;
		s.setAllNotesVolume(0.7);
		return s;
	}
}
