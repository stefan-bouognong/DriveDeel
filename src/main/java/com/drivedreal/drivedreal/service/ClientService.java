package com.drivedreal.drivedreal.service;


import com.drivedreal.drivedreal.dto.ClientCreationRequest;
import com.drivedreal.drivedreal.entity.Client;
import com.drivedreal.drivedreal.entity.Role;
import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.repository.ClientRepository;
import com.drivedreal.drivedreal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client createClient(ClientCreationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }

       
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .role(Role.CLIENT)
                .build();

        // 2️ Créer le Client
        Client client = Client.builder()
                .name(request.getName())
                .address(request.getAddress())
                .user(user)  // lien
                .build();

        // Lier bidirectionnel
        user.setClient(client);

        // 3️ Sauvegarde
        return clientRepository.save(client);
    }
}

