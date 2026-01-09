package com.drivedreal.drivedreal.domain.document;

import java.util.ArrayList;
import java.util.List;

public class BlankDocumentBundle {

    private static BlankDocumentBundle instance;
    private List<Document> documents;

    private BlankDocumentBundle() {
        documents = new ArrayList<>();
        documents.add(new Document("Registration Request", "PDF"));
        documents.add(new Document("Transfer Certificate", "PDF"));
        documents.add(new Document("Order Form", "HTML"));
    }

    public static synchronized BlankDocumentBundle getInstance() {
        if (instance == null) {
            instance = new BlankDocumentBundle();
        }
        return instance;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
