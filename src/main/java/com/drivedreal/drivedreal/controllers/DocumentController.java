package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.services.document.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/test-adapter")
    public String testAdapter() {
        documentService.testAdapter();
        return "Adapter pattern tested successfully. Check console logs.";
    }
}
