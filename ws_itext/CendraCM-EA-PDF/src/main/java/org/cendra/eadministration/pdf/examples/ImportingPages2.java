/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package org.cendra.eadministration.pdf.examples;

import java.io.FileOutputStream;
import java.io.IOException;

import org.cendra.eadministration.pdf.util.SO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class ImportingPages2 {

	/** The resulting PDF file. */
	public static final String SOURCE = "results/pdfa/EEXP_UNC_666_2016.pdf";
	public static final String RESULT = "results/pdfa/EEXP_UNC_777_2016.pdf";

	/**
	 * Main method.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, DocumentException {

		// leo un doc existente
		PdfReader reader = new PdfReader(SOURCE);
		int n = reader.getNumberOfPages();

		// crero un doc nuevo
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		document.open();

		// ------------------------------------------------------

		PdfImportedPage page;

		PdfPTable table = new PdfPTable(1);

		for (int i = 1; i <= n; i++) {

			page = writer.getImportedPage(reader, i);
			table.getDefaultCell().setRotation(-page.getRotation());
			table.addCell(Image.getInstance(page));

		}

		document.add(table);

		// ------------------------------------------------------

		document.close();
		reader.close();
	}
}
