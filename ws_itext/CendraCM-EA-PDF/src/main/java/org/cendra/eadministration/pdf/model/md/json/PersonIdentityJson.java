package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class PersonIdentityJson {

	private String id;
	private String code;
	private String shortName;
	private String name;
	private String number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String toString() {
		String json = "{";

		json += UtilJson.buildAttJson("id", id) + ",";
		json += UtilJson.buildAttJson("code", code) + ",";
		json += UtilJson.buildAttJson("shortName", shortName) + ",";
		json += UtilJson.buildAttJson("name", name) + ",";
		json += UtilJson.buildAttJson("number", number);

		return json + "}";
	}



}
