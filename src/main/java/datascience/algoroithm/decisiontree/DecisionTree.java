package datascience.algoroithm.decisiontree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datascience.algorithm.AlgorithmException;
import datascience.algorithm.FeatureInfoGain;
import datascience.algorithm.Utils;
import datascience.data.AnalysisException;
import datascience.data.DataFrame;

public class DecisionTree {
	private DecisionTreeRequest decisionTreeRequest;

	public DecisionTree(DecisionTreeRequest decisionTreeRequest) {
		super();
		this.decisionTreeRequest = decisionTreeRequest;
	}

	public DecisionTreeModel train() throws AlgorithmException {

		if (this.decisionTreeRequest.getTarget() == null) {
			throw new AlgorithmException("Target is not set");
		}
		if (this.decisionTreeRequest.getFeatures() == null) {
			throw new AlgorithmException("Features are not set");
		}
		if (this.decisionTreeRequest.getDataFrame() == null) {
			throw new AlgorithmException("DataFrame is not set");
		}
		Node root = new Node();	
		Node tree = buildTree(root, this.decisionTreeRequest);

		return new DecisionTreeModel(tree,this.decisionTreeRequest.getTarget());

	}

	private Node buildTree(Node root, DecisionTreeRequest decisionTreeRequest) {
		Object commonClass = Utils.commonClass(
				decisionTreeRequest.getDataFrame(),
				decisionTreeRequest.getTarget());
		root.setClassvalue(commonClass);
		int numClasses = decisionTreeRequest.getDataFrame()
				.select(decisionTreeRequest.getTarget()).distinct().count();
		if (numClasses == 1) {
			root.setLeaf(true);
			return root;
		}
		if (decisionTreeRequest.getFeatures().isEmpty()) {
			root.setLeaf(true);
			return root;
		}
		DataFrame dataFrame = decisionTreeRequest.getDataFrame();
		String target = decisionTreeRequest.getTarget();
		List<String> features = new ArrayList<String>(
				decisionTreeRequest.getFeatures());
		FeatureInfoGain maxInfoGainFeature = Utils.maxInfoGainFeature(dataFrame,
				features, target);
		root.setInfoGain(maxInfoGainFeature.getInfoGain());
		root.setNodeName(maxInfoGainFeature.getFeatureName());
		List<Object> distinctValues = this.decisionTreeRequest.getDataFrame()
				.distinct(maxInfoGainFeature.getFeatureName());
		Iterator<Object> iterator = distinctValues.iterator();
		List<Node> children = new ArrayList<Node>();
		root.setChildren(children);
		while (iterator.hasNext()) {
			Object next = iterator.next();
			DataFrame filterDataFrame = dataFrame
					.filter(maxInfoGainFeature.getFeatureName(), next);
			if(filterDataFrame.count()==0){
				Node child = new Node();				
				child.setClassvalue(commonClass);
				child.setNodeValue(next);
				child.setLeaf(true);
				child.setPerent(root);
				root.getChildren().add(child);
			}else{				
				Node child = new Node();
				child.setPerent(root);
				child.setNodeValue(next);
				features.remove(maxInfoGainFeature.getFeatureName());
				List<String> remainingFeatures = new ArrayList<String>(features);
				DecisionTreeRequest decisionTreeRequestNew = new DecisionTreeRequest(target, remainingFeatures, filterDataFrame);
				root.getChildren().add(buildTree(child,decisionTreeRequestNew));
			}

		}

		return root;

	}

}
