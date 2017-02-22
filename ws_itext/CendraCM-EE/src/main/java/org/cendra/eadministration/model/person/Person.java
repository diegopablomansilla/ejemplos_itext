package org.cendra.eadministration.model.person;

import org.cendra.eadministration.model.Stakeholder;

public class Person implements Stakeholder {

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

	public String toString() {
		return "[" + this.getId() + "]";
	}

	public String toStringStakeholder() {

		return toString();
	}

}
