package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class PersonJson {

	private String id;
	private String[] names;
	private String[] lastNames;
	private PersonIdentityJson[] identities;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getLastNames() {
		return lastNames;
	}

	public void setLastNames(String[] lastNames) {
		this.lastNames = lastNames;
	}

	public PersonIdentityJson[] getIdentities() {
		return identities;
	}

	public void setIdentities(PersonIdentityJson[] identities) {
		this.identities = identities;
	}

	public String toString() {
		String json = "{";

		json += UtilJson.buildAttJson("id", id) + ",";
		json += UtilJson.buildAttJson("names", names) + ",";
		json += UtilJson.buildAttJson("lastNames", lastNames) + ",";
		json += "\"" + "identities" + "\": [";
		for (int i = 0; i < identities.length; i++) {
			json += identities[i];
			if (i < identities.length - 1) {
				json += ",";
			}

		}
		json += "]";

		return json + "}";
	}



}
