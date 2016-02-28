package datascience.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Schema {
	private List<StructField> fields;
	private List<String> fieldNames;
	private Iterator<StructField> iterator;

	public Schema(List<StructField> fields) {
		super();
		this.fields = fields;
		this.iterator = fields.iterator();
	}

	public List<StructField> getFields() {
		return fields;
	}

	public void setFields(List<StructField> fields) {
		this.fields = fields;
	}
	
	public List<String> getFieldNames(){
		this.fieldNames = new ArrayList<String>();
		if(!this.fields.isEmpty()){
			Iterator<StructField> iterator = this.fields.iterator();
			while(iterator.hasNext()){
				this.fieldNames.add(iterator.next().getName());
			}
		}
		return this.fieldNames;
	}
	
	public Schema newSchema(List<String> columnNames){
		List<StructField> fields = new ArrayList<StructField>();
		while(this.iterator.hasNext()){
			StructField next = this.iterator.next();
			String name = next.getName();
			Object type = next.getType();
			if(columnNames.contains(name)){
				fields.add(new StructField(name, type));
			}
		}
		return new Schema(fields);
		
	}
	

}
