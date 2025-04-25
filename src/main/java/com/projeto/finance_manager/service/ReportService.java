package com.projeto.finance_manager.service;

import com.projeto.finance_manager.dto.MonthlyStatsDTO;
import com.projeto.finance_manager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Serviço responsável pela geração de relatórios. Converte os dados brutos da query em objetos DTO.
@Service
public class ReportService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<MonthlyStatsDTO> getMonthlyStatistics() {
        List<Object[]> results = expenseRepository.getMonthlyStatsRaw();
        List<MonthlyStatsDTO> stats = new ArrayList<>();

        for (Object[] row : results) {
            String month = (String) row[0];

            // Conversões seguras:
            BigDecimal total = BigDecimal.valueOf(((Number) row[1]).doubleValue());
            Long count = ((Number) row[2]).longValue();
            BigDecimal average = BigDecimal.valueOf(((Number) row[3]).doubleValue());

            stats.add(new MonthlyStatsDTO(month, total, count, average));
        }

        return stats;
    }
}