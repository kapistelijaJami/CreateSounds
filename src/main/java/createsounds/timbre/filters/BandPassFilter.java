package createsounds.timbre.filters;

public class BandPassFilter extends Filter {
	
	public BandPassFilter(double cutOff, double Q, double sampleRate) {
		super(cutOff, Q, sampleRate);
		
		//There's another version of this one, it just removes the Q multipliers. Supposed to change the max value etc.
		a0 = 1 + alpha;
		a1 = -2 * Math.cos(w0);
		a2 = 1 - alpha;
		b0 = Q * alpha;
		b1 = 0;
		b2 = Q * -alpha;
	}
}
