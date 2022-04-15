package createsounds.sound_creation;

import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public class Multisample implements Iterable<Integer> {
	private ArrayList<Integer> samples;
	
	public Multisample() {
		samples = new ArrayList<>();
	}
	
	public Multisample(int sample) {
		samples = new ArrayList<>();
		samples.add(sample);
	}
	
	public Multisample add(int sample) {
		samples.add(sample);
		return this;
	}
	
	public int get(int i) {
		return samples.get(i);
	}
	
	public int size() {
		return samples.size();
	}
	
	public int total() {
		int total = 0;
		for (int sample : samples) {
			total += sample;
		}
		
		return total;
	}

	@Override
	public Iterator<Integer> iterator() {
		return samples.iterator();
	}
}
