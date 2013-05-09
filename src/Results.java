import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Results {
	private Map<Integer, List<Instance>> map;
	private Instances data;

	public void buildStructure() throws Exception {
		DataSource dataSource = new DataSource("ctrain.csv");
		data = dataSource.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);

		this.map = new HashMap<Integer, List<Instance>>();
		for (int i = 0; i < data.numInstances(); ++i) {
			Instance instance = data.instance(i);
			Integer cls = (int) instance.classValue();
			// System.out.println(data.classAttribute().value(cls));
			List<Instance> list = this.map.get(cls);
			if (list == null) {
				list = new ArrayList<>();
				this.map.put(cls, list);
			}
			list.add(instance);
		}
	}

	public Data buildData(int numAuthors, int numTexts) {

		List<Integer> authors = new ArrayList<Integer>(map.keySet());
		Collections.shuffle(authors);

		Instances train = new Instances(data, 0);
		Instances test = new Instances(data, 0);

		for (int i = 0; i < numAuthors; ++i) {
			List<Instance> texts = map.get(authors.get(i));
			Collections.shuffle(texts);
			for (int j = 0; j < numTexts; ++j) {
				try {
					if (j < numTexts * 2 / 3)
						train.add(texts.get(j));
					else
						test.add(texts.get(j));
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Za malo tekstow");
					System.exit(1);
				}
			}

		}

		return new Data(train, test);
	}

	public static void main(String[] args) throws Exception {
		Results r = new Results();
		r.buildStructure();

		Classifier classifier;
		int numTexts = 4;
		for (int numAuthors = 2; numAuthors < 30; ++numAuthors) {

			classifier = new LibSVM();
			// classifier = new SMO();
			// classifier = new J48();

			Data data = r.buildData(numAuthors, numTexts);
			// System.out.println(data.train);
			// System.out.println(data.test);

			classifier.buildClassifier(data.train);

			Evaluation eval = new Evaluation(data.train);
			eval.evaluateModel(classifier, data.test);
			System.out.println(eval.toSummaryString("\nResults " + numAuthors
					+ "\n======\n", false));
		}
	}
}