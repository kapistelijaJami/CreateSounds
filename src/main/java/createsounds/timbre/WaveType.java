package createsounds.timbre;

import java.util.Random;

/**
 * Use this as a help when creating new waves: https://www.desmos.com/calculator/bo2lpfvjbg
 * (url changes on update)
 */
public enum WaveType {
	SINE_WAVE, TRIANGLE_WAVE, SQUARE_WAVE, SAWTOOTH_WAVE, RAMP_WAVE, PIANO_APPROX1_WAVE, PIANO_APPROX2_WAVE, PIANO_APPROX3_WAVE, PIANO_APPROX4_WAVE,
	MY_OWN1, MY_OWN2, MY_OWN3, HARMONICS, HARMONICS2, HARMONICS3, HARMONICS3_AND_SAWTOOTH, GUITAR, GUITAR2, CLARINET, NOISE;
	
	private static final Random rand = new Random();
	
	public double getValueByFrequencyAndTime(double frequency, double time) {
		switch (this) {
			case SINE_WAVE:
				return sineWave(frequency, time);
			case TRIANGLE_WAVE:
				return triangleWave(frequency, time);
			case SQUARE_WAVE:
				return squareWave(frequency, time);
			case SAWTOOTH_WAVE:
				return sawtoothWave(frequency, time);
			case RAMP_WAVE:
				return rampWave(frequency, time);
			case PIANO_APPROX1_WAVE:
				return pianoApprox1Wave(frequency, time);
			case PIANO_APPROX2_WAVE:
				return pianoApprox2Wave(frequency, time);
			case PIANO_APPROX3_WAVE:
				return pianoApprox3Wave(frequency, time);
			case PIANO_APPROX4_WAVE:
				return pianoApprox4Wave(frequency, time);
			case MY_OWN1:
				return myOwn1(frequency, time);
			case MY_OWN2:
				return mOwn2(frequency, time);
			case MY_OWN3:
				return myOwn3(frequency, time);
			case HARMONICS:
				return harmonics(frequency, time);
			case HARMONICS2:
				return harmonics2(frequency, time);
			case HARMONICS3:
				return harmonics3(frequency, time);
			case HARMONICS3_AND_SAWTOOTH:
				return harmonics3AndSawtooth(frequency, time);
			case GUITAR:
				return guitar(frequency, time);
			case GUITAR2:
				return guitar2(frequency, time);
			case CLARINET:
				return clarinet(frequency, time);
			case NOISE:
				return noise();
		}
		
		return 0;
	}

	private static double sineWave(double frequency, double time) {
		return Math.sin(toAngularFrequency(frequency) * time);
	}
	
	private static double triangleWave(double frequency, double time) {
		double period = frequencyToPeriod(frequency);
		return 4 / period * Math.abs(otherModulo((time - period / 4), period) - period / 2) - 1;
	}

	private double squareWave(double frequency, double time) {
		double val = sineWave(frequency, time);
		return val > 0 ? 1 : (val < 0 ? -1 : 0);
	}

	private double sawtoothWave(double frequency, double time) {
		double period = frequencyToPeriod(frequency);
		return 2 * (time / period - Math.floor(0.5 + time / period));
	}

	private double rampWave(double frequency, double time) {
		double period = frequencyToPeriod(frequency);
		return -2 * (time / period - Math.floor(0.5 + time / period));
	}

	private double pianoApprox1Wave(double frequency, double time) {
		double c = 1.1811037;

		double period = frequencyToPeriod(frequency) * 0.5;
		return Math.pow(Math.sin(Math.PI * (time / period) - c), 3) + Math.sin(Math.PI * (time / period + 2 / 3.0) - c);
	}

	private double pianoApprox2Wave(double frequency, double time) {			
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

	private double pianoApprox3Wave(double frequency, double time) {
		double val = 1 * Math.sin(time * toAngularFrequency(frequency)) * Math.exp(-0.0015 * time * toAngularFrequency(frequency))
				+ (0.2 * Math.sin(Math.PI * time * 1 * toAngularFrequency(frequency)) * Math.exp(-0.0004 * time * 2 * frequency * Math.PI));
		return val;
	}

	private double pianoApprox4Wave(double frequency, double time) {
		double val = 1 * Math.sin(time * toAngularFrequency(frequency)) * Math.exp(-0.0015 * time * toAngularFrequency(frequency))
				+ (0.2 * Math.sin( time * 0.5 * toAngularFrequency(frequency)) * Math.exp(-0.0004 * time * toAngularFrequency(frequency)));
		return val;
	}

	private double myOwn1(double frequency, double time) {
		return (sawtoothWave(frequency, time) + 2 * sineWave(frequency, time)) / 3.0;
	}

	private double mOwn2(double frequency, double time) {
		return 0.25 * sawtoothWave(frequency, time) + 0.65 * triangleWave(frequency, time) + 0.1 * sineWave(frequency, time);
	}

	private double myOwn3(double frequency, double time) {
		return (sawtoothWave(frequency, time) + triangleWave(frequency, time)) / 2.0;
	}

	private double harmonics(double frequency, double time) {
		double total = 0;

		for (int i = 1; i <= 40; i++) {
			total += 1.0 / i * Math.sin(i * toAngularFrequency(frequency) * time);
		}

		return total;
	}

	private double harmonics2(double frequency, double time) {
		double total = 0;

		for (int i = 1; i <= 40; i += 2) {
			total += 1.0 / i * Math.sin(i * toAngularFrequency(frequency) * time);
		}

		return total;
	}

	private double harmonics3(double frequency, double time) {
		double total = Math.sin(toAngularFrequency(frequency) * time);

		for (int i = 2; i <= 40; i += 2) {
			total += 1.0 / i * Math.sin(toAngularFrequency(i * frequency) * time);
		}

		return total;
	}

	private double harmonics3AndSawtooth(double frequency, double time) {
		return (sawtoothWave(frequency, time) + harmonics3(frequency, time)) / 2.0;
	}

	private double guitar(double frequency, double time) {
		double total = 0;

		for (int i = 1; i <= 40; i += 2) {
			total += 1.0 / i * triangleWave(i * frequency, time);
		}

		return total;
	}

	private double guitar2(double frequency, double time) {
		double total = triangleWave(frequency, time);

		for (int i = 2; i <= 40; i += 2) {
			total += 1.0 / i * triangleWave(i * frequency, time);
		}

		return total;
	}

	private double clarinet(double frequency, double time) {
		double total = 0;

		for (int i = 1; i <= 40; i += 2) {
			total += 1.0 / i * squareWave(i * frequency, time);
		}

		return total;
	}

	private double noise() {
		return 2 * rand.nextDouble() - 1;
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
