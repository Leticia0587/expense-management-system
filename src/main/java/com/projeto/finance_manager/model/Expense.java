package com.projeto.finance_manager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;
    private LocalDate date;
    private String category;

    // Relacionamento com o usuário
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
