package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.dto.ClientCreationRequest;
import com.drivedreal.drivedreal.entity.Client;
import com.drivedreal.drivedreal.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody ClientCreationRequest request) {
        try {
            Client client = clientService.createClient(request);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur interne du serveur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
