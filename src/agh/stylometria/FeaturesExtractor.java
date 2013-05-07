package agh.stylometria;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
		// System.out.println(t);
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

	public static void main(String[] args) throws Exception {
		CSVReader reader = new CSVReader(new FileReader("comments.csv"));
		String[] nextLine;
		List<String> header = new LinkedList<String>();

		// ICsvMapWriter writer = new CsvMapWriter(new
		// FileWriter("test.csv"),CsvPreference.STANDARD_PREFERENCE);
		List<Map<String, Double>> featuresMaps = new ArrayList<Map<String, Double>>();
		List<String> authors = new ArrayList<String>();
		int c = 0;
		while ((nextLine = reader.readNext()) != null) {
			Map<String, Double> f = features(nextLine[1].toString());
			authors.add(nextLine[0].toString());
			featuresMaps.add(f);
			++c;
			if (c % 100 == 0)
				System.out.println(c);
		}

		// header
		int max = 0;
		Map<String, Double> headerMap = null;
		for (Map<String, Double> m : featuresMaps) {
			if (m.keySet().size() > max) {
				max = m.keySet().size();
				headerMap = m;
			}
		}

		for (Object k : headerMap.keySet()) {
			header.add(k.toString());
		}
		header.add("author");

		CSVWriter writer = new CSVWriter(new FileWriter("test.csv"), ',',
				CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
		writer.writeNext(header.toArray(new String[0]));
		header.remove("author");
		
		for (int i = 0; i < featuresMaps.size(); i++) {
			Map<String, Double> m = featuresMaps.get(i);
			List<String> entries = new LinkedList<String>();

			for (String key : header) {
				Object val = m.get(key) == null ? 0.0 : m.get(key);
				entries.add(val.toString());
			}
			entries.add(authors.get(i).toString());
			writer.writeNext(entries.toArray(new String[0]));

		}
		writer.close();
	}
}
