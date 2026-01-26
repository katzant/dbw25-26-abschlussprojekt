package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Bestellung;
import com.dbw.spring_boot.repository.BestellungRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BestellungController {

    private final BestellungRepository bestellungRepository;

    public BestellungController(BestellungRepository bestellungRepository) {
        this.bestellungRepository = bestellungRepository;
    }

    @GetMapping("/bestellungen")
    public ResponseEntity<?> getBestellung(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Bestellung bestellung = bestellungRepository.findById(id);
            if (bestellung != null) {
                return ResponseEntity.ok(bestellung);
            } else return ResponseEntity.badRequest().build();
        } else {
            List<Bestellung> bestellungen = bestellungRepository.findAll();
            return ResponseEntity.ok(bestellungen);
        }
    }

    @PostMapping("/bestellungen")
    public ResponseEntity<?> addBestellung(@RequestBody Bestellung bestellung) {
        bestellungRepository.save(bestellung);
        return ResponseEntity.ok("Bestellung wurde hinzugefügt");
    }

    @DeleteMapping("/bestellungen")
    public ResponseEntity<?> deleteBestellung(@RequestParam int id) {
        if (bestellungRepository.findById(id) == null) {
            return ResponseEntity.badRequest().body("Id gehört zu keinem gültigen Produkt");
        }
        bestellungRepository.deleteById(id);
        return ResponseEntity.ok("Bestellung wurde gelöscht");
    }
}
