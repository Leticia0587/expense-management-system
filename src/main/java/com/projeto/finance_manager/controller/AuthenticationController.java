package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.dto.AuthRequest;
import com.projeto.finance_manager.dto.AuthResponse;
import com.projeto.finance_manager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody AuthRequest authRequest) {
        // Autentica o usuário com base nas credencias fornecidas
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Se a autenticação for bem-sucedida, gera o token JWT
        String token = jwtUtil.generateToken(authRequest.getUsername());

        // Retorna o token JWT na resposta
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
