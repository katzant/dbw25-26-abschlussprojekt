package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.Produkt;
import com.dbw.spring_boot.repository.ProduktRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduktController {

    private final ProduktRepository produktRepository;

    public ProduktController(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }

    @GetMapping("/produkte")
    public ResponseEntity<?> getProdukt(@RequestParam String sku) {
        Produkt produkt = produktRepository.findBySku(sku);
        if (produkt != null) {
            return ResponseEntity.ok(produkt);
        } else return ResponseEntity.badRequest().build();
    }
}
