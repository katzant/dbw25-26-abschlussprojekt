package com.dbw.spring_boot.model;

public class ReportBestellstatus {

    private final int personalNr;
    private final String status;
    private final int anzahlBestellungen;

    public ReportBestellstatus(int personalNr, String status, int anzahlBestellungen) {
        this.personalNr = personalNr;
        this.status = status;
        this.anzahlBestellungen = anzahlBestellungen;
    }

    public int getPersonalNr() {
        return personalNr;
    }

    public String getStatus() {
        return status;
    }

    public int getAnzahlBestellungen() {
        return anzahlBestellungen;
    }
}
