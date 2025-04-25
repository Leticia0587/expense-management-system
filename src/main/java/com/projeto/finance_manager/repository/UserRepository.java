package com.projeto.finance_manager.repository;

import com.projeto.finance_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositório JPA para a entidade User.
// Contém métodos para buscar por e-mail e nome de usuário.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}