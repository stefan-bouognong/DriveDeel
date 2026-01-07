package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.dto.AuthRequest;
import com.drivedreal.drivedreal.dto.AuthResponse;
import com.drivedreal.drivedreal.entity.Role;
import com.drivedreal.drivedreal.entity.User;
import com.drivedreal.drivedreal.repository.UserRepository;
import com.drivedreal.drivedreal.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt, user.getRole().name(), "Connexion réussie"));
    }

    // Endpoint pour créer un admin (à utiliser une seule fois ou sécuriser)
    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin() {
        if (userRepository.findByEmail("admin@drivedreal.com").isPresent()) {
            return ResponseEntity.badRequest().body("Admin existe déjà");
        }

        User admin = User.builder()
                .email("admin@drivedreal.com")
                .password(passwordEncoder.encode("admin123"))
                .firstName("Admin")
                .lastName("System")
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);
        return ResponseEntity.ok("Admin créé avec succès");
    }
    @PostMapping("/register-client")
    public ResponseEntity<String> registerClient(@RequestBody AuthRequest request) {
        System.out.println("Requête reçue : " + request);
        
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Client existe déjà");
        }

        User client = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.CLIENT)
                .build();

        userRepository.save(client);
        System.out.println("Client créé : " + client.getEmail());
        return ResponseEntity.ok("Client créé avec succès");
    }


}