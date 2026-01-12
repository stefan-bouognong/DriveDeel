package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.entity.Catalogue;
import com.drivedreal.drivedreal.service.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api/catalogues")
@RequiredArgsConstructor
public class CatalogueController {

    private final CatalogueService catalogueService;

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"CatalogueController.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

    @PostMapping
    public ResponseEntity<?> createCatalogue(@RequestParam String name) {
        try {
            Catalogue newCatalogue = catalogueService.createCatalogue(name);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCatalogue);
        } catch (Exception e) {
            logDebug("Error creating catalogue: " + e.getMessage(), e, "G");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création du catalogue.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalogue(@PathVariable Long id) {
        try {
            Catalogue catalogue = catalogueService.getCatalogueById(id);
            return ResponseEntity.ok(catalogue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logDebug("Error getting catalogue: " + e.getMessage(), e, "G");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération du catalogue.");
        }
    }
}
