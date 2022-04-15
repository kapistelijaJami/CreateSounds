package createsounds.timbre;

import java.util.Random;


@Deprecated
public class Waves {
	private static final Random rand = new Random();
	
	/**
	 * Gets a value of the sample from specified wave type by frequency and time.
	 * @param frequency In Hz
	 * @param time In seconds. Use sample rate (samples per second) to convert sample indexes to time. Sample index / sample rate = time;
	 * @param waveType
	 * @return 
	 */
	public static double getValueByFrequencyTimeAndWaveType(double frequency, double time, WaveType waveType) {
		switch (waveType) {
			case SINE_WAVE:
				return SineWave.getValueByFrequencyAndTime(frequency, time);
			case TRIANGLE_WAVE:
				return TrianleWave.getValueByFrequencyAndTime(frequency, time);
			case SQUARE_WAVE:
				return SquareWave.getValueByFrequencyAndTime(frequency, time);
			case SAWTOOTH_WAVE:
				return SawtoothWave.getValueByFrequencyAndTime(frequency, time);
			case RAMP_WAVE:
				return RampWave.getValueByFrequencyAndTime(frequency, time);
			case PIANO_APPROX1_WAVE:
				return PianoApprox1Wave.getValueByFrequencyAndTime(frequency, time);
			case PIANO_APPROX2_WAVE:
				return PianoApprox2Wave.getValueByFrequencyAndTime(frequency, time);
			case PIANO_APPROX3_WAVE:
				return PianoApprox3Wave.getValueByFrequencyAndTime(frequency, time);
			case PIANO_APPROX4_WAVE:
				return PianoApprox4Wave.getValueByFrequencyAndTime(frequency, time);
			case MY_OWN1:
				return MyOwn1.getValueByFrequencyAndTime(frequency, time);
			case MY_OWN2:
				return MyOwn2.getValueByFrequencyAndTime(frequency, time);
			case MY_OWN3:
				return MyOwn3.getValueByFrequencyAndTime(frequency, time);
			case HARMONICS:
				return Harmonics.getValueByFrequencyAndTime(frequency, time);
			case HARMONICS2:
				return Harmonics2.getValueByFrequencyAndTime(frequency, time);
			case HARMONICS3:
				return Harmonics3.getValueByFrequencyAndTime(frequency, time);
			case HARMONICS3_AND_SAWTOOTH:
				return Harmonics3AndSawtooth.getValueByFrequencyAndTime(frequency, time);
			case GUITAR:
				return Guitar.getValueByFrequencyAndTime(frequency, time);
			case GUITAR2:
				return Guitar2.getValueByFrequencyAndTime(frequency, time);
			case CLARINET:
				return Clarinet.getValueByFrequencyAndTime(frequency, time);
			case NOISE:
				return Noise.getValueByFrequencyAndTime(frequency, time);
		}
		return 0;
	}
	
