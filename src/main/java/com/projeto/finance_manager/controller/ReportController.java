package com.projeto.finance_manager.controller;

import com.projeto.finance_manager.dto.ReportDTO;
import com.projeto.finance_manager.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/monthly-summary")
    public ResponseEntity<ReportDTO> getMonthlySummary() {
        return ResponseEntity.ok(reportService.getMonthlySummary());
    }
}