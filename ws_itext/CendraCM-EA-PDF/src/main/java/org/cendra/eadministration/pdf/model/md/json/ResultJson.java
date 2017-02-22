package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class ResultJson {

	private String pdfB64;
	private String imgB64;

	public String getPdfB64() {
		return pdfB64;
	}

	public void setPdfB64(String pdfB64) {
		this.pdfB64 = pdfB64;
	}

	public String getImgB64() {
		return imgB64;
	}

	public void setImgB64(String imgB64) {
		this.imgB64 = imgB64;
	}

	public String toString() {
		String json = "{";

		json += UtilJson.buildAttJson("pdfB64", pdfB64) + ",";
		json += UtilJson.buildAttJson("imgB64", imgB64);

		return json + "}";
	}

}
