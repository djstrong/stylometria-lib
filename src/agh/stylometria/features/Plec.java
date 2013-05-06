package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.Text;

public class Plec extends Feature {
	public final static String nameLeem = "countL'em";
	public final static String nameLaam = "countL'am";
	public final static String nameLem = "countLem";
	public final static String nameLam = "countLam";

	@Override
	public Map<String, Double> process(final Text t) {
		// płeć - końcówki łam, łem
		Map<String, Double> features = new HashMap<String, Double>();

		features.put(nameLeem, (double) t.countWordsEndsWithIgnoreCase("łem"));
		features.put(nameLaam, (double) t.countWordsEndsWithIgnoreCase("łam"));
		features.put(nameLem, (double) t.countWordsEndsWithIgnoreCase("lem"));
		features.put(nameLam, (double) t.countWordsEndsWithIgnoreCase("lam"));

		return features;
	}

}
