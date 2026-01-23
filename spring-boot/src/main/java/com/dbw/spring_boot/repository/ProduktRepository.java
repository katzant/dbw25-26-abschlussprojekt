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
}
