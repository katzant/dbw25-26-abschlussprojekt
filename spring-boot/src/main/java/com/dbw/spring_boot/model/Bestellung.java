package com.dbw.spring_boot.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bestellung {

    private final int bestellungId;
    private final int kundeId;
    private final int personalNr;
    private final String datum;
    private final String status;
    private final List<BestellpositionDaten> positionen;

    public Bestellung(int bestellungId, int kundeId, int personalNr, String datum,
                      String status, List<BestellpositionDaten> positionen) {
        this.bestellungId = bestellungId;
        this.kundeId = kundeId;
        this.personalNr = personalNr;
        this.datum = datum;
        this.status = status;
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

    public String getDatum() {
        return datum;
    }

    public String getStatus() {
        return status;
    }

    public List<BestellpositionDaten> getPositionen() {
        return positionen;
    }
}
