package com.drivedreal.drivedreal.service;

import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.repository.UserRepository;
import com.drivedreal.drivedreal.repository.CatalogueRepository;
import com.drivedreal.drivedreal.entity.Catalogue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CatalogueRepository catalogueRepository;

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"UserService.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

    public void subscribeToCatalogueNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé."));
        Catalogue defaultCatalogue = catalogueRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Catalogue par défaut non trouvé."));
        defaultCatalogue.addObserver(user);
        catalogueRepository.save(defaultCatalogue);
        logDebug("User " + user.getEmail() + " subscribed to catalogue notifications.", user, "F");
    }

    public void unsubscribeFromCatalogueNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé."));
        Catalogue defaultCatalogue = catalogueRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Catalogue par défaut non trouvé."));
        defaultCatalogue.removeObserver(user);
        catalogueRepository.save(defaultCatalogue);
        logDebug("User " + user.getEmail() + " unsubscribed from catalogue notifications.", user, "F");
    }
}
