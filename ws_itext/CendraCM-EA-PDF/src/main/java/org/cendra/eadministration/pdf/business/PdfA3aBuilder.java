package org.cendra.eadministration.pdf.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cendra.eadministration.pdf.model.md.PdfA3a;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfACopy;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Creates a PDF that conforms with PDF/A-3 Level A.
 */

public abstract class PdfA3aBuilder {

	private static String PATH_FONT = GlobalProperties.get(GlobalProperties.PATH_FONT);
	private static String FONT_NORMAL = GlobalProperties.get(GlobalProperties.FONT_NORMAL);
	private static String FONT_BOLD = GlobalProperties.get(GlobalProperties.FONT_BOLD);

	// All fonts shall be embedded. PDF/A requirement.
	protected final static Font BOLD_24 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 24);
	protected final static Font NORMAL_24 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 24);

	protected final static Font BOLD_18 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 18);
	protected final static Font NORMAL_18 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 18);

	protected final static Font BOLD_14 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 14);
	protected final static Font NORMAL_14 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 14);

	protected final static Font BOLD_11 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 11);
	protected final static Font NORMAL_11 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 11);

	protected final static Font BOLD_10 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 10);
	protected final static Font NORMAL_10 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 10);

	protected final static Font BOLD_8 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 8);
	protected final static Font NORMAL_8 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 8);

	protected final static Font BOLD_7 = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);
	protected final static Font NORMAL_7 = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);

	protected static Font bold7Gray = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);
	protected static Font normal7Gray = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);

	protected static Font bold10Blue = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 10);
	protected static Font bold7Blue = FontFactory.getFont(PATH_FONT + FONT_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);
	protected static Font normal7Blue = FontFactory.getFont(PATH_FONT + FONT_NORMAL, BaseFont.WINANSI, BaseFont.EMBEDDED, 7);

	/** A path to a color profile. */
	protected static final String ICC = "resources/data/sRGB_CS_profile.icm";

	abstract String build(PdfA3a pdfA3a) throws Exception;

	protected PdfAWriter pdfAWriter;
	protected PdfACopy pdfACopy;

	public PdfA3aBuilder() {
		super();
		normal7Blue.setColor(BaseColor.BLUE);
		bold7Blue.setColor(BaseColor.BLUE);
		bold10Blue.setColor(BaseColor.BLUE);

		bold7Gray.setColor(BaseColor.GRAY);
		normal7Gray.setColor(BaseColor.GRAY);
	}

	/**
	 * Creates a PDF that conforms with PDF/A-3 Level B.
	 * 
	 * @param dest
	 *            the path to the resulting PDF
	 * @throws IOException
	 * @throws DocumentException
	 */
	protected Document initPdf(PdfA3a pdfA3a) throws IOException, DocumentException {

		String filePath = GlobalProperties.get(GlobalProperties.PATH_RESULT) + pdfA3a.getFileName() + ".pdf";
		pdfA3a.setPathResultPdf(filePath);

		File file = new File(filePath);
		file.getParentFile().mkdirs();

		Document document = new Document(PageSize.A4);

		// PDF/A-3a
		// Create PdfAWriter with the required conformance level
		pdfAWriter = PdfAWriter.getInstance(document, new FileOutputStream(filePath), PdfAConformanceLevel.PDF_A_3A);
		pdfAWriter.setPdfVersion(PdfWriter.VERSION_1_7);

		// ====================
		// TAGGED PDF
		// Make document tagged
		pdfAWriter.setTagged();

		// ===============
		// PDF/UA
		// Set document metadata
		pdfAWriter.setViewerPreferences(PdfWriter.DisplayDocTitle);

		document.addLanguage("es-AR");
		document.addCreationDate();

		document.addTitle(pdfA3a.getTitle());
		document.addSubject(pdfA3a.getSubject());
		document.addAuthor(pdfA3a.getAuthor());
		document.addCreator(pdfA3a.getCreator());
		document.addKeywords(pdfA3a.getKeywords());

		PdfDictionary info = pdfAWriter.getInfo();
		PdfDate pdfDate = (PdfDate) info.get(PdfName.CREATIONDATE);
		pdfA3a.setCreated(pdfDate.getW3CDate());

		pdfAWriter.createXmpMetadata();

		// =====================
		document.open();

		// PDF/A-3b
		// Set output intents
		ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
		pdfAWriter.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
		// ===================

		return document;

	}

	protected Document initPdfCopy(PdfA3a pdfA3a) throws IOException, DocumentException {

		// String filePath = PATH_RESULT + File.separatorChar + "z" + ".pdf";

		// String filePath = PATH_RESULT + File.separatorChar + pdfA3a.getFileName() + ".pdf";

		// File file = new File(filePath);
		// file.getParentFile().mkdirs();

		Document document = new Document(PageSize.A4);

		// PDF/A-3a
		// Create PdfAWriter with the required conformance level
		// pdfAWriter = PdfAWriter.getInstance(document, new FileOutputStream(filePath), PdfAConformanceLevel.PDF_A_3A);
		pdfACopy = new PdfACopy(document, new FileOutputStream(GlobalProperties.get(GlobalProperties.PATH_RESULT) + "y.pdf"), PdfAConformanceLevel.PDF_A_3A);
		pdfACopy.setPdfVersion(PdfWriter.VERSION_1_7);

		// ====================
		// TAGGED PDF
		// Make document tagged
		pdfACopy.setTagged();

		// ===============
		// PDF/UA
		// Set document metadata
		pdfACopy.setViewerPreferences(PdfWriter.DisplayDocTitle);

		document.addLanguage("es-AR");
		document.addCreationDate();

		document.addTitle(pdfA3a.getTitle());
		document.addSubject(pdfA3a.getSubject());
		document.addAuthor(pdfA3a.getAuthor());
		document.addCreator(pdfA3a.getCreator());
		document.addKeywords(pdfA3a.getKeywords());

		PdfDictionary info = pdfACopy.getInfo();
		PdfDate pdfDate = (PdfDate) info.get(PdfName.CREATIONDATE);
		pdfA3a.setCreated(pdfDate.getW3CDate());

		pdfACopy.createXmpMetadata();

		// =====================
		document.open();

		// PDF/A-3b
		// Set output intents
		ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
		pdfACopy.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
		// ===================

		return document;

	}

	protected void addAttachment(PdfA3a pdfA3a, String name, String desc, String contentType) throws IOException {
		addAttachment(true, pdfA3a, name, desc, contentType);
	}

	protected void addAttachment(boolean w, PdfA3a pdfA3a, String name, String desc, String contentType) throws IOException {

		String filePath = GlobalProperties.get(GlobalProperties.PATH_RESOURCES_JSON) + File.separatorChar + name;

		// Creating PDF/A-3 compliant attachment.
		PdfDictionary pdfDictionary = new PdfDictionary();
		pdfDictionary.put(PdfName.MODDATE, new PdfDate());

		if (w) {
			PdfFileSpecification pdfFileSpecification = PdfFileSpecification.fileEmbedded(pdfAWriter, filePath, name, null, contentType, pdfDictionary, 0);
			pdfFileSpecification.put(new PdfName("AFRelationship"), new PdfName("Data"));
			pdfAWriter.addFileAttachment(desc, pdfFileSpecification);
		} else {
			PdfFileSpecification pdfFileSpecification = PdfFileSpecification.fileEmbedded(pdfACopy, filePath, name, null, contentType, pdfDictionary, 0);
			pdfFileSpecification.put(new PdfName("AFRelationship"), new PdfName("Data"));
			pdfACopy.addFileAttachment(desc, pdfFileSpecification);
		}

	}

	protected void copyAttachment(PdfA3a pdfA3a, PdfReader reader) throws IOException {

		Map<String, String> mapTmp = new HashMap<String, String>();

		// --------------------------------------------
		PdfDictionary catalog = null;
		PdfDictionary documentNames = null;
		PdfDictionary embeddedFiles = null;
		PdfDictionary fileArray = null;
		PdfDictionary file = null;
		PRStream stream = null;

		catalog = reader.getCatalog();

		documentNames = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES));

		if (documentNames != null) {
			embeddedFiles = (PdfDictionary) PdfReader.getPdfObject(documentNames.get(PdfName.EMBEDDEDFILES));
			if (embeddedFiles != null) {
				PdfArray filespecs = embeddedFiles.getAsArray(PdfName.NAMES);
				for (int i = 0; i < filespecs.size(); i++) {
					fileArray = filespecs.getAsDict(i);
					i++;
					fileArray = filespecs.getAsDict(i);
					file = fileArray.getAsDict(PdfName.EF);

					if (file != null) {
						for (PdfName key : file.getKeys()) {
							String fileName = fileArray.getAsString(key).toString();

							if (mapTmp.containsKey(fileName) == false) {

								stream = (PRStream) PdfReader.getPdfObject(file.getAsIndirectObject(key));

								SO.createFile(GlobalProperties.get(GlobalProperties.PATH_RESULT), fileName, new String(PdfReader.getStreamBytes(stream)));

								addAttachment(false, pdfA3a, fileName, "Metadatos", "application/json");

								mapTmp.put(fileName, fileName);
							}

						}

					}

				}
			}
		}

		// --------------------------------------------

	}

}
