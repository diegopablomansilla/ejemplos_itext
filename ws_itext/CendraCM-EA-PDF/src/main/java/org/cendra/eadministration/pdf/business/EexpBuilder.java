package org.cendra.eadministration.pdf.business;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.cendra.eadministration.pdf.model.md.Eexp;
import org.cendra.eadministration.pdf.model.md.PdfA3a;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

public class EexpBuilder extends PdfA3aBuilder {

	/** An image resource. */
	public static final String UNC1_B = "resources/images/logo_institucion.png";

	public String build(PdfA3a pdfA3a) throws Exception {

		if (pdfA3a instanceof Eexp == false) {
			throw new IllegalArgumentException("Se esperaba una modelo de para Expediente (EE), es decir un argumento de clase " + Eexp.class + " y no una clase " + pdfA3a.getClass());
		}
		Eexp ee = (Eexp) pdfA3a;

		ee.setFileName(ee.getCudap().replace("/", "_").replace("-", "_").replace(":", "_"));

		Document document = initPdf(pdfA3a);
		
		ee.geteExp().setCreateDate(ee.getCreated());

		ee.setFileNameJson(ee.getFileName() + ".json");

		ee.setPathResultJson(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_JSON) + ee.getFileNameJson());

		SO.createFile(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_JSON), ee.getFileNameJson(), ee.geteExp().toString());

		addAttachment(ee, ee.getFileNameJson(), "Metadatos", "application/json");

		buildBody(document, ee);

		// //////////////////////////////////////////////////////////

		PdfPTable pdfPTable = new PdfPTable(1);
		pdfPTable.setTotalWidth(200);
		pdfPTable.addCell(buildBarcode(pdfAWriter, ee.getCudap()));

		FooterTableEvent footerTableEvent = new FooterTableEvent(pdfPTable);
		pdfAWriter.setPageEvent(footerTableEvent);

		// //////////////////////////////////////////////////////////

		document.close();
		
		SO.delete(ee.getPathResultJson());
		
		return ee.getPathResultPdf();

	}

	private void buildBody(Document document, Eexp ee) throws MalformedURLException, IOException, DocumentException, ParseException {

		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_14);
