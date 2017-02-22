package org.cendra.eadministration.model;

public abstract class Identifier {

	private String id;

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

}
