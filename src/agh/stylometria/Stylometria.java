package agh.stylometria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Stylometria {

	public static void main(String[] args) {
		String[] texts = new String[] {
				"kiedy xP x-D :-D :( :(((( ;)kierowca samochodu stracił panowanie? :) I Ci ludzie ciągle piją do wszystkich, że nie chcą ich przepuszczać na skrzyżowaniach, że kierowcy samochodów nie chcą wjeżdżać na chodniki, aby przepuścić przepychającego się w korku motocykla? CO ZA HIPOKRYZJA!",
				"co ci podać, Panie? ;) :-D co tam słcyhać? tak. PoKemon pOkemon" };

		Object[] keys = FeaturesExtractor.features("").keySet().toArray();

		FileWriter fstream = null;
		try {
			fstream = new FileWriter("features.csv");
			BufferedWriter out = new BufferedWriter(fstream);

			for (int i = 0; i < keys.length; ++i) {
				try {
					out.write(keys[i].toString());
					if (i < keys.length - 1)
						out.write(",");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out.write("\n");

			for (String text : texts) {
				Map<String, Double> f = FeaturesExtractor.features(text);
				for (int i = 0; i < keys.length; ++i) {
					try {
						out.write(f.get(keys[i]).toString());
						if (i < keys.length - 1)
							out.write(",");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				out.write("\n");
			}

			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
