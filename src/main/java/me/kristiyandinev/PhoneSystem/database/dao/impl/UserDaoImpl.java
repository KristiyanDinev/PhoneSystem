package me.kristiyandinev.PhoneSystem.database.dao.impl;

import me.kristiyandinev.PhoneSystem.database.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate _jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        _jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void insert() {

    }

    @Override
    public void select() {

    }
}
