import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;

public class Process implements Runnable {
	Results r;
	Classifier classifier;

	public Process(Results r, Classifier classifier) {
		this.r = r;
		this.classifier = classifier;
	}

	@Override
	public void run() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("f1"
					+ classifier.getClass().getSimpleName() + ".dat"));

			int minTexts = 2;
			int maxTexts = 100;
			int minAuthors = 2;
			int maxAuthors = 100;
			double[][] results = new double[maxTexts + 1][maxAuthors + 1];
			for (int numTexts = minTexts; numTexts <= maxTexts; ++numTexts) {
				for (int numAuthors = minAuthors; numAuthors <= maxAuthors; ++numAuthors) {

					// classifier = new LibSVM();
					// classifier = new SMO();

					// classifier = new NaiveBayes();

					Data data = r.buildData(numAuthors, numTexts);
					// System.out.println(data.train);
					// System.out.println(data.test);

					classifier.buildClassifier(data.train);

					Evaluation eval = new Evaluation(data.train);
					eval.evaluateModel(classifier, data.test);
					// System.out.println(eval.toSummaryString("\nResults "+
					// numAuthors + "\n======\n", false));
					System.out.println(numTexts + "\t" + numAuthors + "\t"
							+ eval.pctCorrect());
					out.write(numTexts + "\t" + numAuthors + "\t"
							+ eval.pctCorrect() + "\n");

					results[numTexts][numAuthors] = eval.pctCorrect();
				}
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
