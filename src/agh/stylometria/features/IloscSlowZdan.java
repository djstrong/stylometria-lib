package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class IloscSlowZdan extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// ilość słów / zdanie
		features.put("countWords", (double) t.countWords());
		features.put("countSentences", (double) t.countSentences());
		
		return features;
	}

}
