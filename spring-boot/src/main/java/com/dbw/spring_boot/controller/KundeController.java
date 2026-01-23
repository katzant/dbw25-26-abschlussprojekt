package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Kunde;
import com.dbw.spring_boot.repository.KundeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KundeController {

    private final KundeRepository kundeRepository;

    public KundeController(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    @GetMapping("/kunden")
    public ResponseEntity<?> getKunden(@RequestParam(required = false) Integer id,
                                       @RequestParam(required = false) String email) {
        if (id != null) {
            Kunde kunde = kundeRepository.findById(id);
            if (kunde != null) {
                return ResponseEntity.ok(kunde);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if (email != null) {
            Kunde kunde = kundeRepository.findByEmail(email);
            if (kunde != null) {
                return ResponseEntity.ok(kunde);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        List<Kunde> kunden = kundeRepository.findAll();
        return ResponseEntity.ok(kunden);
    }

    @PostMapping("/kunden")
    public ResponseEntity<?> addKunde(@RequestBody Kunde kunde) {
        kundeRepository.save(kunde);
        return ResponseEntity.ok("Kunde wurde hinzugefügt");
    }

    @PutMapping("/kunden")
    public ResponseEntity<?> updateKundeFromId(@RequestBody Kunde kunde) {
        if (kundeRepository.findById(kunde.getKundeId()) == null) {
            return ResponseEntity.badRequest().build();
        } else {
            kundeRepository.updateKunde(kunde.getKundeId(), kunde);
            return ResponseEntity.ok("Kunde wurde aktualisiert");
        }
    }
}
