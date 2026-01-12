package com.drivedreal.drivedreal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreationRequest {
    private String name;
    private String address;
    private Long parentCompanyId;

    // Champs pour l'utilisateur et le client
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone; // Champ additionnel pour le client
}
