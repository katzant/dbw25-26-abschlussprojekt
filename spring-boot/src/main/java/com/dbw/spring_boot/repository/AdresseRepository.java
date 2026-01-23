package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Adresse;
import com.dbw.spring_boot.model.AdresseTypDaten;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresseRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdresseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AdresseTypDaten> findAdressenByKundeId(int kundeId) {
        String sql = "SELECT adresse.adresse_id, adresse.aktiv, adresse.strasse, " +
                "adresse.hausnummer, adresse.plz, adresse.ort, adresse.land, " +
                "kunde_hat_adressen.typ " +
                "FROM adresse " +
                "JOIN kunde_hat_adressen " +
                    "ON adresse.adresse_id = kunde_hat_adressen.adresse_id " +
                "WHERE kunde_hat_adressen.kunde_id = ?";

        List<AdresseTypDaten> adressen = jdbcTemplate.query(sql, (rs, row) -> {
            Adresse adresse = new Adresse(
                        rs.getInt("adresse_id"),
                        rs.getBoolean("aktiv"),
                        rs.getString("strasse"),
                        rs.getString("hausnummer"),
                        rs.getInt("plz"),
                        rs.getString("ort"),
                        rs.getString("land")
                        );
            String typ = rs.getString("typ");
            return new AdresseTypDaten(adresse, typ);
        }, kundeId);

        return adressen;
    }

    public Adresse findbyId(int adresseId) {
        String sql = "SELECT * FROM adresse WHERE adresse_id = ?";
        try {
            Adresse adresse = jdbcTemplate.queryForObject(sql, (rs, row) ->
                    new Adresse(
                            rs.getInt("adresse_id"),
                            rs.getBoolean("aktiv"),
                            rs.getString("strasse"),
                            rs.getString("hausnummer"),
                            rs.getInt("plz"),
                            rs.getString("ort"),
                            rs.getString("land")
                    ), adresseId);
            return adresse;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void save(Adresse adresse) {
        String sql = "INSERT INTO adresse (aktiv, strasse, hausnummer, plz, ort, land) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, adresse.isAktiv(), adresse.getStrasse(),adresse.getHausnummer(),
                adresse.getPlz(), adresse.getOrt(), adresse.getLand());
    }
}
