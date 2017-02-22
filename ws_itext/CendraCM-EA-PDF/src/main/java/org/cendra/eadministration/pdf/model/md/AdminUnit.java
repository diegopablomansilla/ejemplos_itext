package org.cendra.eadministration.pdf.model.md;

public class AdminUnit {

	protected String labelDocType = "documento";

	private String code;
	private String shortName;
	private String name;
	private String pathImg;

	public AdminUnit(String labelDocType) {
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

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		if (pathImg == null) {
			throw new IllegalArgumentException("El logo del encabezado de página del " + labelDocType + " debe ser distinto de NULL.");
		}
		pathImg = pathImg.trim();
		if (pathImg.length() == 0) {
			throw new IllegalArgumentException("El logo del encabezado de página del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.pathImg = pathImg;
	}

	public String toJson() {
		String r = "";

		r += "{";
		r += "\"code\": \"" + this.getCode() + "\",";
		r += "\"shortName\": \"" + this.getShortName() + "\",";
		r += "\"name\": \"" + this.getName() + "\",";
		r += "}";

		return r;
	}

	public String toString() {
		String r = this.getName() + " (" + this.getCode() + " - " + this.getShortName() + ")";

		return r;
	}

}
