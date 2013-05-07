package agh.stylometria;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import agh.stylometria.features.DlugoscSlow;
import agh.stylometria.features.Emotikony;
import agh.stylometria.features.Feature;
import agh.stylometria.features.FormyGrzecznosciowe;
import agh.stylometria.features.FunctionWords;
import agh.stylometria.features.IloscSlowZdan;
import agh.stylometria.features.Plec;
import agh.stylometria.features.Pokemony;
import agh.stylometria.features.Wulgaryzmy;
import agh.stylometria.features.ZnakiDiakrytyczne;
import agh.stylometria.features.ZnakiInterpunkcyjne;

public class FeaturesExtractor {
	private static List<Feature> listOfFeatures = new ArrayList<Feature>();
	static {
		listOfFeatures.add(new DlugoscSlow());
		listOfFeatures.add(new Emotikony());
		listOfFeatures.add(new FormyGrzecznosciowe());
		listOfFeatures.add(new Plec());
		listOfFeatures.add(new Pokemony());
		listOfFeatures.add(new Wulgaryzmy());
		listOfFeatures.add(new ZnakiDiakrytyczne());
		listOfFeatures.add(new ZnakiInterpunkcyjne());
		listOfFeatures.add(new IloscSlowZdan());
		listOfFeatures.add(new FunctionWords());
	}

	public static Map<String, Double> features(String text) {
		Map<String, Double> features = new HashMap<String, Double>();

		Text t = new Text(text);
		System.out.println(t);
		for (Feature f : listOfFeatures) {
			features.putAll(f.process(t));
		}

		Map<String, Double> new_features = new HashMap<String, Double>();

		double value;
		for (Entry<String, Double> entry : features.entrySet()) {
			new_features.put(entry.getKey(), entry.getValue());

			value = features.get(IloscSlowZdan.nameWords);
			if (value == 0.0)
				value = 1.0;
			new_features.put(
					entry.getKey() + "PRZEZ" + IloscSlowZdan.nameWords,
					entry.getValue() / value);

			value = features.get(IloscSlowZdan.nameSentences);
			if (value == 0.0)
				value = 1.0;
			new_features.put(entry.getKey() + "PRZEZ"
					+ IloscSlowZdan.nameSentences, entry.getValue() / value);
		}

		// ilość poszczególnych znaków (raczej specjalnych)

		return new_features;
	}

	public static void main(String[] args) {
		Map<String, Double> f = features("kiedy xP x-D :-D :( :(((( ;)kierowca samochodu stracił panowanie? :) I Ci ludzie ciągle piją do wszystkich, że nie chcą ich przepuszczać na skrzyżowaniach, że kierowcy samochodów nie chcą wjeżdżać na chodniki, aby przepuścić przepychającego się w korku motocykla? CO ZA HIPOKRYZJA!");
		for (String k : f.keySet()) {
			System.out.println(k + " -> " + f.get(k));
		}
		System.out.println(f.size());
	}
}
