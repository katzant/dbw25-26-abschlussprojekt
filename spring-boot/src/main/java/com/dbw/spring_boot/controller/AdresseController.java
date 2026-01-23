package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Adresse;
import com.dbw.spring_boot.repository.AdresseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdresseController {

    private final AdresseRepository adresseRepository;

    public AdresseController(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @GetMapping("/adressen")
    public ResponseEntity<?> getAdresse(@RequestParam Integer id) {
        Adresse adresse = adresseRepository.findbyId(id);
        if (adresse != null) {
            return ResponseEntity.ok(adresse);
        } else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/adressen")
    public ResponseEntity<?> addAdresse(@RequestBody Adresse adresse) {
        adresseRepository.save(adresse);
        return ResponseEntity.ok("Adresse wurde hinzugefügt");
    }

    @PutMapping("/adressen")
    public ResponseEntity<?> updateAdresseFromId(@RequestBody Adresse adresse) {
        if (adresseRepository.findbyId(adresse.getAdresseId()) == null) {
            return ResponseEntity.badRequest().build();
        } else {
            adresseRepository.updateAdresse(adresse.getAdresseId(), adresse);
            return ResponseEntity.ok("Adresse wurde aktualisiert");
        }
    }

}
