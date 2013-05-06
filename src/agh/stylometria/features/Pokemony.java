package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Sentence;
import agh.stylometria.Text;

public class Pokemony extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// pokemony - pomijajac pierwsza litere czy slowo ma male i wielkie
		// litery
		int count = 0;
		for (Sentence s : t.sentences) {
			for (String w : s.words) {
				boolean mala = false;
				boolean wielka = false;
				for (int i = 1; i < w.length() - 1; ++i) {
					String letter = w.substring(i, i + 1);
					if (letter == letter.toLowerCase())
						mala = true;
					else
						wielka = true;
				}
				if (mala && wielka)
					++count;
			}
		}
		features.put("countPokemony", (double) count);
		
		return features;
	}

}
