package com.projeto.finance_manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

// DTO que representa o resumo do relatório mensal.
@Setter
@Getter
public class ReportDTO {
    private Map<String, BigDecimal> monthlySummary;
    private String suggestion;

    public ReportDTO(Map<String, BigDecimal> monthlySummary, String suggestion) {
        this.monthlySummary = monthlySummary;
        this.suggestion = suggestion;
    }

}