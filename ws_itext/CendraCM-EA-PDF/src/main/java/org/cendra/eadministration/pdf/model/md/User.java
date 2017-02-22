package org.cendra.eadministration.pdf.model.md;


public class User {

	protected String labelDocType = "documento";

	private String id;
	private String name;
	private Person person;

	public User(String labelDocType) {
		super();
		this.labelDocType = labelDocType;
	}

	public String getId() {
		if (id == null) {
			throw new IllegalStateException("El id del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("El id del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		id = id.trim();
		if (id.length() == 0) {
			throw new IllegalArgumentException("El id del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.id = id;
	}

	public String getName() {
		if (name == null) {
			throw new IllegalStateException("El nombre del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("El nombre del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		name = name.trim();
		if (name.length() == 0) {
			throw new IllegalArgumentException("El nombre del usuario (persona) iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.name = name;
	}

	public Person getPerson() {
		if (person == null) {
			throw new IllegalStateException("La persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return person;
	}

	public void setPerson(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("La persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		this.person = person;
	}

	public String toJson() {
		String r = "";

		r += "{";
		r += "\"id\": \"" + this.getId() + "\",";
		r += "\"name\": \"" + this.getName() + "\",";
		r += "\"person\": " + this.getPerson();
		r += "}";

		return r;
	}

	public String toString() {
		String r = this.getPerson().getLastNames()[0] + ", " + this.getPerson().getNames()[0] + " (" + this.getPerson().getIdentities()[0].getShortName() + " " + this.getPerson().getIdentities()[0].getNumber() + ") " + this.getName();

		return r;
	}

}
