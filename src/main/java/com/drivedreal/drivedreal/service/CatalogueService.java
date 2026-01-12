package com.drivedreal.drivedreal.service;

import com.drivedreal.drivedreal.entity.Catalogue;
import com.drivedreal.drivedreal.repository.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    private final CatalogueRepository catalogueRepository;

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"CatalogueService.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

    @Transactional
    public Catalogue createCatalogue(String name) {
        Catalogue catalogue = new Catalogue();
        catalogue.setName(name);
        Catalogue savedCatalogue = catalogueRepository.save(catalogue);
        logDebug("Catalogue created: " + savedCatalogue.getName() + " with ID: " + savedCatalogue.getId(), savedCatalogue, "G");
        return savedCatalogue;
    }

    public Catalogue getCatalogueById(Long id) {
        return catalogueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Catalogue non trouvé."));
    }
}
