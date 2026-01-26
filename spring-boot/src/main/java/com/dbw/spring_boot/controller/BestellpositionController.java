package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Bestellposition;
import com.dbw.spring_boot.repository.BestellpositionRepository;
import com.dbw.spring_boot.repository.ProduktRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BestellpositionController {

    private final BestellpositionRepository bestellpositionRepository;
    private final ProduktRepository produktRepository;

    public BestellpositionController(BestellpositionRepository bestellpositionRepository,
                                     ProduktRepository produktRepository) {
        this.bestellpositionRepository = bestellpositionRepository;
        this.produktRepository = produktRepository;
    }

    @GetMapping("/bestellpositionen")
    public ResponseEntity<?> getBestellposiiton(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Bestellposition bestellposition = bestellpositionRepository.findById(id);
            if (bestellposition != null) {
                return ResponseEntity.ok(bestellposition);
            } else return ResponseEntity.notFound().build();
        } else {
            List<Bestellposition> bestellpositionen = bestellpositionRepository.findAll();
            return ResponseEntity.ok(bestellpositionen);
        }
    }

    @PostMapping("/bestellpositionen")
    public ResponseEntity<?> addBestellposition(@RequestBody Bestellposition bestellposition) {
        if (produktRepository.findBySku(bestellposition.getProduktSku()) == null) {
            return ResponseEntity.badRequest().body("Produkt zu angegebener Sku existiert nicht");
        }
        bestellpositionRepository.save(bestellposition);
        return ResponseEntity.ok(bestellposition);
    }

    @DeleteMapping("/bestellpositionen")
    public ResponseEntity<?> deleteBestellposition(int positionId) {
        if (bestellpositionRepository.findById(positionId) == null) {
            return ResponseEntity.badRequest().body("Bestellposition wurde nicht gefunden");
        } else {
            bestellpositionRepository.deleteByPositionId(positionId);
            return ResponseEntity.ok("Bestellposition wurde gelöscht");
        }
    }
}
