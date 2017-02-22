package org.cendra.eadministration.pdf.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.cendra.eadministration.pdf.model.md.Eexp;
import org.cendra.eadministration.pdf.model.md.EexpPage;
import org.cendra.eadministration.pdf.model.md.PdfA3a;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfACopy;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.SimpleBookmark;

public class EexpPageBuilder extends PdfA3aBuilder {

	private Image image2;
	private int pageCount;

	PdfContentByte canvas;

	private String eePathResult = null;

	private String cudapEd;

	public String build(PdfA3a pdfA3a) throws Exception {

		if (pdfA3a instanceof EexpPage == false) {
			throw new IllegalArgumentException("Se esperaba una modelo de para Expediente (EE), es decir un argumento de clase " + Eexp.class + " y no una clase " + pdfA3a.getClass());
		}
		EexpPage eexpPage = (EexpPage) pdfA3a;

		String f1 = eexpPage.getPathSourceEe();
		PdfReader reader1 = new PdfReader(f1);

		eexpPage.setCudap(reader1.getInfo().get("Title"));

		eexpPage.setFileName(eexpPage.getCudapFile() + "_folios");
		eexpPage.setSubject(reader1.getInfo().get("Subject"));

		Document document = initPdf(pdfA3a);

		String pathResultPdf = eexpPage.getPathResultPdf();

		eexpPage.setFileNameJson(eexpPage.getFileName() + ".json");

		eexpPage.setPathResultJson(GlobalProperties.get(GlobalProperties.PATH_RESULT) + eexpPage.getFileNameJson());

		SO.createFile(GlobalProperties.get(GlobalProperties.PATH_RESULT), eexpPage.getFileNameJson(), eexpPage.toJson());

		// addAttachment(eexpPage, eexpPage.getFileNameJson(), "Metadatos folios " + eexpPage.getCudap(), "application/json");

		buildBody(document, eexpPage);

		document.close();

		buildFinalEE(eexpPage);

		SO.delete(eexpPage.getPathResultJson());
		SO.delete(eexpPage.getPathResultPdf());
		SO.delete(pathResultPdf);

		eexpPage.setPathResultPdf(eePathResult);

		return eexpPage.getPathResultPdf();

	}

	private int countPagesEE(String path) throws DocumentException, IOException {

		int pc = 0;

		PdfReader reader = new PdfReader(path);

		pc = reader.getNumberOfPages();

		reader.close();

		return pc - 1;

	}

	private void buildBody(Document document, EexpPage eexpPage) throws MalformedURLException, IOException, DocumentException, ParseException {

		eexpPage.setFileName(eexpPage.getFileName() + "2.pdf");
		eexpPage.setPathResultPdf(GlobalProperties.get(GlobalProperties.PATH_RESULT) + eexpPage.getFileName());

		// String filePath = PATH_RESULT + File.separatorChar + eexpPage.getFileName() + "2.pdf";
		String filePath = eexpPage.getPathResultPdf();

		if (eexpPage.getAdminUnit().getPathImg() != null) {

			image2 = Image.getInstance(eexpPage.getAdminUnit().getPathImg());
			// PDF/UA
			// Set alt text
			image2.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo Universidad Nacional de Córdoba"));
			image2.scaleToFit(70, 70);

		}

		// leo un doc existente
		PdfReader pdfReader = new PdfReader(eexpPage.getPathSourceEd());
		pageCount = pdfReader.getNumberOfPages();

		cudapEd = pdfReader.getInfo().get("Title");

		// ------------------------------------------------------

		PdfPTable pdfPTable = new PdfPTable(1);
		pdfPTable.setWidthPercentage(100);

		PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(filePath));

		PdfImportedPage pdfImportedPage;

		buildHeaderFooter(eexpPage);

		int pc = countPagesEE(eexpPage.getPathSourceEe());

		eexpPage.setStartPage(pc + 1);
		int startPage = eexpPage.getStartPage();
		int page = 0;

		for (int i = 1; i <= pageCount; i++) {

			page = startPage + i - 1;

			pdfImportedPage = pdfAWriter.getImportedPage(pdfReader, i);
			Image image = Image.getInstance(pdfImportedPage);
			image.setAccessibleAttribute(PdfName.ALT, new PdfString(eexpPage.getSubject()));

			canvas = stamper.getOverContent(i);

			Phrase phrase = new Phrase("Folio N° " + page, bold10Blue);

			ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 13, 0);

