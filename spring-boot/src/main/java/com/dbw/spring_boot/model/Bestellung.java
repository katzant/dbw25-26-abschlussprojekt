package com.dbw.spring_boot.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bestellung {

    private final int bestellungId;
    private final int kundeId;
    private final int personalNr;
    private final LocalDateTime datum;
    private final List<Bestellposition> positionen;

    public Bestellung(int bestellungId, int kundeId, int personalNr, LocalDateTime datum, List<Bestellposition> positionen) {
        this.bestellungId = bestellungId;
        this.kundeId = kundeId;
        this.personalNr = personalNr;
        this.datum = datum;
        this.positionen = positionen;
    }

    public int getBestellungId() {
        return bestellungId;
    }

    public int getKundeId() {
        return kundeId;
    }

    public int getPersonalNr() {
        return personalNr;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public List<Bestellposition> getPositionen() {
        return positionen;
    }
}
