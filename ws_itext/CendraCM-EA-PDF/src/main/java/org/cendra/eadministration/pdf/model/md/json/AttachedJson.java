package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class AttachedJson {

	private String embedB64;
	private String url;
	private String id;
	private String name;

	public String getEmbedB64() {
		return embedB64;
	}

	public void setEmbedB64(String embedB64) {
		this.embedB64 = embedB64;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String toString() {
		String json = "{";

		json += UtilJson.buildAttJson("id", id) + ",";
		json += UtilJson.buildAttJson("name", name) + ",";
		json += UtilJson.buildAttJson("url", url);

		return json + "}";
	}

}
