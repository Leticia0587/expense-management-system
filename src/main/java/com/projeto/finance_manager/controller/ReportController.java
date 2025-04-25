package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.dto.MonthlyStatsDTO;
import com.projeto.finance_manager.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller responsável por fornecer relatórios do sistema.
@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Retorna estatísticas mensais (ex: gastos por mês)
    @GetMapping("/monthly-stats")
    public ResponseEntity<List<MonthlyStatsDTO>> getMonthlyStatistics() {
        return ResponseEntity.ok(reportService.getMonthlyStatistics());
    }
}