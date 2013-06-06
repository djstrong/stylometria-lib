import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.net.search.local.K2;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

public class CopyOfEx2 {
	public static double getWeight(double[] distribution, Instance instance) {
		return distribution[(int) instance.classValue()];
	}

	public static String getAuthor(Instances instances, Instance instance) {
		return instances.classAttribute().value((int) instance.classValue());
	}

	public static int getPlace(double[] distribution, Instance instance,
			int numAuthors) {
		double weight = getWeight(distribution, instance);
		if (weight == 0.0)
			return numAuthors;
		int place = 0;
		for (double d : distribution) {
			if (d > weight)
				++place;
		}
		return Math.min(place, numAuthors);
	}

	public static void main(String[] args) throws Exception {
		List<Classifier> classifiers = new ArrayList<Classifier>();
		//classifiers.add(new J48());
		//classifiers.add(new NaiveBayes());
		LibSVM svm = new LibSVM();
		svm.setOptions(new String[] { "-B" });
		//classifiers.add(svm);
		BayesNet bayesnet = new BayesNet2();

		//for (String s : bayesnet.getSearchAlgorithm().getOptions())
		//	System.out.println(s);
		K2 k2 = new K2();
		k2.setMaxNrOfParents(10);
		bayesnet.setSearchAlgorithm(k2);
		//for (String s : bayesnet.getSearchAlgorithm().getOptions())
		//	System.out.println(s);
		classifiers.add(bayesnet);
		classifiers.add(new BayesNet());

		Results r = new Results();
		r.buildStructure("f1.csv");

		int numAuthors = 100;
		int numTexts = 100;

		Map<String, List<double[]>> mapa = new HashMap<String, List<double[]>>();
		for (int powt = 0; powt < 30; ++powt) {
			Data data = r.buildData(numAuthors, numTexts);
			for (Classifier classifier : classifiers) {
				String name = "f1" + classifier.getClass().getSimpleName()
						+ "AUTHOR" + numAuthors + "TEXT" + numTexts + ".dat";

				classifier.buildClassifier(data.train);
				int ok = 0;
				int bad = 0;
				int[] places = new int[numAuthors + 1];
				for (int i = 0; i < data.test.numInstances(); ++i) {
					Instance instance = data.test.instance(i);
					double[] distribution = classifier
							.distributionForInstance(instance);
					// System.out.println(instance.classValue());
					// System.out.println(getAuthor(data.test, instance));
					// System.out.println(getWeight(distribution, instance));

					// System.out.println(i + " " + distribution.length);
					int place = getPlace(distribution, instance, numAuthors);
					++places[place];
					// System.out.println();
					if (place < numAuthors * 0.2)
						++ok;
					else
						++bad;
				}
				System.out.println(ok + " " + bad + " " + (double) ok
						/ (bad + ok));

				// dystrbuanta
				int temp = 0;
				int sum = 0;
				double[] cum = new double[numAuthors + 1];
				for (int i = 0; i < places.length; ++i) {
					cum[i] = places[i] + temp;
					temp += places[i];
					sum += places[i];
				}
				for (int i = 0; i < places.length; ++i) {
					cum[i] /= sum;
					// out.write("" + i + "\t" + cum[i] + "\n");
				}
				// out.close();
				if (mapa.containsKey(name))
					mapa.get(name).add(cum);
				else {
					List<double[]> lista = new ArrayList<double[]>();
					lista.add(cum);
					mapa.put(name, lista);
				}
			}
		}

		for (Classifier classifier : classifiers) {
			String name = "f1" + classifier.getClass().getSimpleName()
					+ "AUTHOR" + numAuthors + "TEXT" + numTexts + ".dat";
			BufferedWriter out = new BufferedWriter(new FileWriter(name));
			List<double[]> lista = mapa.get(name);
			double[] avg = new double[numAuthors + 1];
			double[] odch = new double[numAuthors + 1];

			for (int i = 0; i < avg.length; ++i) {
				avg[i] = 0.0;
			}
			for (double[] cum : lista) {
				for (int i = 0; i < avg.length; ++i) {
					avg[i] += cum[i];
				}
			}
			for (int i = 0; i < avg.length; ++i) {
				avg[i] /= lista.size();
			}

			for (double[] cum : lista) {
				for (int i = 0; i < avg.length; ++i) {
					odch[i] += Math.pow(cum[i] - avg[i], 2.0);
				}
			}
			for (int i = 0; i < odch.length; ++i) {
				odch[i] = Math.sqrt(odch[i] / lista.size());
			}

			for (int i = 0; i < avg.length; ++i) {
				System.out.println(name+i);
				out.write("" + i + "\t" + avg[i] + "\t" + (avg[i] + odch[i])
						+ "\t" + (avg[i] - odch[i]) + "\n");
			}
			out.close();
		}
	}
}
