package createsounds;

import audiofilereader.MusicData;
import createsounds.notation.SheetMusic;
import createsounds.songs.BillieJean;
import createsounds.sound_creation.CreateSounds;
import createsounds.songs.Clocks;
import createsounds.songs.Imagine;
import createsounds.songs.KissanPolkka;
import createsounds.songs.LivingOnAPrayer;
import createsounds.songs.SunshineOfYourLove;
import createsounds.songs.TwinkleTwinkleLittleStar;
import createsounds.songs.UkkoNooa;
import audioplayer.Game;
import java.io.File;
import java.io.FileOutputStream;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println(HelperFunctions.deciBelToVol(-10));
		DrumsLoaded.loadDrums();
		
		//FFT.doFFT();
		
		//SpectrumVisualizer visualizer = new SpectrumVisualizer("Simple sine wave C4.wav");
		/*SpectrumVisualizer visualizer = new SpectrumVisualizer("Clocks synthetized.wav");
        visualizer.visualizeSpectrum();*/
		
		
		//SheetMusic s = SheetMusic.createTest();
		//SheetMusic s = SunshineOfYourLove.create();
		//SheetMusic s = LivingOnAPrayer.create();
		//SheetMusic s = BillieJean.create();
		//SheetMusic s = KissanPolkka.create();
		//SheetMusic s = TwinkleTwinkleLittleStar.create();
		//SheetMusic s = Imagine.create();
		SheetMusic s = Clocks.create();
		
		MusicData musicData = CreateSounds.createFromSheetMusic(s, 44100, 1f);
		
		/*FileOutputStream os = new FileOutputStream(new File("Billie Jean synthesized.wav"));
		SaveToWav.PCMtoFile(os, musicData.dataBytes, musicData.sampleRate, musicData.numberOfChannels, musicData.bitsPerSample);*/
		
		Game game = new Game(musicData);
		new Thread(game).start();
	}
}
