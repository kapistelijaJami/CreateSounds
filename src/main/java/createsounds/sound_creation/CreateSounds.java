package createsounds.sound_creation;

import audiofilereader.MusicData;
import createsounds.notation.Note;
import createsounds.notation.SheetMusic;
import createsounds.notation.SheetMusic.NoteStart;
import java.util.ArrayList;
import static createsounds.Constants.MAX_AMPLITUDE_VALUE;
import createsounds.HelperFunctions;
import createsounds.timbre.Compressor;

public class CreateSounds {
	public static NoteSampleCache noteSampleCache;
	
	public static MusicData createFromSheetMusic(SheetMusic sheetMusic, int sampleRate, float amplitude) {
		sheetMusic.setDefaultWaveTypes();
		System.out.println("SheetMusic done, started creating audiodata from sheetmusic.");
		
		noteSampleCache = new NoteSampleCache();
		
		ArrayList<NoteStart> noteStarts = sheetMusic.getNoteStarts();
		
		ArrayList<Integer> temp = new ArrayList<>();
		for (NoteStart noteStart : noteStarts) {
			combineAll(temp, noteStart, sampleRate, sheetMusic.BPM);
		}
		temp.addAll(0, HelperFunctions.getListFilled(sampleRate / 2, 0)); //half second of silence at the beginning
		
		int[] allSamples = HelperFunctions.arrayListToIntArray(temp);
		
		
		CreateSounds.preventClipping(allSamples, amplitude);
		
		//new HighShelfFilter(6000, 1.5, sampleRate, -10).apply(samples, amplitude);
		//TODO: apply global effects (probably should take from sheetmusic itself)
		
		//new LFO(NoteName.A0.getHertz(), WaveType.SINE_WAVE).apply(allSamples, sampleRate);
		new Compressor().apply(allSamples, sampleRate, amplitude);
		
		noteSampleCache = null;
		
		MusicData musicData = MusicData.createMusicData(sampleRate, 2);
		musicData.setSamples(HelperFunctions.intArrayToShortArray(allSamples), true);
		
		return musicData;
	}
	
	public static void combineAll(ArrayList<Integer> allSamples, NoteStart noteStart, int sampleRate, double BPM) {
		double startTime = noteStart.getTimeInSeconds(BPM);
		int startIdx = (int) (startTime * sampleRate);
		
		for (Note note : noteStart.getNotes()) {
			int[] noteSamples = note.createSamples(BPM, sampleRate);
			
			for (int i = 0; i < noteSamples.length; i++) {
				int idx = startIdx + i;
				while (allSamples.size() <= idx) {
					allSamples.add(0);
				}
				
				int total = noteSamples[i] + allSamples.get(idx);
				
				allSamples.set(idx, total);
			}
		}
	}
	
	public static void preventClipping(int[] samples, float amplitude) {
		double maxValue = HelperFunctions.clamp(amplitude * (double) MAX_AMPLITUDE_VALUE, 0, (int) (MAX_AMPLITUDE_VALUE * 0.95));
		
		//how much over the max amplitude we are
		double ratio = peakValue(samples) / maxValue;
		
		if (ratio <= 1) { //over 1 is too much, less than 1 is allowed, and we dont do anything
			return;
		}
		
		for (int i = 0; i < samples.length; i++) {
			samples[i] /= ratio;
		}
	}
	
	public static int peakValue(int[] samples) {
		int peak = 0;
		
		for (int sample : samples) {
			int val = Math.abs(sample);
			if (val > peak) {
				peak = val;
			}
		}
		return peak;
	}
}
