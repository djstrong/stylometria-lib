package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class ZnakiInterpunkcyjne extends Feature {
	public final static String name = "countZnakiInterpunkcyjne";
	@Override
	public Map<String, Double> process(final Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// ilość znaków interpunkcyjnych / zdanie
		// TODO: kazdy osobno ?
		features.put(name,
				(double) t.countPattern("[,.()?!:;\"'-]"));
		
		return features;
	}

}
