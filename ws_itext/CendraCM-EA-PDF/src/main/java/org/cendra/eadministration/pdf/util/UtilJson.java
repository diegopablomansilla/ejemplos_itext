package org.cendra.eadministration.pdf.util;

public class UtilJson {

	public static String buildAttJson(String name, String value) {
		if (value == null || value.trim().length() == 0) {
			return "\"" + name + "\": null";
		}
		return "\"" + name + "\":\"" + value + "\"";
	}

	public static String buildAttJson(String name, String[] values) {
		String json = "";

		json += "\"" + name + "\": [";
		for (int i = 0; i < values.length; i++) {
			json += "\"" + values[i] + "\"";
			if (i < values.length - 1) {
				json += ",";
			}

		}
		json += "]";

		return json;

	}

}
