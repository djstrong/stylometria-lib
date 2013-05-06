package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class ZnakiDiakrytyczne extends Feature {
	public final static String name = "countZnakiDiakrytyczne";

	@Override
	public Map<String, Double> process(final Text t) {
		Map<String, Double> features = new HashMap<String, Double>();

		features.put(name, (double) t.countPattern("[ąśżźćęńłóĄŚŻŹĆĘŃŁÓ]"));

		return features;
	}

}
