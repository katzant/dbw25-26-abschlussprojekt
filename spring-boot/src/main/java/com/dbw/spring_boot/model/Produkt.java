package com.dbw.spring_boot.model;

public class Produkt {

    private final int sku;
    private final String name;
    private final double preis;
    private final int lagerbestand;
    private final int angelegtVon;

    public Produkt(int sku, String name, double preis, int lagerbestand, int angelegtVon) {
        this.sku = sku;
        this.name = name;
        this.preis = preis;
        this.lagerbestand = lagerbestand;
        this.angelegtVon = angelegtVon;
    }

    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public int getLagerbestand() {
        return lagerbestand;
    }

    public int getAngelegtVon() {
        return angelegtVon;
    }
}
