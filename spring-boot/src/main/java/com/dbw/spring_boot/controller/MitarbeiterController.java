package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Mitarbeiter;
import com.dbw.spring_boot.repository.MitarbeiterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MitarbeiterController {

    private final MitarbeiterRepository mitarbeiterRepository;

    public MitarbeiterController(MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @GetMapping("/mitarbeiter")
    public ResponseEntity<?> getMitarbeiter(@RequestParam(required = false) Integer id) {
        if (id != null) {
            Mitarbeiter mitarbeiter = mitarbeiterRepository.findById(id);
            if (mitarbeiter != null) {
                return ResponseEntity.ok(mitarbeiter);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            List<Mitarbeiter> mitarbeiterListe = mitarbeiterRepository.findAll();
            return ResponseEntity.ok(mitarbeiterListe);
        }
    }

    @PostMapping("/mitarbeiter")
    public ResponseEntity<?> addMitarbeiter(@RequestBody Mitarbeiter mitarbeiter) {
        mitarbeiterRepository.save(mitarbeiter);
        return ResponseEntity.ok("Mitarbeiter wurde hinzugefügt");
    }

    @DeleteMapping("/mitarbeiter")
    public ResponseEntity<?> deleteMitarbeiter(@RequestParam Integer id) {
        if (mitarbeiterRepository.findById(id) == null) {
            return ResponseEntity.badRequest().body("Id gehört zu keinem gültigen Mitarbeiter");
        }
        mitarbeiterRepository.deleteById(id);
        return ResponseEntity.ok("Mitarbeiter wurde gelöscht");
    }
}
