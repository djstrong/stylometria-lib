package agh.stylometria.features;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import agh.stylometria.StringUtils;
import agh.stylometria.Text;

public class FormyGrzecznosciowe extends Feature {

	@Override
	public Map<String, Double> process(Text t) {
		Map<String, Double> features = new HashMap<String, Double>();
		
		// pisanie wielką literą form grzecznościowych
		String[] zwrotyGrzecznosciowe = new String[] { "ty", "ci", "ciebie",
				"cię", "tobą", "tobie", "wam", "wami", "was", "wy", "twój",
				"twa", "twą", "twe", "twego", "twej", "twemu", "twoi",
				"twoich", "twoim", "twoimi", "twoja", "twoją", "twoje",
				"twojego", "twojej", "twojemu", "twych", "twym", "twymi",
				"wasz", "wasi", "wasza", "waszą", "wasze", "waszego", "waszej",
				"waszemu", "waszych", "waszym", "waszymi", "pan", "pana",
				"panem", "panie", "panu", "panach", "panami", "panom", "panów",
				"panowie", "pany", "pani", "pań", "panie", "panią", "paniach",
				"paniami", "paniom", "państwo", "państw", "państwa",
				"państwem", "państwu", "państwach", "państwami", "państwom" };
		features.put("countZwrotyGrzecznoscioweMala", (double) t
				.countWordsWoDiacritics(new HashSet<String>(Arrays
						.asList(zwrotyGrzecznosciowe))));

		HashSet<String> zw = new HashSet<String>(zwrotyGrzecznosciowe.length);
		for (String zwrot : zwrotyGrzecznosciowe)
			zw.add(StringUtils.capitalize(zwrot));
		features.put("countZwrotyGrzecznoscioweWielka",
				(double) t.countWordsWoDiacritics(zw));
		
		return features;
	}

}
