package com.projeto.finance_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity // Essa anotação é obrigatória
@Table(name = "expenses")
public class Expense {

    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double value;
    private LocalDate date;
    private String category;

}