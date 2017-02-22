package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class UserJson {

	private String id;
	private String name;
	private PersonJson person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonJson getPerson() {
		return person;
	}

	public void setPerson(PersonJson person) {
		this.person = person;
	}

	public String toString() {
		String json = "{";

		json += UtilJson.buildAttJson("id", id) + ",";
		json += UtilJson.buildAttJson("name", name) + ",";
		json += "\"person\":" + person;

		return json + "}";
	}

}
