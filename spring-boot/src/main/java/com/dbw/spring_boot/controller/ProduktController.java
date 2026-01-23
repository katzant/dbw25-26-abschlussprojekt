package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.repository.ProduktRepository;
import org.springframework.stereotype.Controller;

@Controller
public class ProduktController {

    private final ProduktRepository produktRepository;

    public ProduktController(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }
}
