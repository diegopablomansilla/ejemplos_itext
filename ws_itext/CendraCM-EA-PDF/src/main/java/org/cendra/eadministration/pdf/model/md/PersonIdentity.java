package org.cendra.eadministration.pdf.model.md;

public class PersonIdentity {

	protected String labelDocType = "documento";

	private String code;
	private String shortName;
	private String name;
	private String number;

	public PersonIdentity(String labelDocType) {
		super();
		this.labelDocType = labelDocType;
	}

	public String getCode() {
		if (code == null) {
			throw new IllegalStateException("El código del tipo documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("El código del tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		code = code.trim();
		if (code.length() == 0) {
			throw new IllegalArgumentException("El código del tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.code = code;
	}

	public String getShortName() {
		if (shortName == null) {
			throw new IllegalStateException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return shortName;
	}

	public void setShortName(String shortName) {
		if (shortName == null) {
			throw new IllegalArgumentException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		shortName = shortName.trim();
		if (shortName.length() == 0) {
			throw new IllegalArgumentException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.shortName = shortName;
	}

	public String getName() {
		if (name == null) {
			throw new IllegalStateException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		name = name.trim();
		if (name.length() == 0) {
			throw new IllegalArgumentException("El tipo de documento de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.name = name;
	}

	public String getNumber() {
		if (number == null) {
			throw new IllegalStateException("El número de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return number;
	}

	public void setNumber(String number) {
		if (number == null) {
			throw new IllegalArgumentException("El número de documento de la persona iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		number = number.trim();
		if (number.length() == 0) {
			throw new IllegalArgumentException("El número de documento de la persona iniciante del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.number = number;
	}

	public String toJson() {
		String r = "";

		r += "{";
		r += "\"code\": \"" + this.getCode() + "\",";
		r += "\"shortName\": \"" + this.getShortName() + "\",";
		r += "\"name\": \"" + this.getName() + "\",";
		r += "\"number\": \"" + this.getNumber() + "\"";
		r += "}";

		return r;
	}

}
