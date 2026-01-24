package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.ReportKunde;
import com.dbw.spring_boot.repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private final ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/report/kunde/summe-anzahl-bestellungen")
    public ResponseEntity<?> getSummeAnzahlBestellungen() {
        List<ReportKunde> reportKunden = reportRepository.findAll();
        return ResponseEntity.ok(reportKunden);
    }
}
