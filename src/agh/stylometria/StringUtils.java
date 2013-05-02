package agh.stylometria;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static int countPatterns(final String text,
			final List<String> patterns) {
		int count = 0;
		for (String pattern : patterns)
			count += countPattern(text, pattern);
		return count;
	}

	public static int countPattern(final String text, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		int count = 0;
		while (m.find())
			++count;
		return count;
	}

	public static String removeDiacritics(String text) {
		StringBuffer sb = new StringBuffer(text);

		String[][] zamiany = { { "ą", "a" }, { "ć", "c" }, { "ę", "e" },
				{ "ł", "l" }, { "ń", "n" }, { "ó", "o" }, { "ś", "s" },
				{ "ź", "z" }, { "ż", "z" } };
		for (String[] zamiana : zamiany) {
			sb = replace(sb, zamiana[0], zamiana[1], -1);
			sb = replace(sb, zamiana[0].toUpperCase(),
					zamiana[1].toUpperCase(), -1);
		}
		return sb.toString();
	}

	public static StringBuffer replace(StringBuffer text, String searchString,
			String replacement, int max) {
		// TODO: slabe
		if (isEmpty(text) || isEmpty(searchString) || replacement == null
				|| max == 0) {
			return text;
		}
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == -1) {
			return text;
		}
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = (increase < 0 ? 0 : increase);
		increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
		StringBuffer buf = new StringBuffer(text.length() + increase);
		while (end != -1) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isEmpty(StringBuffer str) {
		return str == null || str.length() == 0;
	}

	public static String capitalize(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
}
