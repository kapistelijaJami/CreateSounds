package createsounds;

import java.time.Duration;
import org.quifft.QuiFFT;
import org.quifft.output.FFTFrame;
import org.quifft.output.FFTResult;
import org.quifft.output.FrequencyBin;
import stopwatchtimerrender.Settings;

public class FFT {
	public static void doFFT() {
		FFTResult fft;
        try {
            QuiFFT quiFFT = new QuiFFT("Clocks synthetized.wav");
			quiFFT.dBScale(false);
			quiFFT.normalized(true);
            fft = quiFFT.fullFFT();
        } catch (Exception e) {
            System.out.println("Error " + e);
			return;
        }
		
		System.out.println(fft);
		
		FFTFrame[] fftFrames = fft.fftFrames;
		
		System.out.println("There are " + fftFrames.length + " frames in this FFT, each of which was computed " +
                "from a sampling window that was about " + Math.round(fft.windowDurationMs) + " milliseconds long.");
		
		System.out.println();
		
		for (int i = 0; i < fftFrames.length; i++) {
			//printSamplingWindow(fft, i);
			System.out.print(i + ": ");
			printMaxFreqAndAmplitude(fft, i);
		}
	}
	
	private static void printSamplingWindow(FFTResult fft, int window) {
		FFTFrame frame = fft.fftFrames[window];
		
		System.out.println("This sampling window is " + window + " at the timestamp of " + new Settings("hh:mm:ss.lll").format(Duration.ofMillis((long) frame.frameStartMs)));
		
		for (int i = 0; i < frame.bins.length; i++) {
			FrequencyBin bin = frame.bins[i];
			
			System.out.println("Bin " + i + " is located at " + Math.round(bin.frequency) + " Hz, has an amplitude of " +
                (fft.fftParameters.useDecibelScale ? Math.round(bin.amplitude) + " dB." : bin.amplitude));
		}
	}
	
	private static void printMaxFreqAndAmplitude(FFTResult fft, int window) {
		FFTFrame frame = fft.fftFrames[window];
		double amp = Double.NEGATIVE_INFINITY;
		double freq = 0;
		
		for (int i = 0; i < frame.bins.length; i++) {
			FrequencyBin bin = frame.bins[i];
			if (bin.amplitude > amp) {
				amp = bin.amplitude;
				freq = bin.frequency;
			}
		}
		
		System.out.println(new Settings("hh:mm:ss.lll").format(Duration.ofMillis((long) frame.frameStartMs)) + " " +
				"\tMax amplitude: " + (fft.fftParameters.useDecibelScale ? String.format("%.2f", amp).replace(',', '.') + " dB" : String.format("%.2f", amp).replace(',', '.')) + ", freq: " + Math.round(freq) + " Hz");
	}
}
