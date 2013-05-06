package test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import agh.stylometria.FeaturesExtractor;

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

		assertEquals(Double.valueOf(2), features.get("countL'em"));
		assertEquals(Double.valueOf(1), features.get("countL'am"));
		assertEquals(Double.valueOf(0), features.get("countLem"));
		assertEquals(Double.valueOf(1), features.get("countLam"));
	}

	@Test
	public void testWulgaryzmy() {
		String text = "skurwiel SKuRwieL zajebać zajebaĆ zajebac ZAJEBaC";
		Map<String, Double> features = FeaturesExtractor.features(text);

		assertEquals(Double.valueOf(6), features.get("countWulgaryzmy"));
	}

	@Test
	public void testZnakiDiakrytyczne() {
		String text = "ksjhdfaą asdćłĘÓŹŻ";
		Map<String, Double> features = FeaturesExtractor.features(text);

		assertEquals(Double.valueOf(7), features.get("countZnakiDiakrytyczne"));
	}

	@Test
	public void testZwrotyGrzecznosciowe() {
		String text = "co ci podać, Panie";
		Map<String, Double> features = FeaturesExtractor.features(text);
		assertEquals(Double.valueOf(1),
				features.get("countZwrotyGrzecznoscioweWielka"));
		assertEquals(Double.valueOf(1),
				features.get("countZwrotyGrzecznoscioweMala"));

	}
}
