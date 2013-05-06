package agh.stylometria;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import agh.stylometria.features.DlugoscSlow;
import agh.stylometria.features.Emotikony;
import agh.stylometria.features.Feature;
import agh.stylometria.features.FormyGrzecznosciowe;
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
	}

	public static Map<String, Double> features(String text) {
		Map<String, Double> features = new HashMap<String, Double>();

		Text t = new Text(text);

		for (Feature f : listOfFeatures) {
			features.putAll(f.process(t));
		}

		// ilość poszczególnych znaków (raczej specjalnych)

		// common words, functional words - np. jest, na, w, dla - najlepsze
		// wyniki

		// truche duzo spojnikow i przyimkow

		return features;
	}

	public static void main(String[] args) {
		Map<String, Double> f = features("kiedy xP x-D :-D :( :(((( ;)kierowca samochodu stracił panowanie? :) I Ci ludzie ciągle piją do wszystkich, że nie chcą ich przepuszczać na skrzyżowaniach, że kierowcy samochodów nie chcą wjeżdżać na chodniki, aby przepuścić przepychającego się w korku motocykla? CO ZA HIPOKRYZJA!");
		for (String k : f.keySet()) {
			System.out.println(k + " -> " + f.get(k));
		}
	}
}
