package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.dto.CompanyCreationRequest;
import com.drivedreal.drivedreal.entity.Company;
import com.drivedreal.drivedreal.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyCreationRequest request) {
        try {
            Company newCompany = companyService.createCompany(request);
            return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity("Erreur interne du serveur lors de la création de la société.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
