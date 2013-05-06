package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class IloscSlowZdan extends Feature {
	public final static String nameWords = "countWords";
	public final static String nameSentences = "countSentences";

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();

		// ilość słów / zdanie
		features.put(nameWords, (double) t.countWords());
		features.put(nameSentences, (double) t.countSentences());

		return features;
	}

}
