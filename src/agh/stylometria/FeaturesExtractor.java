package agh.stylometria;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.BreakIterator;

public class FeaturesExtractor {

	public static double[] features(String text) {
		double[] features = new double[1];

		Text t = new Text(text);

		// TODO: wielkie i male litery

		// płeć - końcówki łam, łem
		int countLem = t.countWordsEndsWith("łem");
		int countLam = t.countWordsEndsWith("łam");

		// wulgaryzmy
		String[] wulgaryzmy = new String[] { "kurwa" };
		int countWulgaryzmy = t.countWords(new HashSet<String>(Arrays
				.asList(wulgaryzmy)));

		// znaki diakrytyczne
		int countZnakiDiakrytyczne = t.countPattern("[ąśżźćęńłóĄŚŻŹĆĘŃŁÓ]");

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
				"państwem", "państwu", "państwach", "państwami", "państwom"};
		int countZwrotyGrzecznoscioweMala = t.countWords(new HashSet<String>(Arrays
				.asList(zwrotyGrzecznosciowe)));
		//TODO: wielka

		// emotikony
		//TODO: kazda osobno ?
		
		// ilość słów / zdanie
		int countWords = t.countWords();
		int countSentences = t.countSentences();
		
		// ilość znaków interpunkcyjnych / zdanie
		//TODO: kazdy osobno ?
		int countZnakiInterpunkcyjne = t.countPattern("[,.()?!:;\"'-]");
		
		// ilość liter w słowach (częstość występowania słów o określonej długości)
		
		// ilość poszczególnych znaków (raczej specjalnych)
		
		// pokemony
		
		// common words, functional words - np. jest, na, w, dla - najlepsze wyniki
		
		return features;
	}

	public static void main(String[] args) {

	}
}
