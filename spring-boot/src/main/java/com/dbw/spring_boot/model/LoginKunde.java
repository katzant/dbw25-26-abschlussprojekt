package com.dbw.spring_boot.model;

public class LoginKunde {

    private final String email;
    private final String passwort;

    public LoginKunde(String email, String passwort) {
        this.email = email;
        this.passwort = passwort;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
