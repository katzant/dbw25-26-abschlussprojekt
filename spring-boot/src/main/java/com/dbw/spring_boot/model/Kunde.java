package com.dbw.spring_boot.model;

import java.util.List;

public class Kunde {

    private final Integer kundeId;
    private final String email;
    private final String passwort;
    private final String vorname;
    private final String nachname;
    private final List<AdresseTypDaten> adressen;

    public Kunde(Integer kundeId, String email, String vorname, String nachname, String passwort, List<AdresseTypDaten> adressen) {
        this.kundeId = kundeId;
        this.email = email;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adressen = adressen;
    }

    public Integer getKundeId() {
        return kundeId;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public List<AdresseTypDaten> getAdressen() {
        return adressen;
    }
}
