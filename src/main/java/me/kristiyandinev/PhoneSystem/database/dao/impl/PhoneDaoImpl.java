package me.kristiyandinev.PhoneSystem.database.dao.impl;

import me.kristiyandinev.PhoneSystem.database.dao.PhoneDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class PhoneDaoImpl implements PhoneDao {

    private JdbcTemplate jdbcTemplate;

    public PhoneDaoImpl(JdbcTemplate _jdbcTemplate) {
        jdbcTemplate = _jdbcTemplate;
    }
}
