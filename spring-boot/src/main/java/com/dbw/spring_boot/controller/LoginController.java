package com.dbw.spring_boot.controller;

import com.dbw.spring_boot.model.LoginMitarbeiter;
import com.dbw.spring_boot.model.Mitarbeiter;
import com.dbw.spring_boot.repository.MitarbeiterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final MitarbeiterRepository mitarbeiterRepository;

    public LoginController(MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @PostMapping("/login/mitarbeiter")
    public ResponseEntity<?> mitarbeiterLogin(@RequestBody LoginMitarbeiter loginMitarbeiter) {
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findById(loginMitarbeiter.getPersonalNr());
        Mitarbeiter nullMitarbeiter = new Mitarbeiter(null, null, null, null, null);
        if (mitarbeiter == null) {
            return ResponseEntity.status(401).body(nullMitarbeiter);
        } else if (!mitarbeiter.getPasswort().equals(loginMitarbeiter.getPasswort())) {
            return ResponseEntity.status(401).body(nullMitarbeiter);
        }
        Mitarbeiter mitarbeiterErgebnis = new Mitarbeiter(
                mitarbeiter.getPersonalNr(),
                null,
                mitarbeiter.getEmail(),
                mitarbeiter.getVorname(),
                mitarbeiter.getNachname()
        );
        return ResponseEntity.ok(mitarbeiterErgebnis);
    }
}
