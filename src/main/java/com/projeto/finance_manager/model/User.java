package com.projeto.finance_manager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime createdAt;

    //Construtor padrão obrigatorio JPA
    public User() {
    }


        //Construtor para facilitar a criação de usuários
    public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.createdAt = LocalDateTime.now();
        }

        //Getters e Setters para acessar os atributos de forma controlada
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password){
            this.password = password;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }