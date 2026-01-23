package com.dbw.spring_boot.model;

public class Bestellposition {

    private final int positionsId;
    private final int bestellungId;
    private final String produktSku;
    private final int menge;
    private final double gesamtpreis;

    public Bestellposition(int positionsId, int bestellungId, String produktSku, int menge, double gesamtpreis) {
        this.positionsId = positionsId;
        this.bestellungId = bestellungId;
        this.produktSku = produktSku;
        this.menge = menge;
        this.gesamtpreis = gesamtpreis;
    }

    public int getPositionsId() {
        return positionsId;
    }

    public int getBestellungId() {
        return bestellungId;
    }

    public String getProduktSku() {
        return produktSku;
    }

    public int getMenge() {
        return menge;
    }

    public double getGesamtpreis() {
        return gesamtpreis;
    }
}
