package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.ReportKunde;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReportKunde> findAll() {
        String sql = "SELECT kunde_id, email, anzahl, summe FROM v_kunde_summe_anzahl_bestellungen";

        List<ReportKunde> reportKunden = jdbcTemplate.query(sql, (rs, row) ->
                new ReportKunde(
                        rs.getInt("kunde_id"),
                        rs.getString("email"),
                        rs.getInt("anzahl"),
                        rs.getDouble("summe")
        ));
        return reportKunden;
    }
}
