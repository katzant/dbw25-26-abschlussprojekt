package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Adresse;
import com.dbw.spring_boot.repository.AdresseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
