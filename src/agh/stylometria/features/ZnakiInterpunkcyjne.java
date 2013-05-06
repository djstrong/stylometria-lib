package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class ZnakiInterpunkcyjne extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// ilość znaków interpunkcyjnych / zdanie
		// TODO: kazdy osobno ?
		features.put("countZnakiInterpunkcyjne",
				(double) t.countPattern("[,.()?!:;\"'-]"));
		
		return features;
	}

}
