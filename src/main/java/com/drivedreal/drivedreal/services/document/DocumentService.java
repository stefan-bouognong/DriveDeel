package com.drivedreal.drivedreal.services.document;

import org.springframework.stereotype.Service;

import com.drivedreal.drivedreal.domain.document.BlankDocumentBundle;
import com.drivedreal.drivedreal.domain.document.Document;

import java.util.List;

@Service
public class DocumentService {

    public List<Document> getBlankDocumentBundle() {
        return BlankDocumentBundle.getInstance().getDocuments();
    }
}

