package com.dbw.spring_boot.model;

public class ReportKunde {

    private final Integer kundeId;
    private final String email;
    private final Integer anzahlBestsellungen;
    private final double summeBestellungen;

    public ReportKunde(Integer kundeId, String email, Integer anzahlBestsellungen, double anzahlbestellungen) {
        this.kundeId = kundeId;
        this.email = email;
        this.anzahlBestsellungen = anzahlBestsellungen;
        this.summeBestellungen = anzahlbestellungen;
    }

    public Integer getKundeId() {
        return kundeId;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAnzahlBestsellungen() {
        return anzahlBestsellungen;
    }

    public double getSummeBestellungen() {
        return summeBestellungen;
    }
}
