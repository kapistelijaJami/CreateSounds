package createsounds.timbre.filters;

public class HighPassFilter extends Filter {
	
	public HighPassFilter(double cutOff, double Q, double sampleRate) {
		super(cutOff, Q, sampleRate);
		
		a0 = 1 + alpha;
		a1 = -2 * Math.cos(w0);
		a2 = 1 - alpha;
		b0 = (1 + Math.cos(w0)) / 2;
		b1 = -(1 + Math.cos(w0));
		b2 = (1 + Math.cos(w0)) / 2;
	}
}
