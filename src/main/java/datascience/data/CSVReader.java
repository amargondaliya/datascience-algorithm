package datascience.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class CSVReader {
	private Schema schema;
	private String path;
	private String delimiter;

	public CSVReader(Schema schema, String path, String delimiter) {
		super();
		this.schema = schema;
		this.path = path;
		this.delimiter = delimiter;
	}

	public Schema getSchema() {
		return schema;
	}

	public CSVReader setSchema(Schema schema) {
		this.schema = schema;
		return this;
	}

	public String getPath() {
		return path;
	}

	public CSVReader setPath(String path) {
		this.path = path;
		return this;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public CSVReader setDelimiter(String delimiter) {
		this.delimiter = delimiter;
		return this;
	}

	public DataFrame read() throws IOException {
		File file = new File(this.path);
		InputStream inputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		Stream<String> lines = bufferedReader.lines();
		Iterator<String> iterator = lines.iterator();
		List<Record> records = new ArrayList<Record>();
		int i = 0;
		while (iterator.hasNext()) {
			String line = iterator.next();
			if (i!=0) {
				StringTokenizer tokenizer = new StringTokenizer(line,
						this.delimiter);
				List<Element> elements = new ArrayList<Element>();
				int j = 0;
				while (tokenizer.hasMoreTokens()) {
					Object type = this.schema.getFields().get(j).getType();
					String name = this.schema.getFields().get(j).getName();
					String value = tokenizer.nextToken();
					Object parsedValue = null;
					if (type.equals(Integer.class)) {
						parsedValue = Integer.parseInt(value);
					} else if (type.equals(Double.class)) {
						parsedValue = Double.parseDouble(value);
					} else if (type.equals(Long.class)) {
						parsedValue = Long.parseLong(value);
					} else if (type.equals(String.class)) {
						parsedValue = value;
					}
					Element element = new Element(name, parsedValue);
					elements.add(element);
					j++;
				}
				Record record = new Record(elements);
				records.add(record);
			}
			i++;
		}
		inputStream.close();

		return new DataFrame(records, schema);

	}

}
