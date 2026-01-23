package com.dbw.spring_boot.model;

public class Mitarbeiter {

    private final int personalNr;
    private final String passwort;
    private final String email;
    private final String vorname;
    private final String nachname;

    public Mitarbeiter(int personalNr, String passwort, String email, String vorname, String nachname) {
        this.personalNr = personalNr;
        this.passwort = passwort;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public int getPersonalNr() {
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
