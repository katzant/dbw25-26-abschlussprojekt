package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.ReportBestellstatus;
import com.dbw.spring_boot.model.ReportKunde;
import com.dbw.spring_boot.model.ReportMitarbeiter;
import com.dbw.spring_boot.model.ReportProdukt;
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
        List<ReportKunde> reportKunden = reportRepository.findAllReportKunden();
        return ResponseEntity.ok(reportKunden);
    }

    @GetMapping("/report/produkt/verkaufszahlen")
    public ResponseEntity<?> getVerkaufszahlen() {
        List<ReportProdukt> reportProdukte = reportRepository.findAllReportProdukte();
        return ResponseEntity.ok(reportProdukte);
    }

    @GetMapping("/report/mitarbeiter/uebersicht")
    public ResponseEntity<?> getMitarbeiterUebersicht() {
        List<ReportMitarbeiter> reportMitarbeiter = reportRepository.findAllReportMitarbeiter();
        return ResponseEntity.ok(reportMitarbeiter);
    }

    @GetMapping("/report/mitarbeiter/bestellstatus-uebersicht")
    public ResponseEntity<?> getBestellstatusUebersicht() {
        List<ReportBestellstatus> reportBestellstatus = reportRepository.findAllReportBestellstatus();
        return ResponseEntity.ok(reportBestellstatus);
    }
}
