package datascience.algoroithm.decisiontree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import datascience.data.Element;
import datascience.data.Record;

public class DecisionTreeModel {
	private Node tree;
	private String target; 
	private List<List<String>> paths;

	public Node getTree() {
		return tree;
	}

	public void setTree(Node tree) {
		this.tree = tree;
	}

	public DecisionTreeModel(Node tree,String target) {
		super();
		this.tree = tree;
		this.target = target;
		this.paths = new ArrayList<List<String>>();
		traverseTree(this.tree);
		
	}
	

	@Override
	public String toString() {
		StringBuilder path = new StringBuilder();
		Iterator<List<String>> iterator = this.paths.iterator();
		while(iterator.hasNext()){
			List<String> next = iterator.next();
			Collections.reverse(next);
			Iterator<String> iterator2 = next.iterator();
			path.append("IF ");
			while(iterator2.hasNext()){
				String next2 = iterator2.next();
				path.append(next2);
			}
			path.append("\n");
		}		
		return path.toString();

	}
	
	private void traverseTree(Node node){
		if(node.isLeaf()){
			List<String> path = new ArrayList<String>();
			path.add(node.getClassvalue().toString());
			path.add("=");
			path.add(this.target);
			path.add(" --> ");
			path.add(node.getNodeValue().toString());
			Boolean rootNode = false;
			Node currentNode = node.getPerent();
			while(!rootNode){
				if(currentNode.getPerent()==null){
					path.add("=");
					path.add(currentNode.getNodeName());					
					rootNode = true;
				}else{
					path.add("=");
					path.add(currentNode.getNodeName());
					path.add(" & ");
					path.add(currentNode.getNodeValue().toString());
					currentNode = currentNode.getPerent();
				}
			}
			this.paths.add(path);
		}else{			
			Iterator<Node> iterator = node.getChildren().iterator();
			while(iterator.hasNext()){
				traverseTree(iterator.next());
			}
		}
	}
	
	public Object classify(Record record){
		return getClass(this.tree,record);		
	}
	
	private Object getClass(Node node,Record record){
		Object classVal = node.getClassvalue();
		if(node.isLeaf()){
			classVal= node.getClassvalue();
		}else{
			Element element = record.selectElement(node.getNodeName());
			Iterator<Node> iterator = node.getChildren().iterator();
			while(iterator.hasNext()){
				Node next = iterator.next();
				if(next.getNodeValue().equals(element.getValue())){
					classVal = getClass(next,record);					
				}else{
					classVal =next.getPerent().getClassvalue();
				}
			}
		}
		return classVal;
	}
	

}
