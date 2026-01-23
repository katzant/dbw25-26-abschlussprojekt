package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Bestellposition;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BestellpositionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ProduktRepository produktRepository;

    public BestellpositionRepository(JdbcTemplate jdbcTemplate, ProduktRepository produktRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.produktRepository = produktRepository;
    }

    public Bestellposition findById(int id) {
        String sql = "SELECT bestellposition.position_id, bestellposition.bestellung_id, " +
                "bestellposition.sku, bestellposition.menge, (bestellposition.menge * produkt.preis) AS gesamtpreis " +
                "FROM bestellposition JOIN produkt " +
                "ON bestellposition.sku = produkt.sku " +
                "WHERE bestellposition.position_id = ?";
        try {
            Bestellposition bestellposition = jdbcTemplate.queryForObject(sql, (rs, row) ->
                    new Bestellposition(
                            rs.getInt("position_id"),
                            rs.getInt("bestellung_id"),
                            rs.getString("sku"),
                            rs.getInt("menge"),
                            rs.getDouble("gesamtpreis")
                    ), id);
            return bestellposition;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void save(Bestellposition bestellposition) {
        String sql = "INSERT INTO bestellposition (bestellung_id, sku, menge) " +
                "VALUES (?, ?, ?)";
                produktRepository.findBySku(bestellposition.getProduktSku()).getPreis();
        jdbcTemplate.update(sql, bestellposition.getBestellungId(),
                bestellposition.getProduktSku(), bestellposition.getMenge());
    }
}
