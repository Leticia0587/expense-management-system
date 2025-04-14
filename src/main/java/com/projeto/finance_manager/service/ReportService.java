package com.projeto.finance_manager.service;

import com.projeto.finance_manager.dto.ReportDTO;
import com.projeto.finance_manager.model.Expense;
import com.projeto.finance_manager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReportService {

    private final ExpenseRepository expenseRepository;

    public ReportService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ReportDTO getMonthlySummary() {
        List<Expense> expenses = expenseRepository.findAll();

        Map<String, BigDecimal> summary = new TreeMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Expense expense : expenses) {
            String month = expense.getDate().format(formatter);
            summary.merge(month, BigDecimal.valueOf(expense.getValue()), BigDecimal::add);
        }

        String suggestion = generateSuggestion(summary);
        return new ReportDTO(summary, suggestion);
    }

    private String generateSuggestion(Map<String, BigDecimal> summary) {
        if (summary.isEmpty()) return "Sem despesas para analisar.";
        List<BigDecimal> valores = new ArrayList<>(summary.values());
        if (valores.size() < 2) return "Poucos dados para gerar sugestões.";

        BigDecimal ultimo = valores.get(valores.size() - 1);
        BigDecimal penultimo = valores.get(valores.size() - 2);

        if (ultimo.compareTo(penultimo) > 0) {
            return "🚨 Suas despesas aumentaram no último mês. Considere revisar seus gastos.";
        } else {
            return "✅ Parabéns! Suas despesas diminuíram no último mês. Continue assim!";
        }
    }
}