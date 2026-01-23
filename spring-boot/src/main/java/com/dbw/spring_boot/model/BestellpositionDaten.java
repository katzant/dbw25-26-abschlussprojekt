package com.dbw.spring_boot.model;

public class BestellpositionDaten {

    private final int positionsId;
    private final int bestellungId;
    private final Produkt produkt;
    private final int menge;
    private final double gesamtpreis;

    public BestellpositionDaten(int positionsId, int bestellungId, Produkt produkt, int menge, double gesamtpreis) {
        this.positionsId = positionsId;
        this.bestellungId = bestellungId;
        this.produkt = produkt;
        this.menge = menge;
        this.gesamtpreis = gesamtpreis;
    }

    public int getPositionsId() {
        return positionsId;
    }

    public int getBestellungId() {
        return bestellungId;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getMenge() {
        return menge;
    }

    public double getGesamtpreis() {
        return gesamtpreis;
    }
}
