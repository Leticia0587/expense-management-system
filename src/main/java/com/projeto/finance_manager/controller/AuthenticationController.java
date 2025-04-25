package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.model.Role;
import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.repository.UserRepository;
import com.projeto.finance_manager.dto.AuthRequest;
import com.projeto.finance_manager.dto.AuthResponse;
import com.projeto.finance_manager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um Controller REST
@RequestMapping("/auth") // Define o caminho base para este controller
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager; // Para autenticar o usuário

    @Autowired
    private JwtUtil jwtUtil; // Para gerar o token JWT

    @Autowired
    private UserRepository userRepository; // Para acessar os dados do usuário no banco

    @Autowired
    private PasswordEncoder passwordEncoder; // Para codificar a senha do usuário

    @PostMapping("/login") // Endpoint para login
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // Tenta autenticar o usuário com username e senha
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Se a autenticação for bem-sucedida, gera o token JWT
        String token = jwtUtil.generateToken(authRequest.getUsername());
        // Retorna o token na resposta
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register") // Endpoint para registro de novo usuário
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        // Verifica se o username já existe no banco de dados
        if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        // Cria um novo objeto User
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setEmail(authRequest.getUsername()); // Define email igual ao username (pode ser separado depois)
        user.setPassword(passwordEncoder.encode(authRequest.getPassword())); // Codifica a senha antes de salvar
        user.setRole(Role.USER);  // Define o Role do usuário como USER

        // Salva o novo usuário no banco de dados
        userRepository.save(user);

        // Retorna mensagem de sucesso
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }
}