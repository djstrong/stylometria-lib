package agh.stylometria;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Text {
	public final String text;
	public final List<Sentence> sentences = new ArrayList<Sentence>();

	public Text(final String text) {
		this.text = text;

		BreakIterator boundary = BreakIterator.getSentenceInstance();
		boundary.setText(text);
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
				.next()) {
			sentences.add(new Sentence(text.substring(start, end)));
		}
	}

	public int countSentences() {
		return sentences.size();
	}

	public int countWords() {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWords();
		return count;
	}

	public int countWordsEndsWith(final String suffix) {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWordsEndsWith(suffix);
		return count;
	}

	public int countWordsEndsWithIgnoreCase(final String suffix) {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWordsEndsWithIgnoreCase(suffix);
		return count;
	}

	public int countWords(final Set<String> bag) {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWords(bag);
		return count;
	}

	public int countWordsLower(final Set<String> bag) {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWordsLower(bag);
		return count;
	}

	public int countWordsLowerWoDiacritics(final Set<String> bag) {
		int count = 0;
		for (Sentence s : sentences)
			count += s.countWordsLowerWoDiacritics(bag);
		return count;
	}

	public int countWordsWoDiacritics(final Set<String> bag) {
		int count = 0;
		for (Sentence s : sentences) {
			count += s.countWordsWoDiacritics(bag);
		}
		return count;
	}

	public int countPatterns(final List<String> patterns) {
		return StringUtils.countPatterns(text, patterns);
	}

	public int countPattern(String pattern) {
		return StringUtils.countPattern(text, pattern);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Sentence s : sentences) {
			sb.append(s);
			sb.append("\n");
		}
		return sb.toString();
	}

	// words iterator
	public static void main(String[] args) {
		Text t = new Text(
				"kiedy kierowca samochodu stracił panowanie? :) I Ci ludzie ciągle piją do wszystkich, że nie chcą ich przepuszczać na skrzyżowaniach, że kierowcy samochodów nie chcą wjeżdżać na chodniki, aby przepuścić przepychającego się w korku motocykla? CO ZA HIPOKRYZJA!");
		System.out.println(t);
	}
}
