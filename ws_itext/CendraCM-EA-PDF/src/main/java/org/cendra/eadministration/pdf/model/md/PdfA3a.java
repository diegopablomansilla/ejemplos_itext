package org.cendra.eadministration.pdf.model.md;

public abstract class PdfA3a {

	protected String labelDocType = "documento";

	private String fileName;
	private String fileNameJson;
	private String title;
	private String subject;
	private String author;
	private String creator = "Mara Soft para E-Admnistración (Pública)"; // "CendraCM-EA-PDF";
	private String keywords;
	private String created;
	private String pathResultPdf;
	private String pathResultJson;

	public String getFileNameJson() {
		return fileNameJson;
	}

	public void setFileNameJson(String fileNameJson) {
		this.fileNameJson = fileNameJson;
	}

	public String getPathResultPdf() {
		return pathResultPdf;
	}

	public void setPathResultPdf(String pathResultPdf) {
		this.pathResultPdf = pathResultPdf;
	}

	public String getPathResultJson() {
		return pathResultJson;
	}

	public void setPathResultJson(String pathResultJson) {
		this.pathResultJson = pathResultJson;
	}

	public String getFileName() {
		if (fileName == null) {
			throw new IllegalStateException("El nombre de archivo del " + labelDocType + " debe ser distinto de NULL.");
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("El nombre de archivo del " + labelDocType + " debe ser distinto de NULL.");
		}
		fileName = fileName.trim();
		if (fileName.length() == 0) {
			throw new IllegalArgumentException("El nombre de archivo del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.fileName = fileName;
	}

	public String getTitle() {
		if (title == null) {
			throw new IllegalStateException("El título del " + labelDocType + " debe ser distinto de NULL.");
		}
		return title;
	}

	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("El título del " + labelDocType + " debe ser distinto de NULL.");
		}
		title = title.trim();
		if (title.length() == 0) {
			throw new IllegalArgumentException("El título del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.title = title;
	}

	public String getSubject() {
		if (subject == null) {
			throw new IllegalStateException("El asunto del " + labelDocType + " debe ser distinto de NULL.");
		}
		return subject;
	}

	public void setSubject(String subject) {
		if (subject == null) {
			throw new IllegalArgumentException("El asunto del " + labelDocType + " debe ser distinto de NULL.");
		}
		subject = subject.trim();
		if (subject.length() == 0) {
			throw new IllegalArgumentException("El asunto del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.subject = subject;
	}

	public String getAuthor() {
		if (author == null) {
			throw new IllegalStateException("El autor del " + labelDocType + " debe ser distinto de NULL.");
		}
		return author;
	}

	public void setAuthor(String author) {
		if (author == null) {
			throw new IllegalArgumentException("El autor del " + labelDocType + " debe ser distinto de NULL.");
		}
		author = author.trim();
		if (author.length() == 0) {
			throw new IllegalArgumentException("El autor del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.author = author;
	}

	public String getCreator() {
		if (creator == null) {
			throw new IllegalStateException("El creador del " + labelDocType + " debe ser distinto de NULL.");
		}
		return creator;
	}

	// public void setCreator(String creator) {
	// this.creator = creator;
	// }

	public String getKeywords() {
		if (keywords == null) {
			throw new IllegalStateException("El " + labelDocType + " debe contener al menos una palabra clave. Palabras claves deben ser distinto de NULL.");
		}
		return keywords;
	}

	public void setKeywords(String keywords) {
		if (keywords == null) {
			throw new IllegalArgumentException("El " + labelDocType + " debe contener al menos una palabra clave. Palabras claves deben ser distinto de NULL.");
		}
		keywords = keywords.trim();
		if (keywords.length() == 0) {
			throw new IllegalArgumentException("El " + labelDocType + " debe contener al menos una palabra clave. Palabras claves deben ser distinto de vacio.");
		}
		this.keywords = keywords;
	}

	public String getCreated() {
		if (created == null) {
			throw new IllegalArgumentException("La fecha de creación del " + labelDocType + " debe ser distinto de NULL.");
		}
		return created;
	}

	public void setCreated(String created) {
		if (created == null) {
			throw new IllegalStateException("La fecha de creación del " + labelDocType + " debe ser distinto de NULL.");
		}
		created = created.trim();
		if (created.length() == 0) {
			throw new IllegalArgumentException("La fecha de creación del " + labelDocType + " debe ser distinto de vacio.");
		}
		this.created = created;
	}

	public String toJson() {
		return "\"labelDocType\":\"" + labelDocType + "\", \"fileName\":\"" + fileName + "\", \"title\":\"" + title + "\", \"subject\":\"" + subject + "\", \"author\":\"" + author + "\", \"creator\":\"" + creator + "\", \"keywords\":\"" + keywords + "\", \"created\":\"" + created + "\"";
	}

}
