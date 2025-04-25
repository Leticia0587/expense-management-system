package com.projeto.finance_manager.repository;

import com.projeto.finance_manager.dto.MonthlyStatsDTO;
import com.projeto.finance_manager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Repositório JPA para a entidade Expense. Inclui método customizado com @Query para estatísticas mensais.
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT " +
            "DATE_FORMAT(e.date, '%M') AS monthName, " +
            "SUM(e.value), COUNT(e), AVG(e.value) " +
            "FROM Expense e " +
            "GROUP BY DATE_FORMAT(e.date, '%M'), MONTH(e.date) " +
            "ORDER BY MONTH(e.date)")
    List<Object[]> getMonthlyStatsRaw();
}