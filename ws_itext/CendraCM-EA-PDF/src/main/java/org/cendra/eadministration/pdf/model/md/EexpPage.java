package org.cendra.eadministration.pdf.model.md;

public class EexpPage extends Edoc {

	private String pathSourceEe;
	private String pathSourceEd;

	private int startPage;

	public EexpPage() {
		super();
		labelDocType = "Expediente";
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		if (startPage < 1) {
			throw new IllegalArgumentException("El folio de " + labelDocType + " debe ser un múmero mayor a 0.");
		}
		this.startPage = startPage;
	}

	public String getPathSourceEe() {
		if (pathSourceEe == null) {
			throw new IllegalStateException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		return pathSourceEe;
	}

	public void setPathSourceEe(String pathSourceEe) {
		if (pathSourceEe == null) {
			throw new IllegalArgumentException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		this.pathSourceEe = pathSourceEe;
	}

	public String getPathSourceEd() {
		if (pathSourceEd == null) {
			throw new IllegalStateException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		return pathSourceEd;
	}

	public void setPathSourceEd(String pathSourceEd) {
		if (pathSourceEd == null) {
			throw new IllegalArgumentException("El pdf a incrustar en el " + labelDocType + " debe ser distinto de NULL.");
		}
		this.pathSourceEd = pathSourceEd;
	}

}
