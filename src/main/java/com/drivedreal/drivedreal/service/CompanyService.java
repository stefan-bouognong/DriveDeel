package com.drivedreal.drivedreal.service;

import com.drivedreal.drivedreal.dto.CompanyCreationRequest;
import com.drivedreal.drivedreal.entity.Company;
import com.drivedreal.drivedreal.entity.Role;
import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.entity.Subsidiary;
import com.drivedreal.drivedreal.repository.CompanyRepository;
import com.drivedreal.drivedreal.repository.SubsidiaryRepository;
import com.drivedreal.drivedreal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubsidiaryRepository subsidiaryRepository;

    // ========================================
    // LOG DEBUG (DEV ONLY)
    // ========================================
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter(
                "c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {

            fw.write(String.format(
                    "{\"hypothesisId\":\"%s\",\"location\":\"CompanyService.java:%d\","
                            + "\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                    hypothesisId,
                    new Throwable().getStackTrace()[1].getLineNumber(),
                    message,
                    data != null ? data.toString() : "null",
                    System.currentTimeMillis()
            ));
        } catch (IOException ignored) {
        }
    }

    // ========================================
    // CREATE COMPANY + USER (1–1)
    // ========================================
    @Transactional
    public Company createCompany(CompanyCreationRequest request) {

        logDebug("createCompany called", request, "A");

        // 1. Vérifier unicité email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }

        // 2. Créer le User D’ABORD
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .role(Role.COMPANY)
                .isCustomerCompany(true)
                .build();

        // Sauvegarde initiale pour avoir un ID
        userRepository.save(user);
        logDebug("User saved successfully", user, "A");

        // 3. Créer la Company
        Company company = new Company();
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setCustomer(user);  // lier User
        user.setCompany(company);   // lier Company à User

        // 4. Gérer la société mère si présente
        if (request.getParentCompanyId() != null) {
            Company parentCompany = companyRepository.findById(request.getParentCompanyId())
                    .orElseThrow(() -> new IllegalArgumentException("Société mère introuvable."));
            parentCompany.addChild(company); // ajoute l’enfant
        }

        // 5. Sauvegarde finale (cascade)
        Company savedCompany = companyRepository.save(company);
        logDebug("Company + User created successfully", savedCompany, "A");

        return savedCompany;
    }

    // ========================================
    // ADD SUBSIDIARY TO COMPANY
    // ========================================
    @Transactional
    public Company addSubsidiaryToCompany(Long companyId, Long subsidiaryId) {

        logDebug("addSubsidiaryToCompany called", null, "D");

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Société mère introuvable."));

        Subsidiary subsidiary = subsidiaryRepository.findById(subsidiaryId)
                .orElseThrow(() -> new IllegalArgumentException("Filiale introuvable."));

        subsidiary.setCompany(company);
        subsidiaryRepository.save(subsidiary);

        logDebug(
                "Subsidiary " + subsidiary.getName() + " linked to company " + company.getName(),
                null,
                "D"
        );

        return company;
    }
}
