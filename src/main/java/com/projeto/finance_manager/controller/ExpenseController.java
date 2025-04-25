package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.model.Expense;
import com.projeto.finance_manager.repository.ExpenseRepository;
import com.projeto.finance_manager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller que gerencia as despesas (Expense).
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Cria uma nova despesa
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }

    // Lista todas as despesas
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Busca uma despesa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return ResponseEntity.ok(expense);
    }

    // Atualiza uma despesa existente
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
        return ResponseEntity.ok(updatedExpense);
    }

    // Deleta uma despesa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
