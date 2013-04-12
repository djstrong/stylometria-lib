package agh.stylometria;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sentence {
	public final String sentence;
	public final List<String> words = new ArrayList<String>();

	public Sentence(final String sentence) {
		this.sentence = sentence;

		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(sentence);
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
				.next()) {
			words.add(sentence.substring(start, end));
		}
	}

	public int countWords() {
		return words.size(); // TODO: tylko slowa
	}

	public int countWords(final Set<String> bag) {
		int count = 0;
		for (String word : words)
			if (bag.contains(word))
				++count;
		return count;
	}
	
	public int countWordsEndsWith(final String suffix) {
		int count = 0;
		for (String word : words)
			if (word.endsWith(suffix))
				++count;
		return count;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word);
			sb.append(", ");
		}
		return sb.toString();
	}
}
