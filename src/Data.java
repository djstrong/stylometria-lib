import weka.core.Instances;

public class Data {
	public Instances train;
	public Instances test;

	public Data(Instances train, Instances test) {
		this.train = train;
		this.test = test;
	}
}
