package com.dbw.spring_boot.model;

public class Adresse {

    private final int adresseId;
    private final boolean aktiv;
    private final String strasse;
    private final String hausnummer;
    private final Integer plz;
    private final String ort;
    private final String land;

    public Adresse(int adresseId, boolean aktiv, String strasse, String hausnummer, Integer plz, String ort, String land) {
        this.adresseId = adresseId;
        this.aktiv = aktiv;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
    }

    public int getAdresseId() {
        return adresseId;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public Integer getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

}
