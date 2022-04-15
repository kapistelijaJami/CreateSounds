package createsounds.sound_creation;

import java.util.ArrayList;

@Deprecated
public class MultisampleArray {
	private ArrayList<Multisample> multisamples;
	
	public MultisampleArray() {
		multisamples = new ArrayList<>();
	}
	
	public void add(int i, int sample) {
		while (multisamples.size() <= i) {
			multisamples.add(new Multisample());
		}
		multisamples.get(i).add(sample);
	}
	
	public int size() {
		return multisamples.size();
	}
	
	public int sizeOf(int i) {
		if (multisamples.size() <= i) {
			return 0;
		}
		return multisamples.get(i).size();
	}
	
	public void insertSilenceBeginning(int nbrOfSamples) {
		ArrayList<Multisample> list = new ArrayList<>();
		for (int i = 0; i < nbrOfSamples; i++) {
			list.add(new Multisample(0));
		}
		multisamples.addAll(0, list);
	}
	
	public void addSilenceEnd(int nbrOfSamples) {
		for (int i = 0; i < nbrOfSamples; i++) {
			multisamples.add(new Multisample(0));
		}
	}
	
	public int[] combineAll(float amplitude) {
		int[] allSamples = new int[size()];
		
		for (int i = 0; i < multisamples.size(); i++) {
			allSamples[i] = multisamples.get(i).total();
		}
		
		CreateSounds.preventClipping(allSamples, amplitude);
		
		return allSamples;
	}
}
