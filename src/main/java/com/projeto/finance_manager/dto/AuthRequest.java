package com.projeto.finance_manager.dto;

public class AuthRequest {
    private String username;
    private String password;

    // Getters e Setters obrigatórios
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}