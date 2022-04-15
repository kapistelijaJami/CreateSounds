package createsounds;

import audiofilereader.AudioFileReader;
import java.io.File;

public class DrumsLoaded {
	public static int[] BASS;
	public static int[] HIHAT;
	public static int[] HIHAT_OPEN;
	public static int[] SNARE;
	public static int[] CRASH;
	public static int[] RIDE;
	
	public static void loadDrums() {
		AudioFileReader audioReader = new AudioFileReader();
		
		BASS = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Bass-Drum.wav")).samplesLeft);
		
		HIHAT = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Hi-Hat-Closed.wav")).samplesLeft);
		
		HIHAT_OPEN = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Hi-Hat-Open.wav")).samplesLeft);
		
		SNARE = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Snare-Drum2.wav")).samplesLeft);
		
		CRASH = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Crash-Cymbal.wav")).samplesLeft);
		
		RIDE = HelperFunctions.shortArrayToIntArray(audioReader.read(new File("drums/Ride-Cymbal.wav")).samplesLeft);
	}
}
