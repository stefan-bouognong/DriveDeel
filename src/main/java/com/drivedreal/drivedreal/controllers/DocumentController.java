package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.domain.document.Document;
import com.drivedreal.drivedreal.domain.document.HTMLDocument;
import com.drivedreal.drivedreal.services.client.DocumentClient;
import com.drivedreal.drivedreal.services.pdf.adapter.PDFDocumentAdapter;
import com.drivedreal.drivedreal.services.pdf.external.PDFDocument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @GetMapping("/html")
    public String testHTMLDocument() {

        Document document = new HTMLDocument("<h1>Contrat de location</h1>");
        DocumentClient client = new DocumentClient();

        client.processDocument(document);

        return "HTML Document processed (check console)";
    }

    @GetMapping("/pdf")
    public String testPDFDocument() {

        PDFDocument pdfDocument = new PDFDocument(
                new byte[]{},
                "PDF metadata",
                List.of()
        );

        Document document = new PDFDocumentAdapter(pdfDocument);
        DocumentClient client = new DocumentClient();

        client.processDocument(document);

        return "PDF Document processed via Adapter (check console)";
    }
}

