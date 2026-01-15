package com.example.projet_java_fullstack.controller;

import com.example.projet_java_fullstack.dto.*;
import com.example.projet_java_fullstack.model.Role;
import com.example.projet_java_fullstack.model.Users;
import com.example.projet_java_fullstack.repository.UserRepository;
import com.example.projet_java_fullstack.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder encoder,
                          AuthenticationManager authManager,
                          JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (req.username == null || req.username.isBlank()
                || req.email == null || req.email.isBlank()
                || req.password == null || req.password.isBlank()) {
            return "Missing fields";
        }

        if (userRepo.existsByUsername(req.username)) return "Username already used";
        if (userRepo.existsByEmail(req.email)) return "Email already used";

        Users u = new Users();
        u.setUsername(req.username);
        u.setEmail(req.email);
        u.setPassword(encoder.encode(req.password));
        u.setRoles(Role.USER);
        u.setEnabled(true);

        userRepo.save(u);
        return "User registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.login, req.password)
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        Users u = userRepo.findByUsername(userDetails.getUsername()).orElseThrow();

        return new AuthResponse(token, u.getUsername(), u.getRoles().name());
    }
}
