package test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import agh.stylometria.FeaturesExtractor;
import agh.stylometria.features.DlugoscSlow;
import agh.stylometria.features.Emotikony;
import agh.stylometria.features.FormyGrzecznosciowe;
import agh.stylometria.features.FunctionWords;
import agh.stylometria.features.IloscSlowZdan;
import agh.stylometria.features.Plec;
import agh.stylometria.features.Pokemony;
import agh.stylometria.features.Wulgaryzmy;
import agh.stylometria.features.ZnakiDiakrytyczne;
import agh.stylometria.features.ZnakiInterpunkcyjne;

public class Tests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlec() {
		String text = "zrobiłem zrobiłam złamał robiŁem WLAM";
		Map<String, Double> features = FeaturesExtractor.features(text);

		// dokladniejsze
		assertEquals(Double.valueOf(2), features.get(Plec.nameLeem));
		assertEquals(Double.valueOf(1), features.get(Plec.nameLaam));
		// wiecej bledow, np. plam wlam
		assertEquals(Double.valueOf(0), features.get(Plec.nameLem));
		assertEquals(Double.valueOf(1), features.get(Plec.nameLam));
	}

	@Test
	public void testWulgaryzmy() {
		String text = "skurwiel SKuRwieL zajebać zajebaĆ zajebac ZAJEBaC";
		Map<String, Double> features = FeaturesExtractor.features(text);

		assertEquals(Double.valueOf(6), features.get(Wulgaryzmy.name));
	}

	@Test
	public void testZnakiDiakrytyczne() {
		String text = "ksjhdfaą asdćłĘÓŹŻ";
		Map<String, Double> features = FeaturesExtractor.features(text);

		assertEquals(Double.valueOf(7), features.get(ZnakiDiakrytyczne.name));
	}

	@Test
	public void testZwrotyGrzecznosciowe() {
		String text = "co ci podać, Panie";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(1),
				features.get(FormyGrzecznosciowe.nameWielka));
		assertEquals(Double.valueOf(1),
				features.get(FormyGrzecznosciowe.nameMala));

	}

	@Test
	public void testDlugoscSlow() {
		String text = "co ci podać, Panie";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(2), features.get(DlugoscSlow.name + "2"));
		assertEquals(Double.valueOf(0), features.get(DlugoscSlow.name + "3"));
		assertEquals(Double.valueOf(2), features.get(DlugoscSlow.name + "5"));
		assertEquals(Double.valueOf(0), features.get(DlugoscSlow.name + "1"));
	}

	@Test
	public void testEmotikony() {
		String text = "co ci podać, Panie ;) :-D";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(2), features.get(Emotikony.name));
	}

	@Test
	public void testIloscSlowZdan() {
		String text = "co ci podać, Panie? co tam słcyhać? tak.";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(8), features.get(IloscSlowZdan.nameWords));
		assertEquals(Double.valueOf(3),
				features.get(IloscSlowZdan.nameSentences));
	}

	@Test
	public void testPokemony() {
		String text = "co ci podać, Panie? co tam słcyhać? tak. PoKemon pOkemon";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(2), features.get(Pokemony.name));
	}

	@Test
	public void testZnakiInterpunkcyjne() {
		String text = "co ci podać, Panie? ;) :-D co tam słcyhać? tak. PoKemon pOkemon";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(8), features.get(ZnakiInterpunkcyjne.name));
	}

	@Test
	public void testFunctionWords() {
		String text = "co ci podać, Panie? ;) :-D co tam słcyhać? tak. PoKemon pOkemon";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(2), features.get(FunctionWords.name + "co"));
		assertEquals(Double.valueOf(0),
				features.get(FunctionWords.name + "kiedy"));
	}
}
