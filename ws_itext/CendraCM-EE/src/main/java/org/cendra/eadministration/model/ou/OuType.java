package org.cendra.eadministration.model.ou;

public class OuType {

	private String code;
	private String name;
	private String comment;

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

	public String toString() {

		return "(" + this.getCode() + ") " + this.getName();
	}

}
