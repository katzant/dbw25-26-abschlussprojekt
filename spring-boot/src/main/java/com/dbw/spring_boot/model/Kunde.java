package com.dbw.spring_boot.model;

import java.util.List;

public class Kunde {

    public final int kundeId;
    public final String email;
    public final String passwort;
    public final String vorname;
    public final String nachname;
    public final List<AdresseTypDaten> adressen;

    public Kunde(int kundeId, String email, String vorname, String nachname, String passwort, List<AdresseTypDaten> adressen) {
        this.kundeId = kundeId;
        this.email = email;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adressen = adressen;
    }

    public int getKundeId() {
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
