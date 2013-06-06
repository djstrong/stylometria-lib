import java.io.File;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.core.converters.ArffSaver;

public class Czasy {
	public static void main(String[] args) throws Exception {
		Results r = new Results();
		r.buildStructure("f3.csv");

		List<Classifier> classifiers = new ArrayList<Classifier>();
		classifiers.add(new J48());
		classifiers.add(new NaiveBayes());
		classifiers.add(new LibSVM());
		classifiers.add(new BayesNet());

		Data data = r.buildData(100, 100);
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data.train);
		saver.setFile(new File("train3.arff"));
		saver.writeBatch();

		System.exit(0);
		
		for (Classifier classifier : classifiers) {
			Evaluation eval = new Evaluation(data.train);
			long time1 = System.nanoTime();
			classifier.buildClassifier(data.train);
			long time2 = System.nanoTime();
			eval.evaluateModel(classifier, data.test);
			long time3 = System.nanoTime();

			System.out.println(classifier.getClass().getSimpleName() + "\t"
					+ eval.pctCorrect() + "\t" + (double) (time2 - time1) / 1e9
					+ "\t" + (double) (time3 - time2) / 1e9);
		}

	}
}
