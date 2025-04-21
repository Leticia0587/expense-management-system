package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.model.Expense;
import com.projeto.finance_manager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<YearMonth, Double>> getMonthlyExpenseSummary() {
        // Busca todas as despesas do banco
        List<Expense> expenses = expenseRepository.findAll();

        // Agrupa as despesas por mês e soma os valores
        Map<YearMonth, Double> summary = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> YearMonth.from(expense.getDate()),
                        Collectors.summingDouble(expense -> expense.getValue().doubleValue()) // Conversão para double aqui
                ));

        return ResponseEntity.ok(summary);
    }
}
