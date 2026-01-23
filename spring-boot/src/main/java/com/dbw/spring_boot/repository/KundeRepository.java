package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.AdresseTypDaten;
import com.dbw.spring_boot.model.Kunde;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AdresseRepository adresseRepository;

    public KundeRepository(JdbcTemplate jdbcTemplate, AdresseRepository adresseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.adresseRepository = adresseRepository;
    }

    public List<Kunde> findAll() {
        String sql = "SELECT * FROM kunde";
        List<Kunde> kunden = jdbcTemplate.query(sql, (rs, row) -> {
            List<AdresseTypDaten> adressen = adresseRepository.findAdressenByKundeId(
                    rs.getInt("kunde_id")
                );
            return new Kunde(
                    rs.getInt("kunde_id"),
                    rs.getString("email"),
                    rs.getString("vorname"),
                    rs.getString("nachname"),
                    rs.getString("passwort"),
                    adressen
                );
            }
        );
        return kunden;
    }

    public Kunde findById(int id) {
        String sql = "SELECT * FROM kunde WHERE kunde_id = ?";
        List<AdresseTypDaten> adressen = adresseRepository.findAdressenByKundeId(id);
        try {
            Kunde kunde = jdbcTemplate.queryForObject(sql, (rs, row) ->
                new Kunde(
                        rs.getInt("kunde_id"),
                        rs.getString("email"),
                        rs.getString("vorname"),
                        rs.getString("nachname"),
                        rs.getString("passwort"),
                        adressen
                ), id);
            return kunde;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public Kunde findByEmail(String email) {
        String sql = "SELECT * FROM kunde WHERE email = ?";
        try {
            Kunde kunde = jdbcTemplate.queryForObject(sql, (rs, row) -> {
                    int kundeId = rs.getInt("kunde_id");
                    List<AdresseTypDaten> adressen = adresseRepository.findAdressenByKundeId(kundeId);
                    return new Kunde(
                            rs.getInt("kunde_id"),
                            rs.getString("email"),
                            rs.getString("vorname"),
                            rs.getString("nachname"),
                            rs.getString("passwort"),
                            adressen
                    );
            }, email);
            return kunde;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void save(Kunde kunde) {
        String sql = "INSERT INTO kunde (vorname, nachname, passwort, email) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, kunde.getVorname(), kunde.getNachname(),
                kunde.getPasswort(), kunde.getEmail());
    }

    public void updateKunde(int kundeId, Kunde kunde) {
        String sql = "UPDATE kunde SET email = ?, vorname = ?, nachname = ?, " +
                "passwort = ? WHERE kunde_id = ?";
        jdbcTemplate.update(sql, kunde.email, kunde.vorname, kunde.nachname,
                kunde.passwort, kundeId);
    }
}
