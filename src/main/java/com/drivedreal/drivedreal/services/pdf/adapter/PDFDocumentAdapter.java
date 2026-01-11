package com.drivedreal.drivedreal.services.pdf.adapter;

import com.drivedreal.drivedreal.domain.document.Document;
import com.drivedreal.drivedreal.services.pdf.external.PDFDocument;

public class PDFDocumentAdapter implements Document {

    private PDFDocument pdfDocument;

    public PDFDocumentAdapter(PDFDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    @Override
    public void display() {
        pdfDocument.renderPDF();
    }

    @Override
    public void print() {
        pdfDocument.printPDF();
    }

    @Override
    public void save(String filename) {
        pdfDocument.savePDF(filename);
    }
}


