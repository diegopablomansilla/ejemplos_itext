package org.cendra.eadministration.model.document;

public abstract class DocumentType {

	private String id;
	private String code;
	private String name;
	private String comment;

	public String getId() {
		if (id == null) {
			throw new IllegalStateException("El id de la persona debe ser distinto de NULL.");
		}
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("El id de la persona debe ser distinto de NULL.");
		}
		id = id.trim();
		if (id.length() == 0) {
			throw new IllegalArgumentException("El id de la persona debe ser distinto de vacio.");
		}
		this.id = id;
	}

	public String getCode() {
		if (code == null) {
			throw new IllegalStateException("El código del tipo de documento debe ser distinto de NULL.");
		}
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("El código del tipo de documento debe ser distinto de NULL.");
		}

		code = code.trim();

		if (code.length() == 0) {
			throw new IllegalArgumentException("El código del tipo de documento debe ser distinto de vacio.");
		}
		this.code = code;
	}

	public String getName() {
		if (name == null) {
			throw new IllegalStateException("El nombre del tipo de documento debe ser distinto de NULL.");
		}
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("El nombre del tipo de documento debe ser distinto de NULL.");
		}
		name = name.trim();

		if (name.length() == 0) {
			throw new IllegalArgumentException("El nombre del tipo de documento debe ser distinto de vacio.");
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

		return "[" + this.getId() + "] " + "(" + this.getCode() + ") " + this.getName();
	}

}
