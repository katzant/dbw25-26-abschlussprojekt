package com.dbw.spring_boot.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BestellpositionRepository {

    private final JdbcTemplate jdbcTemplate;

    public BestellpositionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
