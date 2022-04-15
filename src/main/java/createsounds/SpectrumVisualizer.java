package createsounds;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.quifft.QuiFFT;
import org.quifft.output.FFTFrame;
import org.quifft.output.FFTStream;
import org.quifft.output.FrequencyBin;

/**
 * Program to visualize the frequency spectrum of a song over time.
 * When the program runs, it plays the song while a frequency spectrum graph animates in sync.
 * The values for the frequency spectrum graph are obtained through a Fast Fourier Transform (FFT).
 * This visualizer uses the QuiFFT library to compute the FFT result for the song.
 *
 * To try it with your own .wav, .aiff, or .mp3 files, add them to the /resources folder and
 * update the class's private constructor.
 *
 * Credits for the royalty-free example song are below.
 * Artist: JPB
 * Title: High [NCS Release]
 * Youtube: https://youtu.be/Tv6WImqSuxA
 * Music provided by NoCopyrightSounds
 * Music promoted by Audio Library: https://youtu.be/R8ZRCXy5vhA
 */
public class SpectrumVisualizer {

    // Song to play
    private String song;

    // FFTStream used to compute FFT frames
    private static FFTStream fftStream;

    // Next frame to graph
    private static FFTFrame nextFrame;

    // Wrapper for JFreeChart line graph
	private static FFTGrapher grapher = new FFTGrapher();
	
	
    public SpectrumVisualizer(String file) {
        //song = new File(SpectrumVisualizer.class.getClassLoader().getResource(file).getFile());
		song = file;
		System.out.println(song);
		
		grapher.initializeGraph();
    }
	
    public void visualizeSpectrum() {
        // Obtain FFTStream for song from QuiFFT
        QuiFFT quiFFT = null;
        try {
            quiFFT = new QuiFFT(song).windowSize(8192).windowOverlap(0.75);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
			return;
        }
        fftStream = quiFFT.fftStream();
        System.out.println(fftStream);

        // Compute first frame
        nextFrame = fftStream.next();

        // Start playing audio
        try {
			AudioFormat newFormat = new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(newFormat, AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(song))));
            //InputStream in = new FileInputStream(song);
			
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();
			
			/*AudioStream audioStream = new AudioStream(in);
			AudioPlayer.player.start(audioStream);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Calculate time between consecutive FFT frames
        double msBetweenFFTs = fftStream.windowDurationMs * (1 - fftStream.fftParameters.windowOverlap);
        long nanoTimeBetweenFFTs = Math.round(msBetweenFFTs * Math.pow(10, 6));

        // Begin visualization cycle
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(SpectrumVisualizer::graphThenComputeNextFrame, 0, nanoTimeBetweenFFTs, TimeUnit.NANOSECONDS);
    }

    private static void graphThenComputeNextFrame() {
        // Graph currently stored frame
        FrequencyBin[] bins = nextFrame.bins;
        long timestamp = (long) nextFrame.frameStartMs / 1000;
        grapher.updateFFTData(bins, timestamp);

        // If next frame exists, compute it
        if(fftStream.hasNext()) {
            nextFrame = fftStream.next();
        } else { // otherwise song has ended, so end program
            System.exit(0);
        }
    }
}
