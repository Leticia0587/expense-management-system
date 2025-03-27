package com.projeto.finance_manager.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    // getters e setters
    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
