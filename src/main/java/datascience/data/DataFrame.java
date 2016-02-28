package datascience.data;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataFrame {
	private List<Record> records;
	private Schema schema;	
	public DataFrame(List<Record> records, Schema schema) {
		super();
		this.records = records;
		this.schema = schema;		
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public DataFrame addRecord(Record record) {
		this.records.add(record);
		return this;
	}

	public int count() {
		return this.records.size();
	}

	public DataFrame distinct() {
		List<Record> distinctRecords = new ArrayList<Record>();
		Iterator<Record> iterator = this.records.iterator();		
		while (iterator.hasNext()) {
			Record next = iterator.next();
			if (!distinctRecords.contains(next)) {
				distinctRecords.add(next);
			}
		}
		return new DataFrame(distinctRecords, this.schema);

	}
	public List<Object> distinct(String column){
		DataFrame distinct = this.select(column).distinct();
		Iterator<Record> iterator = distinct.getRecords().iterator();
		List<Object> distinctValues = new ArrayList<Object>();
		while(iterator.hasNext()){
			Record next = iterator.next();
			distinctValues.add(next.getElements().get(0).getValue());
		}
		return distinctValues;
	}

	public DataFrame select(String... columns) throws AnalysisException {
		List<Record> newRecords = new ArrayList<Record>();
		List<String> columnNames = Arrays.asList(columns);
		for (String column : columnNames) {
			if (!this.schema.getFieldNames().contains(column)) {
				throw new AnalysisException("can not resolve '" + column
						+ "' in given columns " + this.schema.getFieldNames());
			}
		}
		Iterator<Record> iterator = this.records.iterator();
		while (iterator.hasNext()) {
			Record record = iterator.next().selectColumns(columnNames);
			newRecords.add(record);
		}
		Schema newSchema = this.schema.newSchema(columnNames);
		return new DataFrame(newRecords, newSchema);
	}

	public DataFrame filter(String columnName, Object value)
			throws AnalysisException {
		List<Record> records = new ArrayList<Record>();
		if (!this.schema.getFieldNames().contains(columnName)) {
			throw new AnalysisException(
					columnName + " does not exist in given dataframe");

		} else {
			Iterator<Record> iterator = this.records.iterator();
			while (iterator.hasNext()) {
				Record record = iterator.next();
				Iterator<Element> iterator2 = record.getElements().iterator();
				while (iterator2.hasNext()) {
					Element element = iterator2.next();
					if (element.getName().equals(columnName)) {
						if (!element.getValue().getClass()
								.equals(value.getClass())) {
							throw new AnalysisException(
									"Invalid value filtering");
						} else {
							if (element.getValue().equals(value)) {
								records.add(record);
							}

						}
					}
				}

			}

		}
		return new DataFrame(records, this.schema);

	}

	@Override
	public String toString() {
		return "DataFrame [records=" + records + ", schema=" + schema + "]";
	}
	
	

}
