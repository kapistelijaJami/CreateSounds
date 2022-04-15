package createsounds.timbre.filters;

public class NotchFilter extends Filter {
	
	public NotchFilter(double cutOff, double Q, double sampleRate) {
		super(cutOff, Q, sampleRate);
		
		a0 = 1 + alpha;
		a1 = -2 * Math.cos(w0);
		a2 = 1 - alpha;
		b0 = 1;
		b1 = -2 * Math.cos(w0);
		b2 = 1;
	}
}
