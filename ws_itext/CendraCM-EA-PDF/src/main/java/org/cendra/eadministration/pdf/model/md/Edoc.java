package org.cendra.eadministration.pdf.model.md;

import java.util.HashMap;
import java.util.Map;

public abstract class Edoc extends PdfA3a {

	private String cudap;
	private AdminUnit adminUnit; // quien lo creo
	// private String footer;
	private String pathImg;

	private User user; // quien lo creo

	private Map<String, Object> properties = new HashMap<String, Object>();

	// //////////////////////////////////////
	
	public String getCudapFile() {
		return getCudap().replace("/", "_").replace("-", "_").replace(":", "_");
	}

	public String getCudap() {
		if (cudap == null) {
			throw new IllegalStateException("El CUDAP del " + labelDocType + " debe ser distinto de NULL.");
		}
		return cudap;
	}

	public void setCudap(String cudap) {
		if (cudap == null) {
			throw new IllegalArgumentException("El CUDAP del " + labelDocType + " debe ser distinto de NULL.");
		}
		cudap = cudap.trim();
		if (cudap.length() == 0) {
			throw new IllegalArgumentException("El CUDAP del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.cudap = cudap;
		super.setTitle(cudap);
		super.setKeywords(cudap);
	}

	public void setTitle(String title) {
		setCudap(title);
	}

	public AdminUnit getAdminUnit() {
		if (adminUnit == null) {
			throw new IllegalStateException("La unidad administrativa (órgano) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return adminUnit;
	}

	public void setAdminUnit(AdminUnit initiated) {
		if (initiated == null) {
			throw new IllegalArgumentException("La unidad administrativa (órgano) iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}

		this.adminUnit = initiated;
		super.setAuthor(initiated.toString());
	}

	// public String getFooter() {
	// return footer;
	// }
	//
	// public void setFooter(String footer) {
	// if (footer == null) {
	// throw new IllegalArgumentException("El pié de página del " + labelDocType + " debe ser distinto de NULL.");
	// }
	// footer = footer.trim();
	// if (footer.length() == 0) {
	// throw new IllegalArgumentException("El pié de página del " + labelDocType + " debe ser distinto de vacio.");
	// }
	// this.footer = footer;
	// }

	public void setAuthor(String author) {
		// setInitiated(author);
	}

	public String getAuthor() {
		return super.getAuthor() + " - " + getUser().toString();
	}

	public void add(String k, Object v) {
		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de vacio.");
		}

		if (v == null) {
			throw new IllegalArgumentException("El valor de la propiedad del " + labelDocType + " debe ser distinto de NULL.");
		}

		if (v.toString().length() == 0) {
			throw new IllegalArgumentException("El valor de la propiedad del " + labelDocType + " debe ser distinto de vacio.");
		}

		if (properties.containsKey(k)) {
			throw new IllegalArgumentException("Este " + labelDocType + " ya contiene una propiedad llamada " + k + ".");
		}

		properties.put(k, v);
	}

	public void set(String k, Object v) {

		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de vacio.");
		}

		if (v == null) {
			throw new IllegalArgumentException("El valor de la propiedad del " + labelDocType + " debe ser distinto de NULL.");
		}

		if (v.toString().length() == 0) {
			throw new IllegalArgumentException("El valor de la propiedad del " + labelDocType + " debe ser distinto de vacio.");
		}

		if (properties.containsKey(k) == false) {
			throw new IllegalArgumentException("Este " + labelDocType + " ya contiene una propiedad llamada " + k + ".");
		}

		properties.put(k, v);
	}

	public Object get(String k) {
		if (k == null) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de NULL.");
		}

		k = k.trim();

		if (k.length() == 0) {
			throw new IllegalArgumentException("El nombre de la propiedad del " + labelDocType + " debe ser distinto de vacio.");
		}

		if (properties.containsKey(k) == false) {
			throw new IllegalArgumentException("Este " + labelDocType + " no contiene una propiedad llamada " + k + ".");
		}

		return properties.get(k);
	}

	public String[][] getProperties() {
		Object[] keys = properties.keySet().toArray();

		String[][] matrix = new String[keys.length][2];

		for (int i = 0; i < keys.length; i++) {
			matrix[i][0] = keys[i].toString();
			matrix[i][1] = properties.get(keys[i].toString()).toString();
		}

		return matrix;

	}

	public User getUser() {
		if (user == null) {
			throw new IllegalStateException("El usuario iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		return user;
	}

	public void setUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("El usuario iniciante del " + labelDocType + " debe ser distinto de NULL.");
		}
		this.user = user;
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
		String[][] properties = getProperties();

		String props = "";
		if (properties != null) {

			for (int i = 0; i < properties.length; i++) {
				if (i == 0) {
					props += "\"properties\": [";
				}

				if (i == properties.length - 1) {
					props += "{\"" + properties[i][0] + "\":";
					props += "\"" + properties[i][1] + "\"}]";
				} else {
					props += "{\"" + properties[i][0] + "\":";
					props += "\"" + properties[i][1] + "\"},";
				}

			}

		}

		String r = "";

		r += "{";

		r += super.toJson() + ", ";

		r += "\"cudap\": \"" + this.getCudap() + "\",";
		r += "\"initiated\": \"" + this.getAdminUnit() + "\",";
		// r += "\"footer\": \"" + this.getFooter() + "\",";
		r += "\"user\": " + this.getUser().toJson() + ", ";

		r += props;

		r += "}";

		return r;
	}

}
