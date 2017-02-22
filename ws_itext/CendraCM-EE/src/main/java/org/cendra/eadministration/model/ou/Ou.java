package org.cendra.eadministration.model.ou;

import org.cendra.eadministration.model.Stakeholder;

public class Ou implements Stakeholder {

	private String code;
	private String name;
	private String comment;
	private OuType ouType;
	private Ou ouSource;

	public String getCode() {
		if (code == null) {
			throw new IllegalStateException("El código de la Unidad Administrativa debe ser distinto de NULL.");
		}
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("El código de la Unidad Administrativa debe ser distinto de NULL.");
		}

		code = code.trim();

		if (code.length() == 0) {
			throw new IllegalArgumentException("El código de la Unidad Administrativa debe ser distinto de vacio.");
		}
		this.code = code;
	}

	public String getName() {
		if (name == null) {
			throw new IllegalStateException("El nombre de la Unidad Administrativa debe ser distinto de NULL.");
		}
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("El nombre de la Unidad Administrativa debe ser distinto de NULL.");
		}
		name = name.trim();

		if (name.length() == 0) {
			throw new IllegalArgumentException("El nombre de la Unidad Administrativa debe ser distinto de vacio.");
		}
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (comment != null) {
			comment = comment.trim();
		}
		this.comment = comment;
	}

	public OuType getOuType() {
		if (ouType == null) {
			throw new IllegalStateException("El tipo de la Unidad Administrativa debe se distinto de NULL.");
		}
		return ouType;
	}

	public void setOuType(OuType ouType) {
		if (ouType == null) {
			throw new IllegalArgumentException("El tipo de la Unidad Administrativa debe se distinto de NULL.");
		}
		this.ouType = ouType;
	}

	public Ou getOuSource() {
		return ouSource;
	}

	public void setOuSource(Ou ouSource) {
		this.ouSource = ouSource;
	}

	public String toString() {

		return "(" + this.getCode() + ") " + this.getName() + " [" + this.getOuType() + "]";
	}

	public String toStringStakeholder() {

		return toString();
	}

}
