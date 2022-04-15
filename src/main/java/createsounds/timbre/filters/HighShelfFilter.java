package createsounds.timbre.filters;

public class HighShelfFilter extends Filter {
	
	public HighShelfFilter(double cutOff, double Q, double sampleRate, double dBgain) {
		super(cutOff, Q, sampleRate);
		
		double A = Math.pow(10, dBgain / 40);
		
		a0 = (A + 1) - (A - 1) * Math.cos(w0) + 2 * Math.sqrt(A) * alpha;
		a1 = 2 * ((A - 1) - (A + 1) * Math.cos(w0));
		a2 = (A + 1) - (A - 1) * Math.cos(w0) - 2 * Math.sqrt(A) * alpha;
		b0 = A * ((A + 1) + (A - 1) * Math.cos(w0) + 2 * Math.sqrt(A) * alpha);
		b1 = -2 * A * ((A - 1) + (A + 1) * Math.cos(w0));
		b2 = A * ((A + 1) + (A - 1) * Math.cos(w0) - 2 * Math.sqrt(A) * alpha);
	}
}
