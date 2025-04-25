package com.projeto.finance_manager.security;

import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Método obrigatório: busca o usuário no banco e monta o UserDetails do Spring
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo nome de login (username)
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retorna o objeto UserDetails que será usado pelo Spring Security
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername()) // Nome de login
                .password(user.getPassword()) // Senha já criptografada com BCrypt
                .roles(user.getRole().name()) // Perfis (ex: USER, ADMIN)
                .build();
    }
}