package com.dbw.spring_boot.model;

public class AdresseTypDaten {

    private final Adresse adresse;
    private final String typ;

    public AdresseTypDaten(Adresse adresse, String typ) {
        this.adresse = adresse;
        this.typ = typ;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getTyp() {
        return typ;
    }
}
