package com.projeto.finance_manager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

// Entidade que representa o usuário do sistema.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // novo campo obrigatório
}
