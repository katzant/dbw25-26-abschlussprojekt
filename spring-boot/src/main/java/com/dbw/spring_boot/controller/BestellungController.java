package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Bestellung;
import com.dbw.spring_boot.repository.BestellungRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BestellungController {

    private final BestellungRepository bestellungRepository;

    public BestellungController(BestellungRepository bestellungRepository) {
        this.bestellungRepository = bestellungRepository;
    }

    @GetMapping("/bestellungen")
    public ResponseEntity<Bestellung> getBestellung(@RequestParam int id) {
        System.out.println("SUCHE BESTELLUNG   " + id);
        Bestellung bestellung = bestellungRepository.findById(id);
        if (bestellung != null) {
            return ResponseEntity.ok(bestellung);
        } else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/bestellungen")
    public ResponseEntity<?> addBestellung(@RequestBody Bestellung bestellung) {
        bestellungRepository.save(bestellung);
        return ResponseEntity.ok("Bestellung wurde hinzugefügt");
    }
}
