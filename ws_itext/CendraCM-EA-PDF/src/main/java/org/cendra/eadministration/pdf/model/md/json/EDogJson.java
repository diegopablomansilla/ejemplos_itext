package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class EDogJson extends EDocJson {

	private String subject;
	private AttachedJson pdfToEmbed;
	private AttachedJson institutionalImgToEmbed;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public AttachedJson getPdfToEmbed() {
		return pdfToEmbed;
	}

	public void setPdfToEmbed(AttachedJson pdfToEmbed) {
		this.pdfToEmbed = pdfToEmbed;
	}

	public AttachedJson getInstitutionalImgToEmbed() {
		return institutionalImgToEmbed;
	}

	public void setInstitutionalImgToEmbed(AttachedJson institutionalImgToEmbed) {
		this.institutionalImgToEmbed = institutionalImgToEmbed;
	}

	public String toString() {
		String json = "{";

		json += super.toString();
		json += UtilJson.buildAttJson("subject", subject) + ",";
		json += "\"pdfToEmbed\":" + pdfToEmbed;

		return json + "}";
	}

}
