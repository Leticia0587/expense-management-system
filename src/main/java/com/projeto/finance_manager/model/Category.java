package com.projeto.finance_manager.model;

import jakarta.persistence.*;
import lombok.*;

// Entidade que representa uma categoria de despesa.
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}