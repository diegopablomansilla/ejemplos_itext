package org.cendra.eadministration.pdf.examples;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

// Este ejemplo puede servir para el caso de template, para nota, expediente, etc.

public class ImportingPages1 {

	public static final String SOURCE = "results/pdfa/EEXP_UNC_666_2016";
	public static final String RESULT = "results/pdfa/EEXP_UNC_888_2016.pdf";

	public static void main(String[] args) throws IOException, DocumentException {

		// leo un doc existente
		PdfReader reader = new PdfReader(SOURCE);
		int n = reader.getNumberOfPages();

		// crero un doc nuevo
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		document.open();

		// ------------------------------------------------------

		PdfPTable table = new PdfPTable(1);
		PdfImportedPage page;

		for (int i = 1; i <= n; i++) {

			page = writer.getImportedPage(reader, i);
			table.addCell(Image.getInstance(page));

		}

		document.add(table);

		// ------------------------------------------------------

		document.close();
		reader.close();
	}
}
