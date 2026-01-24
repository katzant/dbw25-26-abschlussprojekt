package com.dbw.spring_boot.model;

public class ReportMitarbeiter {

    private final int personalNr;
    private final int anzahlVerwalteterBestellungen;
    private final int anzahlAngelegterProdukte;

    public ReportMitarbeiter(int personalNr, int anzahlVerwalteterBestellungen, int anzahlAngelegterProdukte) {
        this.personalNr = personalNr;
        this.anzahlVerwalteterBestellungen = anzahlVerwalteterBestellungen;
        this.anzahlAngelegterProdukte = anzahlAngelegterProdukte;
    }

    public int getPersonalNr() {
        return personalNr;
    }

    public int getAnzahlVerwalteterBestellungen() {
        return anzahlVerwalteterBestellungen;
    }

    public int getAnzahlAngelegterProdukte() {
        return anzahlAngelegterProdukte;
    }
}
