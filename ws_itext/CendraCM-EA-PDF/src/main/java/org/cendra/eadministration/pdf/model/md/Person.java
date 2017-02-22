package org.cendra.eadministration.pdf.model.md;

public class Person {

	protected String labelDocType = "documento";

	private String id;
	private String[] names;
	private String[] lastNames;
	private PersonIdentity[] identities;

	public Person(String labelDocType) {
		super();
		this.labelDocType = labelDocType;
	}

	public String getId() {
		if (id == null) {
			throw new IllegalStateException("El id de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("El id de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		id = id.trim();
		if (id.length() == 0) {
			throw new IllegalArgumentException("El id de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.id = id;
	}

	public String[] getNames() {
		if (names == null) {
			throw new IllegalStateException("Los nombres de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		for (String name : names) {
			if (name == null) {
				throw new IllegalStateException("El nombre de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
			}
		}

		return names;
	}

	public void setNames(String[] names) {
		if (names == null) {
			throw new IllegalArgumentException("Los nombres de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		for (int i = 0; i < names.length; i++) {
			if (names[i] == null) {
				throw new IllegalStateException("El nombre de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
			}
			names[i] = names[i].trim();
			if (names[i].length() == 0) {
				throw new IllegalArgumentException("El nombre de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
			}
		}
		this.names = names;
	}

	public String[] getLastNames() {
		if (lastNames == null) {
			throw new IllegalStateException("Los nombres de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		for (String name : lastNames) {
			if (name == null) {
				throw new IllegalStateException("El nombre de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
			}
		}

		return lastNames;
	}

	public void setLastNames(String[] lastNames) {
		if (lastNames == null) {
			throw new IllegalArgumentException("Los apellidos de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		for (int i = 0; i < lastNames.length; i++) {
			if (lastNames[i] == null) {
				throw new IllegalStateException("El apellidos de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
			}
			lastNames[i] = lastNames[i].trim();
			if (lastNames[i].length() == 0) {
				throw new IllegalArgumentException("El apellidos de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
			}
		}
		this.lastNames = lastNames;
	}

	public PersonIdentity[] getIdentities() {
		if (identities == null) {
			throw new IllegalStateException("Los documentos de identidad de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return identities;
	}

	public void setIdentities(PersonIdentity[] identities) {
		if (identities == null) {
			throw new IllegalStateException("Los documentos de identidad de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		this.identities = identities;
	}

	public String toJson() {
		String r = "";

		r += "{";
		r += "\"id\": \"" + this.getId() + "\",";
		r += "\"lastNames\": [";
		for (int i = 0; i < lastNames.length; i++) {
			r += "\"" + lastNames[i] + "\"";

			if (i < lastNames.length - 1) {
				r += ", ";
			}
		}
		r += "]" + ", ";

		r += "\"names\": [";
		for (int i = 0; i < names.length; i++) {
			r += "\"" + names[i] + "\"";

			if (i < names.length - 1) {
				r += ", ";
			}
		}
		r += "]" + ", ";

		r += "\"identities\": [";
		for (int i = 0; i < identities.length; i++) {
			r += identities[i].toJson();

			if (i < identities.length - 1) {
				r += ", ";
			}
		}
		r += "]";

		r += "}";

		return r;
	}

}
