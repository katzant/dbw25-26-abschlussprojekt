package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Bestellposition;
import com.dbw.spring_boot.model.BestellpositionDaten;
import com.dbw.spring_boot.model.Bestellung;
import com.dbw.spring_boot.model.Produkt;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BestellungRepository {

    private final JdbcTemplate jdbcTemplate;

    public BestellungRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Bestellung findById(int id) {
        String sqlBestellpositionen = "SELECT bestellposition.position_id, bestellposition.bestellung_id, " +
                "bestellposition.menge, produkt.sku, produkt.name, produkt.preis, produkt.lagerbestand, " +
                "produkt.angelegt_von, (bestellposition.menge * produkt.preis) AS gesamtpreis " +
                "FROM bestellposition " +
                "JOIN produkt ON bestellposition.sku = produkt.sku " +
                "WHERE bestellposition.bestellung_id = ?";

        List<BestellpositionDaten> bestellpositionen = jdbcTemplate.query(sqlBestellpositionen,
                (rs, row) -> {
            Produkt produkt = new Produkt(
                    rs.getString("sku"),
                    rs.getString("name"),
                    rs.getDouble("preis"),
                    rs.getInt("lagerbestand"),
                    rs.getInt("angelegt_von")
            );
            BestellpositionDaten bestellposition = new BestellpositionDaten(
                    rs.getInt("position_id"),
                    rs.getInt("bestellung_id"),
                    produkt,
                    rs.getInt("menge"),
                    rs.getDouble("gesamtpreis")
            );
            return bestellposition;
        }, id);

        String sqlBestellung = "SELECT bestellung_id, kunde_id, mitarbeiterzuweis, datum, status" +
                " FROM bestellung WHERE bestellung_id = ?";

        try {

            Bestellung bestellung = jdbcTemplate.queryForObject(sqlBestellung, (rs, row) ->
                new Bestellung(
                    rs.getInt("bestellung_id"),
                    rs.getInt("kunde_id"),
                    rs.getInt("mitarbeiterzuweis"),
                    rs.getString("datum"),
                    rs.getString("status"),
                    bestellpositionen
                ), id);
            return bestellung;

        } catch (DataAccessException e) {
            return null;
        }
    }

    public void save(Bestellung bestellung) {
        String sql = "INSERT INTO bestellung (kunde_id, mitarbeiterzuweis, datum, status) " +
                "VALUES (?, ?, ?::timestamp, ?)";
        jdbcTemplate.update(sql, bestellung.getKundeId(), bestellung.getPersonalNr(),
                bestellung.getDatum(), bestellung.getStatus());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM bestellung WHERE bestellung_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
