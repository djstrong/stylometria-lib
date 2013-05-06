package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class Emotikony extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// emotikony
		// TODO: kazda osobno ?
		features.put("emoticons",
				(double) t.countPattern("(:|;|x|X){1}-?(\\)|\\(|D|P|d|p)"));
		
		return features;
	}

}
