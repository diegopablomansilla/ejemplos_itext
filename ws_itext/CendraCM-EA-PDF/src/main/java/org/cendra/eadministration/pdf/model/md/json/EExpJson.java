package org.cendra.eadministration.pdf.model.md.json;

import org.cendra.eadministration.pdf.util.UtilJson;

public class EExpJson extends EDocJson {

	private String subject;
	private AttachedJson pdfToEmbed;
	private AttachedJson institutionalImgToEmbed;
	private String causative;
//	private String[] propertiesKey = new String[0];
//	private String[] propertiesValue = new String[0];

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

	public String getCausative() {
		return causative;
	}

	public void setCausative(String causative) {
		this.causative = causative;
	}

	public String toString() {
		
		String json = "{";

		json += super.toString();
		json += UtilJson.buildAttJson("subject", subject) + ",";
		json += UtilJson.buildAttJson("causative", causative);
//		json += "\"pdfToEmbed\":" + pdfToEmbed;

		return json + "}";
	}

}
