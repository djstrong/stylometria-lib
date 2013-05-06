package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class ZnakiDiakrytyczne extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		features.put("countZnakiDiakrytyczne",
				(double) t.countPattern("[ąśżźćęńłóĄŚŻŹĆĘŃŁÓ]"));
		
		return features;
	}

}
