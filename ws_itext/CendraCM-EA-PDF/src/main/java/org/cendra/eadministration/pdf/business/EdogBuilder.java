package org.cendra.eadministration.pdf.business;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.cendra.eadministration.pdf.model.md.Edog;
import org.cendra.eadministration.pdf.model.md.Eexp;
import org.cendra.eadministration.pdf.model.md.PdfA3a;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

public class EdogBuilder extends PdfA3aBuilder {

	private Image image1;
	private Image image2;
	private int page = 1;
	private int pageCount;

	public String build(PdfA3a pdfA3a) throws Exception {

		if (pdfA3a instanceof Edog == false) {
			throw new IllegalArgumentException("Se esperaba una modelo de para Expediente (EE), es decir un argumento de clase " + Eexp.class + " y no una clase " + pdfA3a.getClass());
		}
		Edog edog = (Edog) pdfA3a;

		edog.setFileName(edog.getCudap().replace("/", "_").replace("-", "_").replace(":", "_"));

		Document document = initPdf(pdfA3a);		
		
		edog.geteDog().setCreateDate(edog.getCreated());

		edog.setFileNameJson(edog.getFileName() + ".json");

		edog.setPathResultJson(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_JSON) + edog.getFileNameJson());

		SO.createFile(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_JSON), edog.getFileNameJson(), edog.geteDog().toString());

		addAttachment(edog, edog.getFileNameJson(), "Metadatos " + edog.getCudap(), "application/json");

		buildBody(document, edog);

		document.close();

		SO.delete(edog.getPathResultJson());

		return edog.getPathResultPdf();

	}

	private void buildBody(Document document, Edog edog) throws MalformedURLException, IOException, DocumentException, ParseException {

		if (edog.getPathImg() != null) {

			image1 = Image.getInstance(edog.getPathImg());
			// PDF/UA
			// Set alt text
			image1.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo Universidad Nacional de Córdoba"));
			image1.scaleToFit(80, 80);

		}

		if (edog.getAdminUnit().getPathImg() != null) {

			image2 = Image.getInstance(edog.getAdminUnit().getPathImg());
			// PDF/UA
			// Set alt text
			image2.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo Universidad Nacional de Córdoba"));
			image2.scaleToFit(70, 70);

		}

		// leo un doc existente
		PdfReader pdfReader = new PdfReader(edog.getPathSource());
		pageCount = pdfReader.getNumberOfPages();

		// ------------------------------------------------------

		PdfPTable pdfPTable = new PdfPTable(1);
		pdfPTable.setWidthPercentage(100);

		PdfImportedPage pdfImportedPage;

		buildHeaderFooter(edog);

		for (int i = 1; i <= pageCount; i++) {

			page = i;

			pdfImportedPage = pdfAWriter.getImportedPage(pdfReader, i);
			Image image = Image.getInstance(pdfImportedPage);
			image.setAccessibleAttribute(PdfName.ALT, new PdfString(edog.getSubject()));

			PdfPCell pdfPCell = new PdfPCell(image, true);
			pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pdfPCell.setPaddingTop(0);
			pdfPCell.setPaddingBottom(0);
			pdfPCell.setPaddingLeft(0);
			pdfPCell.setPaddingRight(0);
			pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);

			pdfPTable.addCell(pdfPCell);

			document.newPage();

		}

		document.add(pdfPTable);

	}

	private void buildHeaderFooter(Edog edog) throws BadElementException, MalformedURLException, IOException, ParseException {

		float[] columnWidths = { 7, 1 };
		PdfPTable pdfPTableFooter = new PdfPTable(columnWidths);

		pdfPTableFooter.setTotalWidth(pdfAWriter.getPageSize().getWidth() - pdfAWriter.getPageSize().getBorderWidthLeft() - pdfAWriter.getPageSize().getBorderWidthRight() - 70);

		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(new Chunk("Creado por ", bold7Gray));
		paragraph.add(new Chunk(edog.getAdminUnit() + " - " + edog.getUser(), normal7Gray));
		paragraph.add(new Chunk(" el ", bold7Gray));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		String stringDate = edog.getCreated();
		Date date = simpleDateFormat.parse(stringDate);
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssX");
		paragraph.add(new Chunk(simpleDateFormat.format(date), normal7Gray));

		PdfPCell pdfPCell = new PdfPCell(paragraph);
		// pdfPCell.setColspan(2);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_TOP);
		pdfPCell.setBorder(0);
		pdfPCell.setPaddingTop(0);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorder(Rectangle.TOP);

		pdfPTableFooter.addCell(pdfPCell);

		// ----------------------------------------------------------

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(new Chunk("", normal7Gray));

		pdfPCell = new PdfPCell(paragraph);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_TOP);
		pdfPCell.setBorder(0);
		pdfPCell.setPaddingTop(0);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorder(Rectangle.TOP);

		pdfPTableFooter.addCell(pdfPCell);

		// ============================================================================

		// PdfPTable pdfPTableHeader = new PdfPTable(4);

		PdfPTable pdfPTableHeader = new PdfPTable(new float[] { 1f, 1f, 2f, 2 });
		pdfPTableHeader.setTotalWidth(pdfAWriter.getPageSize().getWidth() - pdfAWriter.getPageSize().getBorderWidthLeft() - pdfAWriter.getPageSize().getBorderWidthRight() - 70);

		if (edog.getPathImg() != null) {

			pdfPCell = new PdfPCell(image1, false);
			pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			pdfPCell.setPaddingBottom(3);
			pdfPCell.setPaddingLeft(0);
			pdfPCell.setPaddingRight(1);
			pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
			pdfPCell.setBorder(Rectangle.BOTTOM);

			pdfPTableHeader.addCell(pdfPCell);
		}

		if (edog.getAdminUnit().getPathImg() != null) {

			pdfPCell = new PdfPCell(image2, false);
			if (edog.getPathImg() == null) {
				pdfPCell.setColspan(2);
			}
			pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			pdfPCell.setPaddingBottom(3);
			pdfPCell.setPaddingLeft(1);
			pdfPCell.setPaddingRight(0);
			pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
			pdfPCell.setBorder(Rectangle.BOTTOM);

			pdfPTableHeader.addCell(pdfPCell);
		}

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.add(new Chunk("\nDocumento General", bold7Gray));
		paragraph.add(new Chunk("\n" + edog.getSubject(), normal7Gray));
		paragraph.add(new Chunk("\n\n" + edog.getCudap(), bold7Gray));

		pdfPCell = new PdfPCell(paragraph);
		if (edog.getPathImg() != null && edog.getAdminUnit().getPathImg() != null) {

		} else if (edog.getPathImg() == null && edog.getAdminUnit().getPathImg() == null) {
			pdfPCell.setColspan(3);
		} else if (edog.getPathImg() == null || edog.getAdminUnit().getPathImg() == null) {
			pdfPCell.setColspan(2);
		}
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pdfPCell.setPaddingBottom(3);
		pdfPCell.setPaddingLeft(0);
		pdfPCell.setPaddingRight(0);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorder(Rectangle.BOTTOM);
		pdfPTableHeader.addCell(pdfPCell);

		paragraph = new Paragraph("", bold7Gray);
		paragraph.setAlignment(Element.ALIGN_LEFT);

		pdfPCell = new PdfPCell(paragraph);
		pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pdfPCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		pdfPCell.setPaddingBottom(3);
		pdfPCell.setBorderColor(BaseColor.LIGHT_GRAY);
		pdfPCell.setBorder(Rectangle.BOTTOM);
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
			pdfPTableHeader.writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + pdfPTableHeader.getTotalHeight()) / 2), writer.getDirectContent());

			pdfPTableFooter.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());

			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página " + document.getPageNumber() + " de " + pageCount, normal7Gray), document.getPageSize().getWidth() - 35, 57, 0);
		}
	}

}
