package com.drivedreal.drivedreal.services.client;

import com.drivedreal.drivedreal.domain.document.Document;

public class DocumentClient {

        public void processDocument(Document document) {
                document.display();
                document.print();
                document.save("document_output.pdf");
        }
}
