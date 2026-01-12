package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"UserController.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

    @PostMapping("/{userId}/subscribeToCatalogue")
    public ResponseEntity<?> subscribeToCatalogue(@PathVariable Long userId) {
        try {
            userService.subscribeToCatalogueNotifications(userId);
            logDebug("User " + userId + " subscribed to catalogue notifications via API.", userId, "F");
            return ResponseEntity.ok("Abonnement aux notifications du catalogue réussi.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logDebug("Error subscribing user " + userId + ": " + e.getMessage(), e, "E");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }

    @PostMapping("/{userId}/unsubscribeFromCatalogue")
    public ResponseEntity<?> unsubscribeFromCatalogue(@PathVariable Long userId) {
        try {
            userService.unsubscribeFromCatalogueNotifications(userId);
            logDebug("User " + userId + " unsubscribed from catalogue notifications via API.", userId, "F");
            return ResponseEntity.ok("Désabonnement des notifications du catalogue réussi.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logDebug("Error unsubscribing user " + userId + ": " + e.getMessage(), e, "E");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }
}