			document.add(image);

			document.newPage();

		}

		// stamper.setOutlines(outlines);

		stamper.close();

	}

	private void buildHeaderFooter(EexpPage eexpPage) throws BadElementException, MalformedURLException, IOException, ParseException {

		float[] columnWidths = { 8 };
		PdfPTable pdfPTableFooter = new PdfPTable(columnWidths);

		pdfPTableFooter.setTotalWidth(pdfAWriter.getPageSize().getWidth() - pdfAWriter.getPageSize().getBorderWidthLeft() - pdfAWriter.getPageSize().getBorderWidthRight() - 70);

		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(new Chunk("Folio añadido por ", normal7Blue));
		paragraph.add(new Chunk(eexpPage.getAdminUnit() + " - " + eexpPage.getUser(), normal7Blue));
		paragraph.add(new Chunk(" el ", normal7Blue));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		String stringDate = eexpPage.getCreated();
		Date date = simpleDateFormat.parse(stringDate);
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssX");
		paragraph.add(new Chunk(simpleDateFormat.format(date), normal7Blue));

		PdfPCell pdfPCell = new PdfPCell(paragraph);
		// pdfPCell.setColspan(2);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_TOP);
		pdfPCell.setBorder(0);
		pdfPCell.setPaddingTop(0);
		// pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		// pdfPCell.setBorder(Rectangle.TOP);

		pdfPTableFooter.addCell(pdfPCell);

		// ============================================================================

		PdfPTable pdfPTableHeader = new PdfPTable(new float[] { 3f, 2f });
		pdfPTableHeader.setTotalWidth(pdfAWriter.getPageSize().getWidth() / 2 - pdfAWriter.getPageSize().getBorderWidthLeft() - pdfAWriter.getPageSize().getBorderWidthRight() - 40);

		if (eexpPage.getAdminUnit().getPathImg() != null) {

			pdfPCell = new PdfPCell(image2, false);
			pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			pdfPCell.setPaddingBottom(3);
			pdfPCell.setPaddingLeft(1);
			pdfPCell.setPaddingRight(0);
			// pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
			pdfPCell.setBorder(0);

			pdfPTableHeader.addCell(pdfPCell);
		}

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);

		// paragraph.add(new Chunk("Folio N° ______________ ", bold7Blue));
		paragraph.add(new Chunk("\n\n" + eexpPage.getCudap() + " ", bold7Blue));

		pdfPCell = new PdfPCell(paragraph);
		if (eexpPage.getAdminUnit().getPathImg() == null) {
			pdfPCell.setColspan(2);
		}
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pdfPCell.setPaddingBottom(3);
		pdfPCell.setPaddingLeft(0);
		pdfPCell.setPaddingRight(0);
		// pdfPCell.setBorderColor(BaseColor.BLUE);
		pdfPCell.setBorder(0);
		pdfPTableHeader.addCell(pdfPCell);

		// ============================================================================

		HeaderFooterTableEvent footerTableEvent = new HeaderFooterTableEvent(pdfPTableHeader, pdfPTableFooter);
		pdfAWriter.setPageEvent(footerTableEvent);
	}

	public class HeaderFooterTableEvent extends PdfPageEventHelper {

		protected PdfPTable pdfPTableHeader;
		protected PdfPTable pdfPTableFooter;

		public HeaderFooterTableEvent(PdfPTable pdfPTableHeader, PdfPTable pdfPTableFooter) {
			super();
			this.pdfPTableHeader = pdfPTableHeader;
			this.pdfPTableFooter = pdfPTableFooter;
		}

		public void onEndPage(PdfWriter writer, Document document) {
			pdfPTableHeader.writeSelectedRows(0, -1, (document.getPageSize().getWidth() / 2), document.top() + ((document.topMargin() + pdfPTableHeader.getTotalHeight()) / 2), canvas);

			pdfPTableFooter.writeSelectedRows(0, -1, 36, 50, canvas);
		}
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public void buildFinalEE(EexpPage eexpPage) throws IOException, DocumentException {

		String f1 = eexpPage.getPathSourceEe();
		String f2 = eexpPage.getPathResultPdf();

		// -------------------------------------------------------------------

		eePathResult = GlobalProperties.get(GlobalProperties.PATH_RESULT) + File.separatorChar + eexpPage.getCudapFile() + Calendar.getInstance().getTimeInMillis() + ".pdf";

		Document document = new Document(PageSize.A4);

		// PDF/A-3a
		// Create PdfAWriter with the required conformance level
		pdfACopy = new PdfACopy(document, new FileOutputStream(eePathResult), PdfAConformanceLevel.PDF_A_3A);
		pdfACopy.setPdfVersion(PdfWriter.VERSION_1_7);

		// ====================
		// TAGGED PDF
		// Make document tagged
		pdfACopy.setTagged();

		pdfACopy.setMergeFields();

		// ===============
		// PDF/UA
		// Set document metadata
		pdfACopy.setViewerPreferences(PdfWriter.DisplayDocTitle);

		document.addLanguage("es-AR");
		document.addCreationDate();

		document.addTitle(eexpPage.getTitle());
		document.addSubject(eexpPage.getSubject());
		document.addAuthor(eexpPage.getAuthor());
		document.addCreator(eexpPage.getCreator());
		document.addKeywords(eexpPage.getKeywords());

		PdfDictionary info = pdfACopy.getInfo();
		PdfDate pdfDate = (PdfDate) info.get(PdfName.CREATIONDATE);
		eexpPage.setCreated(pdfDate.getW3CDate());

		pdfACopy.createXmpMetadata();

		document.open();

		// START :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

		int page = 1;

		// Create a list for the bookmarks
		ArrayList<HashMap<String, Object>> outlines = new ArrayList<HashMap<String, Object>>(); // Indice completo

		ArrayList<HashMap<String, Object>> kids = new ArrayList<HashMap<String, Object>>();

		// =====================================================================================

		// add the first document
		PdfReader reader1 = new PdfReader(f1);
		pdfACopy.addDocument(reader1);

		// --------------------------------------------

		copyAttachment(eexpPage, reader1);

		// --------------------------------------------

		List<HashMap<String, Object>> bookmarkOld = SimpleBookmark.getBookmark(reader1);

		HashMap<String, Object> itemMapRootNew = new HashMap<String, Object>();

		for (int i = 0; i < bookmarkOld.size(); i++) {

			HashMap<String, Object> itemMapOld = bookmarkOld.get(i);

			itemMapRootNew.put("Title", itemMapOld.get("Title"));
			itemMapRootNew.put("Action", "GoTo");
//			itemMapRootNew.put("Page", itemMapOld.get("Page"));
			itemMapRootNew.put("Page", String.format("%d Fit", 1));
			
			outlines.add(itemMapRootNew);

			if (itemMapOld.containsKey("Kids")) {

				List<HashMap<String, Object>> bookmarkKidsOld = (List<HashMap<String, Object>>) itemMapOld.get("Kids");

				for (int j = 0; j < bookmarkKidsOld.size(); j++) {
					HashMap<String, Object> itemMapNew = new HashMap<String, Object>();
					HashMap<String, Object> itemMapKidsOld = bookmarkKidsOld.get(j);

					itemMapNew.put("Title", itemMapKidsOld.get("Title"));
					itemMapNew.put("Action", "GoTo");					
					itemMapNew.put("Page", itemMapKidsOld.get("Page"));
					kids.add(itemMapNew);
				}

			}

		}

		// =====================================================================================

		PdfReader reader2 = new PdfReader(f2);
		pdfACopy.addDocument(reader2);

		// update page count
		page += reader1.getNumberOfPages();

		HashMap<String, Object> itemMapNew = new HashMap<String, Object>();

		itemMapNew.put("Title", "Folio N° " + eexpPage.getStartPage() + " - " + cudapEd);
		itemMapNew.put("Action", "GoTo");
		itemMapNew.put("Page", String.format("%d Fit", page));		

		kids.add(itemMapNew);

		itemMapRootNew.put("Kids", kids);

		// =====================================================================================

		// Add the merged bookmarks
		pdfACopy.setOutlines(outlines);

		// --------------------------------------------

		copyAttachment(eexpPage, reader2);

		// --------------------------------------------

		ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
		pdfACopy.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);

		pdfACopy.close();
	}
	// ///////////////////////////////////////////////////////////////////////////////////////////////

}
