package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Bestellposition;
import com.dbw.spring_boot.repository.BestellpositionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestellpositionController {

    private final BestellpositionRepository bestellpositionRepository;

    public BestellpositionController(BestellpositionRepository bestellpositionRepository) {
        this.bestellpositionRepository = bestellpositionRepository;
    }

    @GetMapping("/bestellpositionen")
    public ResponseEntity<?> getBestellposiiton(@RequestParam int id) {
        Bestellposition bestellposition = bestellpositionRepository.findById(id);
        if (bestellposition != null) {
            return ResponseEntity.ok(bestellposition);
        } else return ResponseEntity.notFound().build();
    }
}
