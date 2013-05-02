package agh.stylometria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FeaturesExtractor {

	public static Map<String, Double> features(String text) {
		Map<String, Double> features = new HashMap<String, Double>();

		Text t = new Text(text);

		// TODO: wielkie i male litery, co z polskimi znakami

		// płeć - końcówki łam, łem
		features.put("countL'em",
				(double) t.countWordsEndsWithIgnoreCase("łem"));
		features.put("countL'am",
				(double) t.countWordsEndsWithIgnoreCase("łam"));
		features.put("countLem", (double) t.countWordsEndsWithIgnoreCase("lem"));
		features.put("countLam", (double) t.countWordsEndsWithIgnoreCase("lam"));

		// wulgaryzmy
		// http://slowniki.zoni.pl/?s=wulgaryzmy_list
		// http://www.smf.pl/index.php?topic=9867.0
		String[] wulgaryzmy = new String[] { "chuj", "chuja", "chujem",
				"chujowi", "chujom", "chujów", "chujach", "chujami", "chuje",
				"chujowy", "chujowa", "chujowe", "chujowego", "chujowej",
				"chujowemu", "chujowych", "chujowym", "chujowymi", "chujową",
				"chujowo", "cipa", "cipy", "cipą", "cipę", "cipie", "cip",
				"cipach", "cipami", "cipom", "dojebać", "dojebali",
				"dojebaliście", "dojebaliśmy", "dojebano", "dojebał",
				"dojebała", "dojebałam", "dojebałaś", "dojebałem", "dojebałeś",
				"dojebało", "dojebały", "dojebałyście", "dojebałyśmy",
				"dojebie", "dojebią", "dojebiecie", "dojebcie", "dojebiemy",
				"dojebmy", "dojeb", "dojebiesz", "dojebię", "dojebaliby",
				"dojebalibyście", "dojebalibyśmy", "dojebałby", "dojebanoby",
				"dojebałaby", "dojebałabym", "dojebałabyś", "dojebałbym",
				"dojebałbyś", "dojebałoby", "dojebałyby", "dojebałybyście",
				"dojebałybyśmy", "dojebawszy", "dopierdolić", "dopierdolili",
				"dopierdoliliście", "dopierdoliliśmy", "dopierdolono",
				"dopierdolił", "dopierdoliła", "dopierdoliłam",
				"dopierdoliłaś", "dopierdoliłem", "dopierdoliłeś",
				"dopierdoliło", "dopierdoliły", "dopierdoliłyście",
				"dopierdoliłyśmy", "dopierdoli", "dopierdolą", "dopierdolicie",
				"dopierdolcie", "dopierdolimy", "dopierdolmy", "dopierdol",
				"dopierdolisz", "dopierdolę", "dopierdoliliby",
				"dopierdolilibyście", "dopierdolilibyśmy", "dopierdoliłby",
				"dopierdoliłaby", "dopierdoliłabym", "dopierdoliłabyś",
				"dopierdoliłbym", "dopierdoliłbyś", "dopierdoliłoby",
				"dopierdoliłyby", "dopierdoliłybyście", "dopierdoliłybyśmy",
				"dopierdoliwszy", "dupa", "dupie", "dupy", "dupą", "dupę",
				"dupach", "dupom", "dup", "dupami", "fuck", "fucker",
				"jebaniec", "jebańca", "jebańcem", "jebańcu", "jebańcy",
				"jebańcach", "jebańcami", "jebańcom", "jebańców", "jebany",
				"jebana", "jebane", "jebanego", "jebanej", "jebanemu",
				"jebanych", "jebanym", "jebanymi", "jebaną", "jebani",
				"jebnięty", "jebnięta", "jebnięte", "jebniętego", "jebniętej",
				"jebniętemu", "jebniętych", "jebniętym", "jebniętymi",
				"jebniętą", "jebnięci", "kurwa", "kurwie", "kurwy", "kurwą",
				"kurwę", "kurew", "kurwach", "kurwami", "kurwom", "kurwiszon",
				"kurwiszona", "kurwiszonem", "kurwiszonie", "kurwiszonowi",
				"kurwiszony", "kurwiszonach", "kurwiszonami", "kurwiszonom",
				"kurwiszonów", "kurestwo", "kurestwa", "kurestwem",
				"kurestwie", "kurestwu", "kurestwach", "kurestwami",
				"kurestwom", "kurewski", "kurewska", "kurewskie",
				"kurewskiego", "kurewskiej", "kurewskiemu", "kurewskich",
				"kurewskim", "kurewskimi", "kurewską", "kurewsko", "kurewscy",
				"kurewstwo", "kurewstwa", "kurewstwem", "kurewstwie",
				"kurewstwu", "matkojebca", "matkojebcy", "matkojebcą",
				"matkojebcę", "matkojebcach", "matkojebcami", "matkojebcom",
				"matkojebców", "ochujać", "ochujali", "ochujaliście",
				"ochujaliśmy", "ochujał", "ochujała", "ochujałam", "ochujałaś",
				"ochujałem", "ochujałeś", "ochujało", "ochujały",
				"ochujałyście", "ochujałyśmy", "odpierdolić", "odpierdolili",
				"odpierdoliliście", "odpierdoliliśmy", "odpierdolono",
				"odpierdolił", "odpierdoliła", "odpierdoliłam",
				"odpierdoliłaś", "odpierdoliłem", "odpierdoliłeś",
				"odpierdoliło", "odpierdoliły", "odpierdoliłyście",
				"odpierdoliłyśmy", "odpierdoli", "odpierdolą", "odpierdolicie",
				"odpierdolcie", "odpierdolimy", "odpierdolmy", "odpierdol",
				"odpierdolisz", "odpierdolę", "odpierdoliliby",
				"odpierdolilibyście", "odpierdolilibyśmy", "odpierdoliłby",
				"odpierdoliłaby", "odpierdoliłabym", "odpierdoliłabyś",
				"odpierdoliłbym", "odpierdoliłbyś", "odpierdoliłoby",
				"odpierdoliłyby", "odpierdoliłybyście", "odpierdoliłybyśmy",
				"odpierdoliwszy", "pierdolący", "pierdoląca", "pierdolące",
				"pierdolącego", "pierdolącej", "pierdolącemu", "pierdolących",
				"pierdolącym", "pierdolącymi", "pierdolącą", "pierdolony",
				"pierdolona", "pierdolone", "pierdolonego", "pierdolonej",
				"pierdolonemu", "pierdolonych", "pierdolonym", "pierdolonymi",
				"pierdoloną", "pierdoleni", "pierdolić", "pierdolili",
				"pierdoliliście", "pierdoliliśmy", "pierdolił", "pierdoliła",
				"pierdoliłam", "pierdoliłaś", "pierdoliłem", "pierdoliłeś",
				"pierdoliło", "pierdoliły", "pierdoliłyście", "pierdoliłyśmy",
				"pierdoli", "pierdolą", "pierdolicie", "pierdolimy",
				"pierdolisz", "pierdolę", "pierdol", "pierdolcie", "pierdolmy",
				"pierdoliliby", "pierdoliłby", "pizda", "pizdy", "pizdzie",
				"pizdą", "pizdę", "pizd", "pizdach", "pizdami", "pizdom",
				"piździe", "piździelec", "piździelca", "piździelcem",
				"piździelcowi", "piździelcu", "piździelcach", "piździelcami",
				"piździelcom", "piździelców", "piździelcy", "podjebać",
				"podjebali", "podjebaliście", "podjebaliśmy", "podjebano",
				"podjebał", "podjebała", "podjebałam", "podjebałaś",
				"podjebałem", "podjebałeś", "podjebało", "podjebały",
				"podjebałyście", "podjebałyśmy", "podjebie", "podjebią",
				"podjebiecie", "podjebcie", "podjebiemy", "podjebmy", "podjeb",
				"podjebiesz", "podjebię", "podjebaliby", "podjebalibyście",
				"podjebalibyśmy", "podjebałby", "podjebałaby", "podjebałabym",
				"podjebałabyś", "podjebałbym", "podjebałbyś", "podjebałoby",
				"podjebałyby", "podjebałybyście", "podjebałybyśmy",
				"podjebawszy", "podpierdalać", "podpierdalali",
				"podpierdalaliście", "podpierdalaliśmy", "podpierdalano",
				"podpierdalając", "podpierdalał", "podpierdalała",
				"podpierdalałam", "podpierdalałaś", "podpierdalałem",
				"podpierdalałeś", "podpierdalało", "podpierdalały",
				"podpierdalałyście", "podpierdalałyśmy", "podpierdala",
				"podpierdalają", "podpierdalacie", "podpierdalajcie",
				"podpierdalamy", "podpierdalajmy", "podpierdalaj",
				"podpierdalasz", "podpierdalam", "podpierdalaliby",
				"podpierdalalibyście", "podpierdalalibyśmy", "podpierdalałby",
				"podpierdalałaby", "podpierdalałabym", "podpierdalałabyś",
				"podpierdalałbym", "podpierdalałbyś", "podpierdalałoby",
				"podpierdalałyby", "podpierdalałybyście", "podpierdalałybyśmy",
				"podpierdolić", "podpierdolili", "podpierdoliliście",
				"podpierdoliliśmy", "podpierdolono", "podpierdolił",
				"podpierdoliła", "podpierdoliłam", "podpierdoliłaś",
				"podpierdoliłem", "podpierdoliłeś", "podpierdoliło",
				"podpierdoliły", "podpierdoliłyście", "podpierdoliłyśmy",
				"podpierdoli", "podpierdolą", "podpierdolicie",
				"podpierdolcie", "podpierdolimy", "podpierdolmy", "podpierdol",
				"podpierdolisz", "podpierdolę", "podpierdoliliby",
				"podpierdolilibyście", "podpierdolilibyśmy", "podpierdoliłby",
				"podpierdolonoby", "podpierdoliłaby", "podpierdoliłabym",
				"podpierdoliłabyś", "podpierdoliłbym", "podpierdoliłbyś",
				"podpierdoliłoby", "podpierdoliłyby", "podpierdoliłybyście",
				"podpierdoliłybyśmy", "podpierdoliwszy", "pojeb", "pojeba",
				"pojebem", "pojebowi", "pojeby", "pojebach", "pojebami",
				"pojebom", "pojebów", "pojebaniec", "pojebańca", "pojebańcem",
				"pojebańcowi", "pojebańcu", "pojebańcy", "pojebańcach",
				"pojebańcami", "pojebańcom", "pojebańców", "pojebany",
				"pojebana", "pojebane", "pojebanego", "pojebanej",
				"pojebanemu", "pojebanych", "pojebanym", "pojebanymi",
				"pojebaną", "pojebani", "pokurwiony", "pokurwiona",
				"pokurwione", "pokurwionego", "pokurwionej", "pokurwionemu",
				"pokurwionych", "pokurwionym", "pokurwionymi", "pokurwioną",
				"pokurwieni", "popierdolić", "popierdolili",
				"popierdoliliście", "popierdoliliśmy", "popierdolił",
				"popierdoliła", "popierdoliłam", "popierdoliłaś",
				"popierdoliłem", "popierdoliłeś", "popierdoliło",
				"popierdoliły", "popierdoliłyście", "popierdoliłyśmy",
				"popierdoli", "popierdolą", "popierdolicie", "popierdolimy",
				"popierdolisz", "popierdolę", "popierdolony", "popierdolona",
				"popierdolone", "popierdolonego", "popierdolonej",
				"popierdolonemu", "popierdolonych", "popierdolonym",
				"popierdolonymi", "popierdoloną", "popierdoleni", "przejebany",
				"przejebana", "przejebane", "przejebanego", "przejebanej",
				"przejebanemu", "przejebanych", "przejebanym", "przejebanymi",
				"przejebaną", "przepierdolić", "przepierdolili",
				"przepierdoliliście", "przepierdoliliśmy", "przepierdolono",
				"przepierdolił", "przepierdoliła", "przepierdoliłam",
				"przepierdoliłaś", "przepierdoliłem", "przepierdoliłeś",
				"przepierdoliło", "przepierdoliły", "przepierdoliłyście",
				"przepierdoliłyśmy", "przepierdoli", "przepierdolą",
				"przepierdolicie", "przepierdolimy", "przepierdolisz",
				"przepierdolę", "przepierdoliliby", "przepierdolilibyście",
				"przepierdolilibyśmy", "przepierdolonoby", "przepierdoliłby",
				"przepierdoliłaby", "przepierdoliłabym", "przepierdoliłabyś",
				"przepierdoliłbym", "przepierdoliłbyś", "przepierdoliłoby",
				"przepierdoliłyby", "przepierdoliłybyście",
				"przepierdoliłybyśmy", "przypierdolić", "przypierdolili",
				"przypierdoliliście", "przypierdoliliśmy", "przypierdolono",
				"przypierdolił", "przypierdoliła", "przypierdoliłam",
				"przypierdoliłaś", "przypierdoliłem", "przypierdoliłeś",
				"przypierdoliło", "przypierdoliły", "przypierdoliłyście",
				"przypierdoliłyśmy", "przypierdoli", "przypierdolą",
				"przypierdolicie", "przypierdolcie", "przypierdolimy",
				"przypierdolmy", "przypierdol", "przypierdolisz",
				"przypierdolę", "przypierdoliliby", "przypierdolilibyście",
				"przypierdolilibyśmy", "przypierdolonoby", "przypierdoliłby",
				"przypierdoliłaby", "przypierdoliłabym", "przypierdoliłabyś",
				"przypierdoliłbym", "przypierdoliłbyś", "przypierdoliłoby",
				"przypierdoliłyby", "przypierdoliłybyście",
				"przypierdoliłybyśmy", "przypierdoliwszy", "rozjebanie",
				"rozjebania", "rozjebaniem", "rozjebaniu", "rozjebać",
				"rozjebali", "rozjebaliście", "rozjebaliśmy", "rozjebano",
				"rozjebał", "rozjebała", "rozjebałam", "rozjebałaś",
				"rozjebałem", "rozjebałeś", "rozjebało", "rozjebały",
				"rozjebałyście", "rozjebałyśmy", "rozjebie", "rozjebią",
				"rozjebiecie", "rozjebcie", "rozjebiesz", "rozjebię",
				"rozjebaliby", "rozjebalibyście", "rozjebalibyśmy",
				"rozjebałby", "rozjebanoby", "rozjebałaby", "rozjebałabym",
				"rozjebałabyś", "rozjebałbym", "rozjebałbyś", "rozjebałoby",
				"rozjebałyby", "rozjebałybyście", "rozjebałybyśmy",
				"rozjebawszy", "rozjebany", "rozjebana", "rozjebane",
				"rozjebanego", "rozjebanej", "rozjebanemu", "rozjebanych",
				"rozjebanym", "rozjebanymi", "rozjebaną", "rozjebani",
				"skurwiel", "skurwiela", "skurwielem", "skurwielowi",
				"skurwielu", "skurwiele", "skurwielach", "skurwielami",
				"skurwielom", "skurwieli", "skurwysyn", "skurwysyna",
				"skurwysynem", "skurwysynowi", "skurwysynie", "skurwysynach",
				"skurwysynami", "skurwysynom", "skurwysynów", "skurwysynu",
				"skurwysyński", "skurwysyńska", "skurwysyńskie",
				"skurwysyńskiego", "skurwysyńskiej", "skurwysyńskiemu",
				"skurwysyńskich", "skurwysyńskim", "skurwysyńskimi",
				"skurwysyńską", "skurwysyńscy", "skurwysyństwo",
				"skurwysyństwa", "skurwysyństwem", "skurwysyństwie",
				"skurwysyństwu", "skurwysyństw", "skurwysyństwach",
				"skurwysyństwami", "skurwysyństwom", "spierdolić",
				"spierdolili", "spierdoliliście", "spierdoliliśmy",
				"spierdolono", "spierdolił", "spierdoliła", "spierdoliłam",
				"spierdoliłaś", "spierdoliłem", "spierdoliłeś", "spierdoliło",
				"spierdoliły", "spierdoliłyście", "spierdoliłyśmy",
				"spierdoli", "spierdolą", "spierdolicie", "spierdolimy",
				"spierdolisz", "spierdolę", "spierdolcie", "spierdol",
				"spierdolmy", "spierdalać", "spierdalał", "spierdalała",
				"spierdalałam", "spierdalałaś", "spierdalałem", "spierdalałeś",
				"spierdalało", "spierdalały", "spierdalałyście",
				"spierdalałyśmy", "spierdala", "spierdalaj", "spierdalajcie",
				"spierdalajmy", "spierdalacie", "spierdalamy", "spierdalasz",
				"wjebać", "wjebali", "wjebaliście", "wjebaliśmy", "wjebano",
				"wjebał", "wjebała", "wjebałam", "wjebałaś", "wjebałem",
				"wjebałeś", "wjebało", "wjebały", "wjebałyście", "wjebałyśmy",
				"wjebie", "wjebią", "wjebiecie", "wjebiemy", "wjebiesz",
				"wjebię", "wjebaliby", "wjebalibyście", "wjebalibyśmy",
				"wjebałby", "wjebałaby", "wjebałabym", "wjebałabyś",
				"wjebałbym", "wjebałbyś", "wjebałoby", "wjebałyby",
				"wjebałybyście", "wjebałybyśmy", "wjebawszy", "wkurwiać",
				"wkurwiali", "wkurwialiście", "wkurwialiśmy", "wkurwiano",
				"wkurwiając", "wkurwiał", "wkurwiała", "wkurwiałam",
				"wkurwiałaś", "wkurwiałem", "wkurwiałeś", "wkurwiało",
				"wkurwiały", "wkurwiałyście", "wkurwiałyśmy", "wkurwia",
				"wkurwiają", "wkurwiacie", "wkurwiajcie", "wkurwiamy",
				"wkurwiajmy", "wkurwiaj", "wkurwiasz", "wkurwiam",
				"wkurwialiby", "wkurwialibyście", "wkurwialibyśmy",
				"wkurwianoby", "wkurwiałby", "wkurwiałaby", "wkurwiałabym",
				"wkurwiałabyś", "wkurwiałbym", "wkurwiałbyś", "wkurwiałoby",
				"wkurwiałyby", "wkurwiałybyście", "wkurwiałybyśmy",
				"wkurwiający", "wkurwiająca", "wkurwiające", "wkurwiającego",
				"wkurwiającej", "wkurwiającemu", "wkurwiających",
				"wkurwiającym", "wkurwiającymi", "wkurwiającą", "wkurwić",
				"wkurwili", "wkurwiliście", "wkurwiliśmy", "wkurwiono",
				"wkurwił", "wkurwiła", "wkurwiłam", "wkurwiłaś", "wkurwiłem",
				"wkurwiłeś", "wkurwiło", "wkurwiły", "wkurwiłyście",
				"wkurwiłyśmy", "wkurwi", "wkurwią", "wkurwicie", "wkurwimy",
				"wkurwisz", "wkurwię", "wkurwiliby", "wkurwilibyście",
				"wkurwilibyśmy", "wkurwiłby", "wkurwiłaby", "wkurwiłabym",
				"wkurwiłabyś", "wkurwiłbym", "wkurwiłbyś", "wkurwiłoby",
				"wkurwiłybyście", "wkurwiłybyśmy", "wyjebać", "wyjebali",
				"wyjebaliście", "wyjebaliśmy", "wyjebano", "wyjebał",
				"wyjebała", "wyjebałam", "wyjebałaś", "wyjebałem", "wyjebałeś",
				"wyjebało", "wyjebały", "wyjebałyście", "wyjebałyśmy",
				"wyjebie", "wyjebią", "wyjebiecie", "wyjebcie", "wyjebiemy",
				"wyjebmy", "wyjebiesz", "wyjebię", "wyjebałby", "wyjebałaby",
				"wyjebałabym", "wyjebałabyś", "wyjebałbym", "wyjebałbyś",
				"wyjebałoby", "wyjebałyby", "wypierdalać", "wypierdalali",
				"wypierdalaliście", "wypierdalaliśmy", "wypierdalano",
				"wypierdalając", "wypierdalał", "wypierdalała",
				"wypierdalałam", "wypierdalałaś", "wypierdalałem",
				"wypierdalałeś", "wypierdalało", "wypierdalały",
				"wypierdalałyście", "wypierdalałyśmy", "wypierdala",
				"wypierdalają", "wypierdalacie", "wypierdalajcie",
				"wypierdalamy", "wypierdalajmy", "wypierdalaj", "wypierdalasz",
				"wypierdalam", "wypierdalałby", "wypierdalałaby",
				"wypierdolić", "wypierdolili", "wypierdoliliście",
				"wypierdoliliśmy", "wypierdolono", "wypierdolił",
				"wypierdoliła", "wypierdoliłam", "wypierdoliłaś",
				"wypierdoliłem", "wypierdoliłeś", "wypierdoliło",
				"wypierdoliły", "wypierdoliłyście", "wypierdoliłyśmy",
				"wypierdoli", "wypierdolą", "wypierdolicie", "wypierdolcie",
				"wypierdolimy", "wypierdolmy", "wypierdol", "wypierdolisz",
				"wypierdolę", "wypierdoliliby", "wypierdolilibyście",
				"wypierdolilibyśmy", "wypierdoliłby", "wypierdoliłaby",
				"wypierdoliłabym", "wypierdoliłabyś", "wypierdoliłbym",
				"wypierdoliłbyś", "wypierdoliłoby", "wypierdoliłyby",
				"wypierdoliłybyście", "wypierdoliłybyśmy", "wypierdoliwszy",
				"zajebać", "zajebali", "zajebaliście", "zajebaliśmy",
				"zajebano", "zajebał", "zajebała", "zajebałam", "zajebałaś",
				"zajebałem", "zajebałeś", "zajebało", "zajebały",
				"zajebałyście", "zajebałyśmy", "zajebie", "zajebią",
				"zajebiecie", "zajebcie", "zajebiemy", "zajebiesz", "zajeb",
				"zajebię", "zajebaliby", "zajebalibyście", "zajebalibyśmy",
				"zajebałby", "zajebałaby", "zajebałabym", "zajebałąbyś",
				"zajebałbym", "zajebałbyś", "zajebałoby", "zajebałyby",
				"zajebałybyście", "zajebałybyśmy", "zajebawszy", "zakurwić",
				"zakurwili", "zakurwiliście", "zakurwiliśmy", "zakurwił",
				"zakurwiła", "zakurwiłam", "zakurwiłaś", "zakurwiono",
				"zakurwiłem", "zakurwiłeś", "zakurwiło", "zakurwiły",
				"zakurwiłyście", "zakurwiłyśmy", "zakurwi", "zakurwią",
				"zakurwicie", "zakurwimy", "zakurwisz", "zakurwię",
				"zakurwiliby", "zakurwilibyście", "zakurwilibyśmy",
				"zakurwiłby", "zakurwiłaby", "zakurwiłabym", "zakurwiłabyś",
				"zakurwiłbym", "zakurwiłbyś", "zakurwiłoby", "zakurwiłyby",
				"zakurwiłybyście", "zakurwiłybyśmy", "zakurwiwszy",
				"zapierdalać", "zapierdalali", "zapierdalaliście",
				"zapierdalaliśmy", "zapierdalano", "zapierdalając",
				"zapierdalał", "zapierdalała", "zapierdalałam",
				"zapierdalałaś", "zapierdalałem", "zapierdalałeś",
				"zapierdalało", "zapierdalały", "zapierdalałyście",
				"zapierdalałyśmy", "zapierdala", "zapierdalają",
				"zapierdalacie", "zapierdalajcie", "zapierdalamy",
				"zapierdalajmy", "zapierdalaj", "zapierdalasz", "zapierdalam",
				"zapierdalaliby", "zapierdalalibyście", "zapierdalalibyśmy",
				"zapierdalałby", "zapierdalałaby", "zapierdalałabym",
				"zapierdalałabyś", "zapierdalałbym", "zapierdalałbyś",
				"zapierdalałoby", "zapierdalałyby", "zapierdalałybyście",
				"zapierdalałybyśmy", "zapierdolić", "zapierdolili",
				"zapierdoliliście", "zapierdoliliśmy", "zapierdolono",
				"zapierdolił", "zapierdoliła", "zapierdoliłam",
				"zapierdoliłaś", "zapierdoliłem", "zapierdoliłeś",
				"zapierdoliło", "zapierdoliły", "zapierdoliłyście",
				"zapierdoliłyśmy", "zapierdoli", "zapierdolą", "zapierdolicie",
				"zapierdolcie", "zapierdolimy", "zapierdolisz", "zapierdolę",
				"zapierdoliliby", "zapierdolilibyście", "zapierdolilibyśmy",
				"zapierdoliłby", "zapierdolonoby", "zapierdoliłaby",
				"zapierdoliłabym", "zapierdoliłabyś", "zapierdoliłbym",
				"zapierdoliłbyś", "zapierdoliłoby", "zapierdoliłyby" };
		HashSet<String> wulg = new HashSet<String>(wulgaryzmy.length);
		for (String wulgaryzm : wulgaryzmy) {
			wulg.add(StringUtils.removeDiacritics(wulgaryzm));
		}
		features.put("countWulgaryzmy",
				(double) t.countWordsLowerWoDiacritics(wulg));

		// znaki diakrytyczne
		features.put("countZnakiDiakrytyczne",
				(double) t.countPattern("[ąśżźćęńłóĄŚŻŹĆĘŃŁÓ]"));

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
				"państwem", "państwu", "państwach", "państwami", "państwom" };
		features.put("countZwrotyGrzecznoscioweMala", (double) t
				.countWordsWoDiacritics(new HashSet<String>(Arrays
						.asList(zwrotyGrzecznosciowe))));

		HashSet<String> zw = new HashSet<String>(zwrotyGrzecznosciowe.length);
		for (String zwrot : zwrotyGrzecznosciowe)
			zw.add(StringUtils.capitalize(zwrot));
		features.put("countZwrotyGrzecznoscioweWielka",
				(double) t.countWordsWoDiacritics(zw));

		// emotikony
		// TODO: kazda osobno ?
		features.put("emoticons",
				(double) t.countPattern("(:|;|x|X){1}-?(\\)|\\(|D|P|d|p)"));

		// ilość słów / zdanie
		features.put("countWords", (double) t.countWords());
		features.put("countSentences", (double) t.countSentences());

		// ilość znaków interpunkcyjnych / zdanie
		// TODO: kazdy osobno ?
		features.put("countZnakiInterpunkcyjne",
				(double) t.countPattern("[,.()?!:;\"'-]"));

		// ilość liter w słowach (częstość występowania słów o określonej
		// długości)
		int max = 20;
		int[] slowa = new int[max];
		for (int i = 0; i < max; ++i) {
			slowa[i] = 0;
		}
		for (Sentence s : t.sentences) {
			for (String w : s.words) {
				if (w.length() < max)
					slowa[w.length()]++;
			}
		}
		for (int i = 0; i < max; ++i) {
			features.put("countDlugosc" + i, (double) slowa[i]);
		}
		
		// ilość poszczególnych znaków (raczej specjalnych)

		// pokemony - pomijajac pierwsza litere czy slowo ma male i wielkie
		// litery
		int count = 0;
		for (Sentence s : t.sentences) {
			for (String w : s.words) {
				boolean mala = false;
				boolean wielka = false;
				for (int i = 1; i < w.length() - 1; ++i) {
					String letter = w.substring(i, i + 1);
					if (letter == letter.toLowerCase())
						mala = true;
					else
						wielka = true;
				}
				if (mala && wielka)
					++count;
			}
		}
		features.put("countPokemony", (double) count);

		// common words, functional words - np. jest, na, w, dla - najlepsze
		// wyniki
		
		//truche duzo spojnikow i przyimkow

		return features;
	}

	public static void main(String[] args) {
		Map<String, Double> f = features("kiedy xP x-D :-D :( :(((( ;)kierowca samochodu stracił panowanie? :) I Ci ludzie ciągle piją do wszystkich, że nie chcą ich przepuszczać na skrzyżowaniach, że kierowcy samochodów nie chcą wjeżdżać na chodniki, aby przepuścić przepychającego się w korku motocykla? CO ZA HIPOKRYZJA!");
		for (String k : f.keySet()) {
			System.out.println(k + " -> " + f.get(k));
		}
	}
}
