package org.cendra.eadministration.pdf.util;

import java.util.Properties;

public class GlobalProperties {

	private static Properties properties;

	public final static String PATH_RESULT = "PATH_RESULT";
	
	public final static String PATH_FONT = "PATH_FONT";
	public final static String FONT_NORMAL = "FONT_NORMAL";
	public final static String FONT_BOLD = "FONT_BOLD";
	
	public final static String PATH_RESOURCES_PDF = "PATH_RESOURCES_PDF";
	public final static String PATH_RESOURCES_IMG = "PATH_RESOURCES_IMG";
	public final static String PATH_RESOURCES_JSON = "PATH_RESOURCES_JSON";

	private static Properties getProperties() {

		if (properties == null) {
			
			properties = new Properties();

			properties.put("PATH_INPUT_PDF", "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/resources/pdf_externos/");
			properties.put("PATH_INPUT_IMG", "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/resources/images/");

			properties.put(PATH_RESULT, "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/results/");
			properties.put(PATH_FONT, "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/resources/fonts/");
			
			properties.put(FONT_NORMAL, "FreeSans.ttf");
			properties.put(FONT_BOLD, "FreeSansBold.ttf");
			
			properties.put(PATH_RESOURCES_PDF, "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/results/tmp_pdf_input/");
			properties.put(PATH_RESOURCES_IMG, "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/results/tmp_img_input/");
			properties.put(PATH_RESOURCES_JSON, "/home/java/Dropbox/dev/ws_itext/CendraCM-EA-PDF/results/tmp_json/");
		}

		return properties;
	}

	public static String get(Object key) {
		return getProperties().get(key).toString();
	}

}
