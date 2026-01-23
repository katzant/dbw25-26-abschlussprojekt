package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Produkt;
import com.dbw.spring_boot.repository.MitarbeiterRepository;
import com.dbw.spring_boot.repository.ProduktRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProduktController {

    private final ProduktRepository produktRepository;
    private final MitarbeiterRepository mitarbeiterRepository;

    public ProduktController(ProduktRepository produktRepository, MitarbeiterRepository mitarbeiterRepository) {
        this.produktRepository = produktRepository;
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @GetMapping("/produkte")
    public ResponseEntity<?> getProdukt(@RequestParam String sku) {
        Produkt produkt = produktRepository.findBySku(sku);
        if (produkt != null) {
            return ResponseEntity.ok(produkt);
        } else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/produkte")
    public ResponseEntity<?> addProdukt(@RequestBody Produkt produkt) {
        if (produktRepository.findBySku(produkt.getSku()) != null) {
            return ResponseEntity.badRequest().body("Angegebene Sku existiert schon");
        }
        if (mitarbeiterRepository.findById(produkt.getAngelegtVon()) == null) {
            return ResponseEntity.badRequest().body("Mitarbeiter, der Produkt angelegt hat, " +
                    "existiert nicht");
        }
        produktRepository.save(produkt);
        return ResponseEntity.ok("Produkt wurde hinzugefügt");
    }

    @DeleteMapping("/produkte")
    public ResponseEntity<?> deleteProdukt(@RequestParam String sku) {
        if (produktRepository.findBySku(sku) == null) {
            return ResponseEntity.badRequest().body("Sku gehört zu keinem gültigen Produkt");
        }
        produktRepository.deleteBySku(sku);
        return ResponseEntity.ok("Produkt wurde gelöscht");
    }

    @PatchMapping("/produkte")
    public ResponseEntity<?> updateLagerbestand(@RequestParam String sku,
                                                @RequestParam int lagerbestand) {
        if (produktRepository.findBySku(sku) == null) {
            return ResponseEntity.badRequest().body("Produkt wurde nicht gefunden");
        } else {
            produktRepository.updateLagerbestand(sku, lagerbestand);
            return ResponseEntity.ok("Produkt wurde aktualisiert");
        }
    }
}
