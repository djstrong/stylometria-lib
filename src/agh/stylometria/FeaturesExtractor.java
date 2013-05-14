package agh.stylometria;

import java.io.FileNotFoundException;
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

	public static void process(String in, String out) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(in));
		String[] nextLine;

		List<String> header = new LinkedList<String>();
		Map<String, Double> f = features("");
		for (String h : f.keySet())
			header.add(h);
		header.add("author");

		CSVWriter writer = new CSVWriter(new FileWriter(out), ',',
				CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
		writer.writeNext(header.toArray(new String[0]));
		header.remove("author");

		String author;
		int c = 0;
		while ((nextLine = reader.readNext()) != null) {
			f = features(nextLine[1].toString());
			author = nextLine[0].toString();

			List<String> entries = new LinkedList<String>();

			for (String key : header) {
				Object val = f.get(key) == null ? 0.0 : f.get(key);
				entries.add(val.toString());
			}
			entries.add(author);
			writer.writeNext(entries.toArray(new String[0]));
			writer.flush();
			++c;

			if (nextLine.length > 2) {
				System.out.println(c);
				System.out.println(nextLine[0].toString());
				System.out.println(nextLine[1].toString());
				System.out.println(nextLine[2].toString());
				System.out.println();
			}

			if (c % 100 == 0) {
				System.out.println(c);

			}
		}

		writer.close();

	}

	public static void main(String[] args) throws Exception {
		process("comments_1000.csv", "f3.csv");
		// process("comments_test.csv", "ctest.csv");
		// process("comments_train.csv", "ctrain.csv");
	}
}
