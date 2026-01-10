package com.drivedreal.drivedreal.services.pdf.adapter;

import com.drivedreal.drivedreal.domain.document.Document;
import com.drivedreal.drivedreal.services.pdf.external.PDFDocument;

public class PDFDocumentAdapter extends Document {

    private PDFDocument pdfDocument;

    public PDFDocumentAdapter(String id_document, String document_type, PDFDocument pdfDocument) {
        super(id_document, document_type, "PDF");
        this.pdfDocument = pdfDocument;
    }

    @Override
    public void display() {
        pdfDocument.renderPDF();
    }

    @Override
    public void print() {
        pdfDocument.renderPDF();
        System.out.println("Printing PDF document...");
    }

    @Override
    public void save(String path) {
        pdfDocument.savePDF(path);
    }
}

