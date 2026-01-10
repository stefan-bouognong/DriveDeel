package com.drivedreal.drivedreal.services.pdf.external;

import java.util.List;

public class PDFDocument {

    private byte[] pdfData;
    private String metadata;
    private List<String> pages;

    public PDFDocument(byte[] pdfData, String metadata, List<String> pages) {
        this.pdfData = pdfData;
        this.metadata = metadata;
        this.pages = pages;
    }

    public void renderPDF() {
        System.out.println("Rendering PDF document using external library...");
    }

    public void savePDF(String filename) {
        System.out.println("Saving PDF document as: " + filename);
    }

    public void loadPDF(String filename) {
        System.out.println("Loading PDF document from: " + filename);
    }

    public void addPage(String page) {
        pages.add(page);
    }
}
