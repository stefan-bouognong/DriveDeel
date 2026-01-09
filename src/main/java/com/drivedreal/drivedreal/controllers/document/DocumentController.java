package com.drivedreal.drivedreal.controllers.document;

import org.springframework.web.bind.annotation.*;

import com.drivedreal.drivedreal.domain.document.Document;
import com.drivedreal.drivedreal.services.document.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/blank-bundle")
    public List<Document> getBlankDocumentBundle() {
        return documentService.getBlankDocumentBundle();
    }
}
