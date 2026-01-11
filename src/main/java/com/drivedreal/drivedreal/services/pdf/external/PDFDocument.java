package com.drivedreal.drivedreal.services.pdf.external;

import java.util.List;

public class PDFDocument {

    private byte[] pdfData;
    private String metadata;
    private List<String> annotations;

    public PDFDocument(byte[] pdfData, String metadata, List<String> annotations) {
        this.pdfData = pdfData;
        this.metadata = metadata;
        this.annotations = annotations;
    }

    public void renderPDF() {
        System.out.println("Rendering PDF document using external library...");
    }

    public void printPDF() {
        System.out.println("Printing PDF document...");
    }

    public void savePDF(String filename) {
        System.out.println("Saving PDF document as: " + filename);
    }
}

