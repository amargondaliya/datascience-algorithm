package datascience.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeTraverseAction;

import datascience.algoroithm.decisiontree.DecisionTree;
import datascience.algoroithm.decisiontree.DecisionTreeModel;
import datascience.algoroithm.decisiontree.DecisionTreeRequest;
import datascience.algoroithm.decisiontree.Node;
import datascience.data.CSVReader;
import datascience.data.DataFrame;
import datascience.data.Record;
import datascience.data.Schema;
import datascience.data.StructField;

public class DecisionTreeTest {

	public static void main(String[] args) {
		List<StructField> structFileds = new ArrayList<StructField>();
		structFileds.add(new StructField("Name", String.class));
		structFileds.add(new StructField("Blood_Type", String.class));
		structFileds.add(new StructField("Give_Birth", String.class));
		structFileds.add(new StructField("Can_Fly", String.class));
		structFileds.add(new StructField("Live_in_Water", String.class));
		structFileds.add(new StructField("Class", String.class));
		Schema schema = new Schema(structFileds);
		String path = "";
		String delimiter = ",";
		CSVReader reader = new CSVReader(schema, path, delimiter);
		DataFrame dataFrame = null;
		try {
			dataFrame = reader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dataFrame.count());
		String target = "Class";
		List<String> features = new ArrayList<String>(
				Arrays.asList(new String[] { "Blood_Type", "Give_Birth",
						"Can_Fly", "Live_in_Water" }));
		DecisionTreeRequest decisionTreeRequest = new DecisionTreeRequest(
				target, features, dataFrame);
		DecisionTreeModel tree = new DecisionTree(decisionTreeRequest).train();
		System.out.println(tree.toString());
		List<Record> records = decisionTreeRequest.getDataFrame()
				.select(features.toArray(new String[features.size()]))
				.getRecords();
		Iterator<Record> iterator = records.iterator();
		while (iterator.hasNext()) {
			Record next = iterator.next();
			System.out.println(tree.classify(next));
		}

	}

}
