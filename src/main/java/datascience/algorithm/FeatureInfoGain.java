package datascience.algorithm;

public class FeatureInfoGain {
	private String featureName;
	private double infoGain;
	
	public FeatureInfoGain(String featureName, double infoGain) {
		super();
		this.featureName = featureName;
		this.infoGain = infoGain;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public double getInfoGain() {
		return infoGain;
	}
	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}
	

}
