package agh.stylometria.features;

import java.util.HashMap;
import java.util.Map;

import agh.stylometria.StringUtils;
import agh.stylometria.Text;

public class FunctionWords extends Feature {
	public final static String name = "functionWord_";

	@Override
	public Map<String, Double> process(final Text t) {

		Map<String, Double> features = new HashMap<String, Double>();

		String[] spojnikiZaimki = new String[] { "natomiast", "póty",
				"inaczej", "zarówno", "toteż", "a", "abo", "jednak", "i",
				"zaś", "niżli", "to", "jako", "bądź", "niźli", "tymczasem",
				"ergo", "dopóty", "niby", "tudzież", "ewentualnie", "dopoty",
				"tym", "nie", "zwłaszcza", "aniżeli", "zamiast", "aliści",
				"atoli", "czym", "ani", "wszakże", "tylko", "niczym", "zasię",
				"oraz", "alić", "jakokolwiek", "lub", "czy", "jakoli",
				"namiast", "albo", "zasie", "wszakżeż", "mianowicie", "ali",
				"ni", "alias", "jakoż", "ale", "vel", "lecz", "więc",
				"dopotąd", "przeto", "jak", "niżeli", "zatem", "czyli", "tedy",
				"niż", "miast", "gdyż", "zanim", "acz", "chociaj", "póki",
				"dopokąd", "kieby", "choćby", "ledwo", "jeżeliby", "im", "aby",
				"aże", "jakby", "gdyby", "chocia", "byle", "pókiż", "aż",
				"dopoki", "aczkolwiek", "albowiem", "jakożkolwiek", "nim",
				"lubo", "jeśli", "dopókąd", "iżby", "jakkolwiek", "jeżeli",
				"choć", "przetoż", "jeźliby", "żeby", "byleby", "zaledwo",
				"ponieważ", "że", "chociaż", "kiedy", "co", "ażeby", "podwiel",
				"ledwie", "skoro", "jeśliby", "gdybyż", "jeźli", "bowiem",
				"bo", "zaledwie", "iże", "chociażby", "jakoby", "dopóki", "by",
				"iż", "kromia", "kromie", "naokoło", "wewnątrz", "bez",
				"kontra", "przez", "popod", "przed", "zśród", "sprzed", "a",
				"wskutek", "sponad", "spoza", "poza", "sponad", "pośrodku",
				"wskróś", "niżli", "spod", "wbrew", "wzdłuż", "jako",
				"podczas", "niźli", "koło", "ad", "przy", "prze", "po", "spod",
				"u", "krom", "według", "pośród", "pod", "niby", "loko",
				"zewnątrz", "spopod", "la", "poniżej", "nad", "pomiędzy", "z",
				"popod", "wokoło", "skroś", "wokół", "kwoli", "między", "w",
				"aniżeli", "zamiast", "sprzed", "opodal", "spopod", "od",
				"dzięki", "wśrzód", "do", "ku", "dokoła", "spośród", "pomimo",
				"względem", "oprócz", "od", "poprzez", "spomiędzy", "niczym",
				"z", "poprzez", "wedle", "wśród", "okrom", "wspak", "znad",
				"k", "wskroś", "naprzeciwko", "za", "wkoło", "co", "śród",
				"via", "o", "pobocz", "ponad", "nieopodal", "powyżej", "skróś",
				"przed", "dookoła", "przeciwko", "loco", "podal", "w", "na",
				"vis-à-vis", "naprzeciw", "pod", "podle", "temu", "mimo",
				"znad", "przez", "bez", "skrony", "przeciw", "ponad", "dla",
				"obok", "lada", "około", "jak", "niżeli", "per", "pobok",
				"podług", "nad", "wyjąwszy", "gwoli", "wobec", "contra", "niż",
				"zza", "miast", "prócz" };

		for (String functionWord : spojnikiZaimki) {
			functionWord = StringUtils.removeDiacritics(functionWord);
			features.put(name + functionWord,
					(double) t.countWordsLowerWoDiacritics(functionWord));
		}

		return features;
	}

}
