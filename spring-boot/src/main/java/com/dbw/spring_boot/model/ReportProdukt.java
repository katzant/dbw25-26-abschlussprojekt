package com.dbw.spring_boot.model;

public class ReportProdukt {

    private final String sku;
    private final String name;
    private final int gesamtVerkaufteMenge;
    private final double umsatz;
    private final int anzahlBestellungen;

    public ReportProdukt(String sku, String name, int gesamtVerkaufteMenge, double umsatz, int anzahlBestellungen) {
        this.sku = sku;
        this.name = name;
        this.gesamtVerkaufteMenge = gesamtVerkaufteMenge;
        this.umsatz = umsatz;
        this.anzahlBestellungen = anzahlBestellungen;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public int getGesamtVerkaufteMenge() {
        return gesamtVerkaufteMenge;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public int getAnzahlBestellungen() {
        return anzahlBestellungen;
    }
}
