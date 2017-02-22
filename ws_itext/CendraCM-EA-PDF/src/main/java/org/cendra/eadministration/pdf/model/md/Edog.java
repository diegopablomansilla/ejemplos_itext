package org.cendra.eadministration.pdf.model.md;

import org.cendra.eadministration.pdf.model.md.json.EDogJson;

public class Edog extends Edoc {

	private String pathSource;
	private EDogJson eDog;

	public Edog() {
		super();
		labelDocType = "Expediente";
	}

	public String getPathSource() {
		if (pathSource == null) {
			throw new IllegalStateException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		return pathSource;
	}

	public void setPathSource(String pathSource) {
		if (pathSource == null) {
			throw new IllegalArgumentException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		this.pathSource = pathSource;
	}

	public EDogJson geteDog() {
		return eDog;
	}

	public void seteDog(EDogJson eDog) {
		this.eDog = eDog;
	}

}
