import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
			
			int minTexts = 2;
			int maxTexts = 100;
			int minAuthors = 2;
			int maxAuthors = 100;
			
			int numTexts;
			int numAuthors;
			try {
				BufferedReader in = new BufferedReader(new FileReader("f1"
						+ classifier.getClass().getSimpleName() + ".dat"));
				
				String strLine = null, tmp;
				while ((tmp = in.readLine()) != null) {
					strLine = tmp;
				}
				in.close();

				String[] line = strLine.split("\t");
				numTexts = Integer.parseInt(line[0])+1;
				numAuthors = Integer.parseInt(line[1])+1;
			} catch (FileNotFoundException e) {
				numTexts = minTexts;
				numAuthors = minAuthors;
			}


			BufferedWriter out = new BufferedWriter(new FileWriter("f1"
					+ classifier.getClass().getSimpleName() + ".dat", true));


			double[][] results = new double[maxTexts + 1][maxAuthors + 1];
			for (numTexts = minTexts; numTexts <= maxTexts; ++numTexts) {
				for (; numAuthors <= maxAuthors; ++numAuthors) {

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
					out.flush();
					results[numTexts][numAuthors] = eval.pctCorrect();
				}
				numAuthors = minAuthors;
			}
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
