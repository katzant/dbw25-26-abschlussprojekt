package com.dbw.spring_boot.model;

public class LoginMitarbeiter {

    private final Integer personalNr;
    private final String passwort;

    public LoginMitarbeiter(Integer personalNr, String passwort) {
        this.personalNr = personalNr;
        this.passwort = passwort;
    }

    public Integer getPersonalNr() {
        return personalNr;
    }

    public String getPasswort() {
        return passwort;
    }
}
