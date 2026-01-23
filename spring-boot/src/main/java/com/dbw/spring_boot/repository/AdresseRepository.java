package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Adresse;
import com.dbw.spring_boot.model.AdresseTypDaten;
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
}
