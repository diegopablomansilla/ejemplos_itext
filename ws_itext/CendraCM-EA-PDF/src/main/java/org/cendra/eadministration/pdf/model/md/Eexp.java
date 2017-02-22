package org.cendra.eadministration.pdf.model.md;

import org.cendra.eadministration.pdf.model.md.json.EExpJson;

public class Eexp extends Edoc {

	private String causative;
	private EExpJson eExp;

	public Eexp() {
		super();
		labelDocType = "Expediente";
	}

	public String getCausative() {
		if (causative == null) {
			throw new IllegalStateException("El causante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return causative;
	}

	public void setCausative(String causative) {
		if (causative == null) {
			throw new IllegalArgumentException("El causante del " + labelDocType + " debe ser distinto de NULL.");
		}
		causative = causative.trim();
		if (causative.length() == 0) {
			throw new IllegalArgumentException("El causante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.causative = causative;
	}

	public EExpJson geteExp() {
		return eExp;
	}

	public void seteExp(EExpJson eExp) {
		this.eExp = eExp;
	}

	public String toJson() {
		String[][] properties = getProperties();

		String props = "";
		if (properties != null) {

			for (int i = 0; i < properties.length; i++) {
				if (i == 0) {
					props += "\"properties\": [";
				}

				if (i == properties.length - 1) {
					props += "{\"" + properties[i][0] + "\":";
					props += "\"" + properties[i][1] + "\"}]";
				} else {
					props += "{\"" + properties[i][0] + "\":";
					props += "\"" + properties[i][1] + "\"},";
				}

			}

		}

		String r = "";

		r += "{";

		r += super.toJson() + ", ";

		r += "\"cudap\": \"" + this.getCudap() + "\",";
		r += "\"initiated\": \"" + this.getAdminUnit() + "\",";
		// r += "\"footer\": \"" + this.getFooter() + "\",";
		r += "\"causative\": \"" + this.getCausative() + "\",";
		r += "\"user\": " + this.getUser().toJson() + ", ";

		r += props;

		r += "}";

		return r;
	}

}
