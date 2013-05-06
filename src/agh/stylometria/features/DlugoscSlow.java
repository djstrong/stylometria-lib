package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Sentence;
import agh.stylometria.Text;

public class DlugoscSlow extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// ilość liter w słowach (częstość występowania słów o określonej
		// długości)
		int max = 20;
		int[] slowa = new int[max];
		for (int i = 0; i < max; ++i) {
			slowa[i] = 0;
		}
		for (Sentence s : t.sentences) {
			for (String w : s.words) {
				if (w.length() < max)
					slowa[w.length()]++;
			}
		}
		for (int i = 0; i < max; ++i) {
			features.put("countDlugosc" + i, (double) slowa[i]);
		}
		
		return features;
	}

}
