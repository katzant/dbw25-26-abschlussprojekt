package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.ReportKunde;
import com.dbw.spring_boot.model.ReportProdukt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ReportKunde> findAllReportKunden() {
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

    public List<ReportProdukt> findAllReportProdukte() {
        String sql = "SELECT sku, name, gesamt_verkaufte_menge, umsatz, anzahl_bestellungen " +
                "FROM v_produkt_verkaufszahlen";

        List<ReportProdukt> reportProdukte = jdbcTemplate.query(sql, (rs, row) ->
                new ReportProdukt(
                        rs.getString("sku"),
                        rs.getString("name"),
                        rs.getInt("gesamt_verkaufte_menge"),
                        rs.getDouble("umsatz"),
                        rs.getInt("anzahl_bestellungen")
                ));
        return reportProdukte;
    }
}
