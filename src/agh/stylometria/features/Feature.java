package agh.stylometria.features;

import java.util.Map;

import agh.stylometria.Text;

public abstract class Feature {
	public  abstract Map<String, Double> process(final Text t);
}
