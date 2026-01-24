package com.dbw.spring_boot.model;

public class Mitarbeiter {

    private final Integer personalNr;
    private final String passwort;
    private final String email;
    private final String vorname;
    private final String nachname;

    public Mitarbeiter(Integer personalNr, String passwort, String email, String vorname, String nachname) {
        this.personalNr = personalNr;
        this.passwort = passwort;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Integer getPersonalNr() {
        return personalNr;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getEmail() {
        return email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }
}
