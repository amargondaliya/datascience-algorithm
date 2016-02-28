package datascience.algorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datascience.data.*;
public class CSVReaderTest {

	public static void main(String[] args) throws IOException {
		List<StructField> structFileds = new ArrayList<StructField>();
		structFileds.add(new StructField("A", String.class));
		structFileds.add(new StructField("B", String.class));
		structFileds.add(new StructField("C", String.class));
		structFileds.add(new StructField("D", String.class));
		Schema schema = new Schema(structFileds);
		String path = "/home/amar/Downloads/Amar/decisonTree/Xurmo/sample.csv";
		String delimiter = ",";
		CSVReader reader = new CSVReader(schema, path, delimiter);
		DataFrame dataFrame = reader.read();
		System.out.println(dataFrame.getRecords().toString());
		System.out.println(dataFrame.count());
//		System.out.println(dataFrame.filter("c", "x").count());
		System.out.println(dataFrame.distinct().count());
		System.out.println(dataFrame.select("A").distinct().count());
		System.out.println(dataFrame.distinct("C"));
		System.out.println(Utils.entropy("A", dataFrame));
		System.out.println(Utils.infoGain("A", "D", dataFrame));
		System.out.println(Utils.commonClass(dataFrame, "A"));

	}

}
