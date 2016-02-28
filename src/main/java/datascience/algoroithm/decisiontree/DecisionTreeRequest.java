package datascience.algoroithm.decisiontree;

import java.util.List;

import datascience.data.DataFrame;

public class DecisionTreeRequest {
	private String target;
	private List<String> features;
	private DataFrame dataFrame;
	public DecisionTreeRequest(String target, List<String> features,
			DataFrame dataFrame) {
		super();
		this.target = target;
		this.features = features;
		this.dataFrame = dataFrame;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public DataFrame getDataFrame() {
		return dataFrame;
	}
	public void setDataFrame(DataFrame dataFrame) {
		this.dataFrame = dataFrame;
	}
	

}
