package datascience.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Record {
	private List<Element> elements;

	public Record(List<Element> elements) {
		super();
		this.elements = elements;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public List<Object> getValues() {
		List<Object> values = new ArrayList<Object>();
		if (!this.elements.isEmpty()) {
			Iterator<Element> iterator = this.elements.iterator();
			while (iterator.hasNext()) {
				values.add(iterator.next().getValue());
			}
		}
		return values;
	}

	public Element selectElement(String name) {
		Iterator<Element> iterator = this.elements.iterator();
		Element element = null;
		while (iterator.hasNext()) {
			Element next = iterator.next();
			if (next.getName().equals(name)) {
				element = next;
			}
		}
		return element;
	}

	public Record selectColumns(List<String> columns) {
		List<Element> recordNew = new ArrayList<Element>();
		Iterator<String> iterator = columns.iterator();
		while (iterator.hasNext()) {
			String column = iterator.next();
			Element element = this.selectElement(column);
			recordNew.add(element);
		}
		return new Record(recordNew);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Record [elements=" + elements + "]";
	}
	
	

}
