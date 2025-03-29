package com.projeto.finance_manager.service;

import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        log.info("Buscando usuário com ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("Usuário encontrado: {}", user.get());
            return user.get();
        } else {
            log.warn("Usuário com ID {} não encontrado.", id);
            return null;
        }
    }

    public User save(User user) {
        log.info("Salvando novo usuário: {}", user);
        User savedUser = userRepository.save(user);
        log.info("Usuário salvo com sucesso: {}", savedUser);
        return savedUser;
    }
}