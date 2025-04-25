package com.projeto.finance_manager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta definida no application.properties
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    // Tempo de validade do token em milissegundos (1 dia)
    private static final long EXPIRATION_TIME = 86400000;

    // Após a injeção dos valores, inicializa a chave segura do JWT
    @PostConstruct
    public void init() {
        // Converte a string da chave secreta em uma chave criptográfica segura
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera um novo token JWT com base no nome do usuário
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Define o usuário dono do token
                .setIssuedAt(new Date()) // Data de criação
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiração
                .signWith(secretKey) // Assina com a chave segura
                .compact(); // Finaliza e retorna o token
    }

    // Extrai o nome de usuário contido no token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Valida se o token pertence ao usuário e não está expirado
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Extrai as informações (claims) do token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Define a chave para validar o token
                .build()
                .parseClaimsJws(token) // Analisa o token JWT
                .getBody(); // Retorna o corpo com os dados (claims)
    }
}