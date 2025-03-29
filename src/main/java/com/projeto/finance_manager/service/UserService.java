package com.projeto.finance_manager.service;

import com.projeto.finance_manager.exception.UserNotFoundException;
import com.projeto.finance_manager.model.User;
import com.projeto.finance_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Criar novo usuário
    public User createUser(User user){
        return userRepository.save(user);
    }

    //Listar todos os usuários
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    // Atualizar usuário
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    // Deletar usuário
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
