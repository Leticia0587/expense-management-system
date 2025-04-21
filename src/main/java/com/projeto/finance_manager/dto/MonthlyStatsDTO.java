package com.projeto.finance_manager.dto;

import java.math.BigDecimal;

public class MonthlyStatsDTO {

    private String month;
    private BigDecimal totalValue;
    private Long totalExpenses;
    private BigDecimal averageValue;

    public MonthlyStatsDTO(String month, BigDecimal totalValue, Long totalExpenses, BigDecimal averageValue) {
        this.month = month;
        this.totalValue = totalValue;
        this.totalExpenses = totalExpenses;
        this.averageValue = averageValue;
    }

    public String getMonth() {
        return month;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Long getTotalExpenses() {
        return totalExpenses;
    }

    public BigDecimal getAverageValue() {
        return averageValue;
    }
}