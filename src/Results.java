import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
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
		DataSource dataSource = new DataSource("f2.csv");
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

		for (Entry<Integer, List<Instance>> e : map.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue().size() + " "
					+ data.classAttribute().value(e.getKey()));
		}
	}

	public Data buildData(int numAuthors, int numTexts) {

		List<Integer> authors = new ArrayList<Integer>(map.keySet());
		Collections.shuffle(authors);

		Instances train = new Instances(data, 0);
		Instances test = new Instances(data, 0);

		for (int i = 0; i < numAuthors; ++i) {
			List<Instance> texts = map.get(authors.get(i));

			if (texts.size() < numTexts) {
				numAuthors++;
				continue;
			}

			Collections.shuffle(texts);
			for (int j = 0; j < numTexts; ++j) {
				try {
					if (j < numTexts * 2 / 3)
						train.add(texts.get(j));
					else
						test.add(texts.get(j));
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Za malo tekstow " + numTexts + " "
							+ texts.size());
					System.exit(1);
				}
			}

		}

		return new Data(train, test);
	}

	

	public static void main(String[] args) throws Exception {
		Results r = new Results();
		r.buildStructure();

		List<Classifier> classifiers = new ArrayList<Classifier>();
		//classifiers.add(new J48());
		classifiers.add(new NaiveBayes());
		//classifiers.add(new LibSVM());
		classifiers.add(new BayesNet());

		List<Thread> threads = new ArrayList<Thread>();
		for (Classifier classifier : classifiers) {
			Thread t = new Thread(new Process(r, classifier));
			threads.add(t);
			t.start();
			// process(r, classifier);
		}

		for (Thread t : threads) {
			t.join();
		}

	}
}