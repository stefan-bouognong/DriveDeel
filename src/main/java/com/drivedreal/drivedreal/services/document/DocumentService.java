package com.drivedreal.drivedreal.services.document;

import com.drivedreal.drivedreal.domain.document.Document;
import com.drivedreal.drivedreal.domain.document.HTMLDocument;
import com.drivedreal.drivedreal.services.pdf.adapter.PDFDocumentAdapter;
import com.drivedreal.drivedreal.services.pdf.external.PDFDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DocumentService {

    public void testAdapter() {

        // HTML document (sans adaptateur)
        Document htmlDoc = new HTMLDocument(
                "DOC_HTML_01",
                "Bon de commande",
                "<h1>Bon de commande</h1>"
        );

        htmlDoc.display();
        htmlDoc.print();
        htmlDoc.save("bon_commande.html");

        System.out.println("------------------------------------------------");

        // PDF document (avec adaptateur)
        PDFDocument pdf = new PDFDocument(
                new byte[]{},
                "PDF Metadata",
                new ArrayList<>()
        );

        Document pdfDoc = new PDFDocumentAdapter(
                "DOC_PDF_01",
                "Bon de commande",
                pdf
        );

        pdfDoc.display();
        pdfDoc.print();
        pdfDoc.save("bon_commande.pdf");
    }
}
