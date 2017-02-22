package org.cendra.eadministration.model;

import java.util.Calendar;

import org.cendra.eadministration.model.document.DocumentType;
import org.cendra.eadministration.model.ou.Ou;

public class Cudap {

	private DocumentType documentType;
	private Ou org;
	private Integer number;
	private Integer year;

	public Cudap() {
		year = Calendar.getInstance().get(Calendar.YEAR);
	}

	public DocumentType getDocumentType() {
		if (documentType == null) {
			throw new IllegalStateException("El tipo de documento para el CUDAP debe ser distinto de NULL.");
		}
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		if (documentType == null) {
			throw new IllegalArgumentException("El tipo de documento para el CUDAP debe ser distinto de NULL.");
		}

		this.documentType = documentType;

	}

	public Ou getOrg() {
		if (org == null) {
			throw new IllegalStateException("La organización para el CUDAP debe ser distinto de NULL.");
		}
		return org;
	}

	public void setOrg(Ou org) {

		if (org == null) {
			throw new IllegalArgumentException("La organización para el CUDAP debe ser distinto de NULL.");
		}
	}

	public Integer getNumber() {
		if (number == null) {
			throw new IllegalStateException("El número incremental para el CUDAP debe ser distinto de NULL.");
		}
		return number;
	}

	public void setNumber(Integer number) {
		if (number == null) {
			throw new IllegalArgumentException("El número incremental para el CUDAP debe ser distinto de NULL.");
		}
		if (number < 1) {
			throw new IllegalArgumentException("El número incremental para el CUDAP debe ser mayor que 0 (cero). Se intento asignar el valor: " + number + ".");
		}
		this.number = number;
	}

	public Integer getYear() {
		if (year == null) {
			throw new IllegalStateException("El año para el CUDAP debe ser distinto de NULL.");
		}
		return year;
	}

	public void setYear(Integer year) {
		if (year == null) {
			throw new IllegalArgumentException("El año para el CUDAP debe ser distinto de NULL.");
		}
		if (year < 1) {
			throw new IllegalArgumentException("El año para el CUDAP debe ser mayor que 0 (cero). Se intento asignar el valor: " + year + ".");
		}
		this.year = year;
	}

	public String toString() {

		return this.getDocumentType().getCode() + "-" + this.getOrg() + ":" + this.getNumber() + "/" + this.getYear();

	}

}
