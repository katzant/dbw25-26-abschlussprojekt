package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Produkt;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProduktRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProduktRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Produkt findBySku(String sku) {
        String sql = "SELECT * FROM produkt WHERE sku = ?";
        try {
            Produkt produkt = jdbcTemplate.queryForObject(sql, (rs, row) ->
                    new Produkt(
                            rs.getString("sku"),
                            rs.getString("name"),
                            rs.getDouble("preis"),
                            rs.getInt("lagerbestand"),
                            rs.getInt("angelegt_von")
                    ), sku);
            return produkt;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void save(Produkt produkt){
        String sql = "INSERT INTO produkt (sku, name, preis, lagerbestand, angelegt_von) " +
                "VALUES (?, ?, ?, ? ,?)";
        jdbcTemplate.update(sql, produkt.getSku(), produkt.getName(), produkt.getPreis(),
                produkt.getLagerbestand(), produkt.getAngelegtVon());
    }

    public void deleteBySku(String sku) {
        String sql = "DELETE FROM produkt WHERE sku = ?";
        jdbcTemplate.update(sql, sku);
    }

    public void updateLagerbestand(String sku, int lagerbestand) {
        String sql = "UPDATE produkt SET lagerbestand = ? WHERE sku = ?";
        jdbcTemplate.update(sql, lagerbestand, sku);
    }
}