	private static class SineWave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double dampeningFactor = 0; //-40 or even higher are very dampened, might need to increase the main function so it's not too quiet.
			return Math.sin(toAngularFrequency(frequency) * time) * Math.exp(dampeningFactor * time);
		}
	}
	
	private static class TrianleWave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double period = frequencyToPeriod(frequency);
			return 4 / period * Math.abs(otherModulo((time - period / 4), period) - period / 2) - 1;
		}
	}
	
	private static class SquareWave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double val = SineWave.getValueByFrequencyAndTime(frequency, time);
			return val > 0 ? 1 : (val < 0 ? -1 : 0);
		}
	}
	
	private static class SawtoothWave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double period = frequencyToPeriod(frequency);
			return 2 * (time / period - Math.floor(0.5 + time / period));
		}
	}
	
	private static class RampWave { //not sure if there's an actual difference to sawtooth, other than looks.
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double period = frequencyToPeriod(frequency);
			return -2 * (time / period - Math.floor(0.5 + time / period));
		}
	}
	
	private static class PianoApprox1Wave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double c = 1.1811037;
			
			double period = frequencyToPeriod(frequency) * 0.5;
			return Math.pow(Math.sin(Math.PI * (time / period) - c), 3) + Math.sin(Math.PI * (time / period + 2 / 3.0) - c);
		}
	}
	
	private static class PianoApprox2Wave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {			
			double y = Math.sin(toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time);
			y += Math.sin(2 * toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time) / 2;
			y += Math.sin(3 * toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time) / 4;
			y += Math.sin(4 * toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time) / 8;
			y += Math.sin(5 * toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time) / 16;
			y += Math.sin(6 * toAngularFrequency(frequency) * time) * Math.exp(-0.0004 * toAngularFrequency(frequency) * time) / 32;
			
			y /= 3.0;
			
			y *= 1 + 16 * time * Math.exp(-6 * time);
			
			return y;
		}
	}
	
	private static class PianoApprox3Wave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double val = 1 * Math.sin(time * toAngularFrequency(frequency)) * Math.exp(-0.0015 * time * toAngularFrequency(frequency))
					+ (0.2 * Math.sin(Math.PI * time * 1 * toAngularFrequency(frequency)) * Math.exp(-0.0004 * time * 2 * frequency * Math.PI));
			return val;
		}
	}
	
	private static class PianoApprox4Wave {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double val = 1 * Math.sin(time * toAngularFrequency(frequency)) * Math.exp(-0.0015 * time * toAngularFrequency(frequency))
					+ (0.2 * Math.sin( time * 0.5 * toAngularFrequency(frequency)) * Math.exp(-0.0004 * time * toAngularFrequency(frequency)));
			return val;
		}
	}
	
	private static class MyOwn1 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			return (SawtoothWave.getValueByFrequencyAndTime(frequency, time) + 2 * SineWave.getValueByFrequencyAndTime(frequency, time)) / 3.0;
		}
	}
	
	private static class MyOwn2 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			return 0.25 * SawtoothWave.getValueByFrequencyAndTime(frequency, time) + 0.65 * TrianleWave.getValueByFrequencyAndTime(frequency, time) + 0.1 * SineWave.getValueByFrequencyAndTime(frequency, time);
		}
	}
	
	private static class MyOwn3 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			return (SawtoothWave.getValueByFrequencyAndTime(frequency, time) + TrianleWave.getValueByFrequencyAndTime(frequency, time)) / 2.0;
		}
	}
	
	private static class Harmonics {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = 0;
			
			for (int i = 1; i <= 40; i++) {
				total += 1.0 / i * Math.sin(i * toAngularFrequency(frequency) * time);
			}
			
			return total;
		}
	}
	
	private static class Harmonics2 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = 0;
			
			for (int i = 1; i <= 40; i += 2) {
				total += 1.0 / i * Math.sin(i * toAngularFrequency(frequency) * time);
			}
			
			return total;
		}
	}
	
	private static class Harmonics3 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = Math.sin(toAngularFrequency(frequency) * time);
			
			for (int i = 2; i <= 40; i += 2) {
				total += 1.0 / i * Math.sin(toAngularFrequency(i * frequency) * time);
			}
			
			return total;
		}
	}
	
	private static class Harmonics3AndSawtooth {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			return (SawtoothWave.getValueByFrequencyAndTime(frequency, time) + Harmonics3.getValueByFrequencyAndTime(frequency, time)) / 2.0;
		}
	}
	
	//I DONT THINK THIS MAKES SENSE. Triangle wave already has sine harmonics. Not sure what this even does, maybe just amplifies the harmonic parts. Same for Guitar2 and Clarinet.
	private static class Guitar {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = 0;
			
			for (int i = 1; i <= 40; i += 2) {
				total += 1.0 / i * TrianleWave.getValueByFrequencyAndTime(i * frequency, time);
			}
			
			return total;
		}
	}
	
	private static class Guitar2 {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = TrianleWave.getValueByFrequencyAndTime(frequency, time);
			
			for (int i = 2; i <= 40; i += 2) {
				total += 1.0 / i * TrianleWave.getValueByFrequencyAndTime(i * frequency, time);
			}
			
			return total;
		}
	}
	
	private static class Clarinet {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			double total = 0;
			
			for (int i = 1; i <= 40; i += 2) {
				total += 1.0 / i * SquareWave.getValueByFrequencyAndTime(i * frequency, time);
			}
			
			return total;
		}
	}
	
	private static class Noise {
		public static double getValueByFrequencyAndTime(double frequency, double time) {
			return 2 * rand.nextDouble() - 1;
		}
	}
	
	public static double periodToFrequency(double period) {
		return 1 / period;
	}
	
	public static double frequencyToPeriod(double frequency) {
		return 1 / frequency;
	}
	
	public static double toAngularFrequency(double frequency) {
		return 2 * Math.PI * frequency;
	}
	
	public static double otherModulo(double a, double b) {
		return a - b * (Math.floor(a / b));
	}
}
