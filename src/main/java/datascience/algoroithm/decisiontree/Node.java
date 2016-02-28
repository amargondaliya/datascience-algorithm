package datascience.algoroithm.decisiontree;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Node {
	private double infoGain;
	private List<Node> children;
	private Node parent;
	private boolean isLeaf;
	private Object classvalue;
	private String NodeName;
	private Object NodeValue;
	public double getInfoGain() {
		return infoGain;
	}
	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node perent) {
		this.parent = perent;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Object getClassvalue() {
		return classvalue;
	}
	public void setClassvalue(Object classvalue) {
		this.classvalue = classvalue;
	}
	public String getNodeName() {
		return NodeName;
	}
	public void setNodeName(String nodeName) {
		NodeName = nodeName;
	}
	public Object getNodeValue() {
		return NodeValue;
	}
	public void setNodeValue(Object nodeValue) {
		NodeValue = nodeValue;
	}

}