//		paragraph.add("Carátula " + ee.getCudap());
		paragraph.add(ee.getCudap());

		Chapter chapter = new Chapter(paragraph, 0);
		chapter.setNumberDepth(0);		
		document.add(chapter);

		document.add(buildTitle(document, ee));

		document.add(buildMetaData(ee));

	}

	public static PdfPTable buildTitle(Document document, Eexp ee) throws DocumentException, BadElementException, MalformedURLException, IOException {
		float[] columnWidths = { 2, 5 };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);

		PdfPCell cell = null;

		// //////////////////////////////////////////////////////////////////////////////////

		Image image = Image.getInstance(UNC1_B);
		// PDF/UA
		// Set alt text
		image.setAccessibleAttribute(PdfName.ALT, new PdfString("Escudo Universidad Nacional de Córdoba"));
		cell = new PdfPCell(image, true);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setPaddingTop(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);

		Chunk chunk = new Chunk("Universidad Nacional de Córdoba", BOLD_14);
		paragraph.add(chunk);

		chunk = new Chunk(" - República Argentina", NORMAL_11);
		paragraph.add(chunk);

		chunk = new Chunk("\n\n\nExpediente Electrónico", NORMAL_11);

		paragraph.add(chunk);

		chunk = new Chunk("\n" + ee.getCudap(), BOLD_24);
		paragraph.add(chunk);

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		cell.setPaddingTop(0);
		cell.setBorder(Rectangle.BOTTOM);

		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		return table;
	}

	public static PdfPTable buildMetaData(Eexp ee) throws DocumentException, BadElementException, MalformedURLException, IOException, ParseException {
		float[] columnWidths = { 3, 10 };
		PdfPTable table = new PdfPTable(columnWidths);
		// PdfPTable table = new PdfPTable(2);
		// table.setWidthPercentage(100f);
		table.setWidthPercentage(100);

		PdfPCell cell = null;

		// //////////////////////////////////////////////////////////////////////////////////

		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_14);
		paragraph.add("\n\nDatos de registración");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setPaddingTop(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_11);
		paragraph.add("\nFeha y hora");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(NORMAL_11);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		String stringDate = ee.getCreated();
		Date date = simpleDateFormat.parse(stringDate);
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssX");

		paragraph.add("\n" + simpleDateFormat.format(date));

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_11);
		paragraph.add("Unidad administrativa");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(NORMAL_11);

		String v = "";
		if (ee.getAdminUnit().toString().length() > 100) {
			v = ee.getAdminUnit().toString().substring(0, 100);
		} else {
			v = ee.getAdminUnit().toString();
		}

		paragraph.add(v);

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_14);
		paragraph.add("\nCausante");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		cell.setPaddingTop(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(NORMAL_14);

		v = "";
		if (ee.getCausative().length() > 100) {
			v = ee.getCausative().substring(0, 100);
		} else {
			v = ee.getCausative();
		}

		paragraph.add("\n" + v);

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		cell.setPaddingTop(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(BOLD_14);
		paragraph.add("\nAsunto");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPaddingTop(0);
		cell.setPaddingBottom(5);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(NORMAL_14);

		v = "";
		if (ee.getSubject().length() > 500) {
			v = ee.getSubject().substring(0, 500);
		} else {
			v = ee.getSubject();
		}

		paragraph.add("\n        " + v + "\n");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setPaddingTop(0);
		cell.setPaddingBottom(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(NORMAL_14);
		paragraph.add("\n");

		cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPaddingTop(0);
		cell.setPaddingBottom(0);
		table.addCell(cell);

		// //////////////////////////////////////////////////////////////////////////////////

		String[][] properties = ee.getProperties();
		if (properties != null) {

			// //////////////////////////////////////////////////////////////////////////////////

			paragraph = new Paragraph();
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setFont(BOLD_14);
			paragraph.add("\nDatos de complementarios");

			cell = new PdfPCell(paragraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setPaddingTop(0);
			table.addCell(cell);

			// //////////////////////////////////////////////////////////////////////////////////

			int length = 5;

			if (properties.length < length) {
				length = properties.length;
			}

			for (int i = 0; i < length; i++) {
				// //////////////////////////////////////////////////////////////////////////////////

				String t = "";
				if (i == 0) {
					t = "\n";
				} else {
					t = "";
				}

				paragraph = new Paragraph();
				paragraph.setAlignment(Element.ALIGN_LEFT);
				paragraph.setFont(BOLD_11);

				String k = "";
				if (properties[i][0].length() > 30) {
					k = properties[i][0].substring(0, 30);
				} else {
					k = properties[i][0];
				}

				paragraph.add(t + k);

				cell = new PdfPCell(paragraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				cell.setPaddingTop(0);
				table.addCell(cell);

				// //////////////////////////////////////////////////////////////////////////////////

				paragraph = new Paragraph();
				paragraph.setAlignment(Element.ALIGN_LEFT);
				paragraph.setFont(NORMAL_11);

				v = "";
				if (properties[i][1].length() > 200) {
					v = properties[i][1].substring(0, 200);
				} else {
					v = properties[i][1];
				}

				paragraph.add(t + v);

				cell = new PdfPCell(paragraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				cell.setPaddingTop(0);
				table.addCell(cell);

				// //////////////////////////////////////////////////////////////////////////////////
			}

		}

		// //////////////////////////////////////////////////////////////////////////////////
		return table;
	}

	public static PdfPCell buildBarcode(PdfWriter writer, String code) throws DocumentException, IOException {

		PdfContentByte cb = writer.getDirectContent();

		Barcode128 barcode = new Barcode128();
		barcode.setCode(code.trim());
		barcode.setCodeType(Barcode128.CODE128);
		barcode.setFont(NORMAL_8.getBaseFont());
		barcode.setBarHeight(20f);

		Image code128Image = barcode.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.GRAY);
		code128Image.setScaleToFitHeight(true);

		code128Image.setAccessibleAttribute(PdfName.ALT, new PdfString("CUDAP"));
		PdfPCell cell = new PdfPCell(code128Image, true);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		return cell;
	}

	public class FooterTableEvent extends PdfPageEventHelper {

		protected PdfPTable footer;

		public FooterTableEvent(PdfPTable footer) {
			this.footer = footer;
		}

		public void onEndPage(PdfWriter writer, Document document) {
			footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
		}
	}

}
