package com.dbw.spring_boot.repository;

import com.dbw.spring_boot.model.Mitarbeiter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MitarbeiterRepository {

    private final JdbcTemplate jdbcTemplate;

    public MitarbeiterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Mitarbeiter> findAll() {
        String sql = "SELECT * FROM mitarbeiter";
        List<Mitarbeiter> mitarbeiter = jdbcTemplate.query(sql, (rs, row) -> new Mitarbeiter(
                rs.getInt("personal_nr"),
                rs.getString("passwort"),
                rs.getString("email"),
                rs.getString("vorname"),
                rs.getString("nachname")
        ));
        return mitarbeiter;
    }

    public void save(Mitarbeiter mitarbeiter) {
        String sql = "INSERT INTO mitarbeiter (vorname, nachname, passwort, email) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, mitarbeiter.getVorname(), mitarbeiter.getNachname(),
                mitarbeiter.getPasswort(), mitarbeiter.getEmail());
    }

    public Mitarbeiter findById(int id) {
        String sql = "SELECT * FROM mitarbeiter WHERE personal_nr = ?";
        try {
            Mitarbeiter mitarbeiter = jdbcTemplate.queryForObject(sql, (rs, row) ->
                    new Mitarbeiter(
                    rs.getInt("personal_nr"),
                    rs.getString("passwort"),
                    rs.getString("email"),
                    rs.getString("vorname"),
                    rs.getString("nachname")
            ), id);
            return mitarbeiter;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM mitarbeiter WHERE personal_nr = ?";
        jdbcTemplate.update(sql, id);
    }
}
