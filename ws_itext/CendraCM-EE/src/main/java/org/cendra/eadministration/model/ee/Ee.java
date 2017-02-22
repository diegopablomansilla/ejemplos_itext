package org.cendra.eadministration.model.ee;

import org.cendra.eadministration.model.Cudap;
import org.cendra.eadministration.model.Stakeholder;
import org.cendra.eadministration.model.document.Document;
import org.cendra.eadministration.model.document.DocumentType;
import org.cendra.eadministration.model.ou.Ou;

public class Ee extends Document {

	private Cudap cudap;
	private String subject;
	private Ou initiated;
	private Stakeholder causative;

	public Ee() {
		cudap = new Cudap();
//		cudap.setDocumentType(documentType);
	}

	public String getCudap() {
		if (cudap == null) {
			throw new IllegalStateException("El CUDAP del expediente debe ser distinto de NULL.");
		}
		return cudap.toString();
	}

	// public void setCudap(Cudap cudap) {
	// if (cudap == null) {
	// throw new IllegalArgumentException("El CUDAP del expediente debe ser distinto de NULL.");
	// }
	// this.cudap = cudap;
	// }

	public String getSubject() {
		if (subject == null) {
			throw new IllegalStateException("El asunto del expediente debe ser distinto de NULL.");
		}
		return subject;
	}

	public DocumentType getDocumentType() {
		return cudap.getDocumentType();
	}

	public Ou getOrg() {
		return cudap.getOrg();
	}

	public void setOrg(Ou org) {
		cudap.setOrg(org);
	}

	public Integer getNumber() {
		return cudap.getNumber();
	}

	public void setNumber(Integer number) {
		cudap.setNumber(number);
	}

	public Integer getYear() {
		return cudap.getYear();
	}

	public void setYear(Integer year) {
		cudap.setYear(year);
	}

	public void setSubject(String subject) {
		if (subject == null) {
			throw new IllegalArgumentException("El asunto del expediente debe ser distinto de NULL.");
		}
		subject = subject.trim();

		if (subject.length() == 0) {
			throw new IllegalArgumentException("El asunto del expediente debe ser distinto de vacio.");
		}
		this.subject = subject;
	}

	public Ou getInitiated() {
		if (initiated == null) {
			throw new IllegalStateException("La unidad administrativa (órgano) iniciante del expediente debe ser distinto de NULL.");
		}
		return initiated;
	}

	public void setInitiated(Ou initiated) {
		if (initiated == null) {
			throw new IllegalArgumentException("La unidad administrativa (órgano) iniciante del expediente debe ser distinto de NULL.");
		}
		this.initiated = initiated;
	}

	public Stakeholder getCausative() {
		if (causative == null) {
			throw new IllegalStateException("El causante del expediente debe ser distinto de NULL.");
		}
		return causative;
	}

	public void setCausative(Stakeholder causative) {
		if (causative == null) {
			throw new IllegalArgumentException("El causante del expediente debe ser distinto de NULL.");
		}
		this.causative = causative;
	}

}
