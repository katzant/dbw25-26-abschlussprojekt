package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.repository.BestellpositionRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BestellpositionController {

    private final BestellpositionRepository bestellpositionRepository;

    public BestellpositionController(BestellpositionRepository bestellpositionRepository) {
        this.bestellpositionRepository = bestellpositionRepository;
    }
}
