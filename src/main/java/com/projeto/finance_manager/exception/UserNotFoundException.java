package com.projeto.finance_manager.exception;

// Exceção personalizada lançada quando um usuário não é encontrado.
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
