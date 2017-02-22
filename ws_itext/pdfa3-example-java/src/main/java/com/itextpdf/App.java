package com.itextpdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.xml.InvoiceType;
import com.itextpdf.xml.TicketType;

public class App {
	public static void main(String[] args) throws DocumentException, IOException, JAXBException {

		// Load invoice.xml here
		InvoiceType invoice = load("./src/main/resources/com/itextpdf/invoice.xml", InvoiceType.class);

		// Start building our document
		Document document = new Document();

		// Create PdfAWriter with PdfAConformanceLevel.PDF_A_3B option if you
		// want to get a PDF/A-3b compliant document.
		PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream("./target/pdfa3-example.pdf"), PdfAConformanceLevel.PDF_A_3B);
		
		// Create XMP metadata. It's a PDF/A requirement.
		writer.createXmpMetadata();

		document.open();

		// Set output intent. PDF/A requirement.
		ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream("./src/main/resources/com/itextpdf/sRGB Color Space Profile.icm"));
		writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);

		// All fonts shall be embedded. PDF/A requirement.
		Font bold10 = FontFactory.getFont("./src/main/resources/com/itextpdf/FreeSansBold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, 10);
		Font normal9 = FontFactory.getFont("./src/main/resources/com/itextpdf/FreeSans.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, 9);
		Font bold9 = FontFactory.getFont("./src/main/resources/com/itextpdf/FreeSansBold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, 9);
		Font normal8 = FontFactory.getFont("./src/main/resources/com/itextpdf/FreeSans.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, 8);

		// Creating PDF/A-3 compliant attachment.
		PdfDictionary parameters = new PdfDictionary();
		parameters.put(PdfName.MODDATE, new PdfDate());
		
		PdfFileSpecification fileSpec = PdfFileSpecification.fileEmbedded(writer, "./src/main/resources/com/itextpdf/invoice.xml", "invoice.xml", null, "application/xml", parameters, 0);
		fileSpec.put(new PdfName("AFRelationship"), new PdfName("Data"));
		
		writer.addFileAttachment("invoice.xml", fileSpec);
		
		PdfArray array = new PdfArray();
		array.add(fileSpec.getReference());
		writer.getExtraCatalog().put(new PdfName("AF"), array);

		// From here on we can add content to the PDF just like we would do for
		// a regular PDF.

		// Building header.
		document.add(new Paragraph("Invoice number: " + invoice.getNumber(), bold10));
		document.add(new Paragraph("\n", normal8));
		document.add(new Paragraph("Dear " + invoice.getName() + ",", normal9));
		document.add(new Paragraph("Thank you for your order with amiando. The order for \"Technical Conference Europe 2013\" from June 18, 2013 until June 19, 2013 is calculated as follows:", normal9));
		document.add(new Paragraph("\n", normal8));

		// Building "Ticket category" table.
		if (invoice.getTickets() != null) {
			PdfPTable table = new PdfPTable(new float[] { 40, 15, 15, 15, 15 });
			table.addCell(new Paragraph("Ticket category", bold9));
			table.addCell(new Paragraph("Amount", bold9));
			table.addCell(new Paragraph("Price (net)", bold9));
			table.addCell(new Paragraph("VAT", bold9));
			table.addCell(new Paragraph("Price (gross)", bold9));
			if (invoice.getTickets().getTicket() != null) {
				java.util.List<TicketType> tickets = invoice.getTickets().getTicket();
				for (TicketType ticket : tickets) {
					table.addCell(new Paragraph(ticket.getCategory(), normal9));
					table.addCell(new Paragraph(ticket.getAmount(), normal9));
					table.addCell(new Paragraph(ticket.getPriceNet(), normal9));
					table.addCell(new Paragraph(ticket.getVat(), normal9));
					table.addCell(new Paragraph(ticket.getPriceGross(), normal9));
				}
			}
			table.addCell(new Paragraph("Subtotal tickets", bold9));
			table.addCell(new Paragraph(" ", bold9));
			table.addCell(new Paragraph(" ", bold9));
			table.addCell(new Paragraph(" ", bold9));
			table.addCell(new Paragraph(invoice.getTickets().getSubtotal(), bold9));
			document.add(table);
			document.add(new Paragraph("\n", normal8));
		}

		// Building "Total" table
		if (invoice.getTotal() != null) {
			PdfPTable table = new PdfPTable(new float[] { 70, 15, 15 });
			table.addCell(new Paragraph("Total", bold9));
			table.addCell(new Paragraph("VAT", bold9));
			table.addCell(new Paragraph("Price (gross)", bold9));
			if (invoice.getTotal().getSubtotal() != null) {
				table.addCell(new Paragraph("Subtotal tickets", normal9));
				table.addCell(new Paragraph(" ", normal9));
				table.addCell(new Paragraph(invoice.getTotal().getSubtotal().getPriceGross(), normal9));
				if (invoice.getTotal().getSubtotal().getInvoiceAmountNet() != null) {
					table.addCell(new Paragraph("     Invoice amount (net)", normal8));
					table.addCell(new Paragraph(invoice.getTotal().getSubtotal().getInvoiceAmountNet().getVat(), normal8));
					table.addCell(new Paragraph(invoice.getTotal().getSubtotal().getInvoiceAmountNet().getPriceGross(), normal8));
				}
				if (invoice.getTotal().getSubtotal().getIncludedVat() != null) {
					table.addCell(new Paragraph("     Included VAT", normal8));
					table.addCell(new Paragraph(invoice.getTotal().getSubtotal().getIncludedVat().getVat(), normal8));
					table.addCell(new Paragraph(invoice.getTotal().getSubtotal().getIncludedVat().getPriceGross(), normal8));
				}
				table.addCell(new Paragraph("Invoice amount", bold10));
				table.addCell(new Paragraph(" ", bold10));
				table.addCell(new Paragraph(invoice.getTotal().getInvoiceAmount(), bold10));
			}
			document.add(table);
		}

		document.close();
	}

	static <T> T load(String fileName, Class<T> tClass) throws IOException, JAXBException {
		return load(new File(fileName), tClass);
	}

	static <T> T load(File file, Class<T> tClass) throws IOException, JAXBException {
		FileInputStream fis = new FileInputStream(file);
		T t = load(fis, tClass);
		fis.close();
		return t;
	}

	static <T> T load(InputStream inputStream, Class<T> tClass) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(tClass);
		Unmarshaller m = context.createUnmarshaller();
		return (T) m.unmarshal(inputStream);
	}

}
