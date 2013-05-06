package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class Plec extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		// płeć - końcówki łam, łem
		Map<String, Double> features = new HashMap<String, Double>();
		
		features.put("countL'em",
				(double) t.countWordsEndsWithIgnoreCase("łem"));
		features.put("countL'am",
				(double) t.countWordsEndsWithIgnoreCase("łam"));
		features.put("countLem", (double) t.countWordsEndsWithIgnoreCase("lem"));
		features.put("countLam", (double) t.countWordsEndsWithIgnoreCase("lam"));
		
		return features;
	}

}
