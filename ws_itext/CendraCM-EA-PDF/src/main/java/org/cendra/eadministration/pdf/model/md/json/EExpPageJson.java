package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class EExpPageJson extends EDocJson {

	private AttachedJson pdfExpToConcatenate;
	private AttachedJson pdfEdocToConcatenate;

	public AttachedJson getPdfExpToConcatenate() {
		return pdfExpToConcatenate;
	}

	public void setPdfExpToConcatenate(AttachedJson pdfExpToConcatenate) {
		this.pdfExpToConcatenate = pdfExpToConcatenate;
	}

	public AttachedJson getPdfEdocToConcatenate() {
		return pdfEdocToConcatenate;
	}

	public void setPdfEdocToConcatenate(AttachedJson pdfEdocToConcatenate) {
		this.pdfEdocToConcatenate = pdfEdocToConcatenate;
	}

	public String toString() {

		String json = "{";

		json += super.toString();
		json += UtilJson.buildAttJson("subject", subject) + ",";
		json += UtilJson.buildAttJson("causative", causative);
		// json += "\"pdfToEmbed\":" + pdfToEmbed;

		return json + "}";
	}

}
