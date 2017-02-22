package org.cendra.eadministration.model.user;

import org.cendra.eadministration.model.person.Person;

public class User {

	private String id;
	private Person person;

	public String getId() {
		if (id == null) {
			throw new IllegalStateException("El id del usuario debe ser distinto de NULL.");
		}
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("El id del usuario debe ser distinto de NULL.");
		}
		id = id.trim();
		if (id.length() == 0) {
			throw new IllegalArgumentException("El id del usuario debe ser distinto de vacio.");
		}
		this.id = id;
	}

	public Person getPerson() {
		if (person == null) {
			throw new IllegalStateException("La persona debe ser distinto de NULL.");
		}
		return person;
	}

	public void setPerson(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("La persona debe ser distinto de NULL.");
		}
		this.person = person;
	}

	public String toString() {
		return "[" + this.getId() + "] " + this.getPerson();
	}

}
