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
}
