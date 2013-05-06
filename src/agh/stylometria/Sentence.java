package agh.stylometria;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sentence {
	public final String sentence;
	public final List<String> words = new ArrayList<String>();
	public final List<String> wordsLower = new ArrayList<String>();
	public final List<String> wordsLowerWoDiacritics = new ArrayList<String>();
	public final List<String> wordsWoDiacritics = new ArrayList<String>();
	public final List<String> onlyWords = new ArrayList<String>();

	public Sentence(final String sentence) {
		this.sentence = sentence;

		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(sentence);
		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
				.next()) {
			String word = sentence.substring(start, end);
			words.add(word);
			wordsLower.add(word.toLowerCase());
			wordsLowerWoDiacritics.add(StringUtils.removeDiacritics(word
					.toLowerCase()));
			wordsWoDiacritics.add(StringUtils.removeDiacritics(word));

			if (StringUtils.countPattern(word, "[a-zA-Z]") > 0)
				onlyWords.add(word);
		}
	}

	public int countWords() {
		return onlyWords.size();
	}

	public int countWords(final Set<String> bag) {
		int count = 0;
		for (String word : words)
			if (bag.contains(word))
				++count;
		return count;
	}

	public int countWordsLower(final Set<String> bag) {
		int count = 0;
		for (String word : wordsLower)
			if (bag.contains(word))
				++count;
		return count;
	}

	public int countWordsLowerWoDiacritics(final Set<String> bag) {
		int count = 0;
		for (String word : wordsLowerWoDiacritics)
			if (bag.contains(word))
				++count;
		return count;
	}

	public int countWordsLowerWoDiacritics(final String otherWord) {
		int count = 0;
		for (String word : wordsLowerWoDiacritics)
			if (otherWord.equals(word))
				++count;
		return count;
	}

	public int countWordsWoDiacritics(final Set<String> bag) {
		int count = 0;
		for (String word : wordsWoDiacritics) {

			if (bag.contains(word))
				++count;
		}
		return count;
	}

	public int countWordsEndsWith(final String suffix) {
		int count = 0;
		for (String word : words)
			if (word.endsWith(suffix))
				++count;
		return count;
	}

	public int countWordsEndsWithIgnoreCase(String suffix) {
		suffix = suffix.toLowerCase();
		int count = 0;
		for (String word : wordsLower)
			if (word.endsWith(suffix))
				++count;
		return count;
	}

	public int countPatterns(final List<String> patterns) {
		return StringUtils.countPatterns(sentence, patterns);
	}

	public int countPattern(String pattern) {
		return StringUtils.countPattern(sentence, pattern);
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
