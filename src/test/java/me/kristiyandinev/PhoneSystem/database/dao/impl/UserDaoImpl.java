package me.kristiyandinev.PhoneSystem.database.dao.impl;


import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class UserDaoImpl {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDaoImpl userHandler;


    @Test
    public void testUser() {

        //User user = new User(null, "John", "john@example.com", );
        //userHandler
    }
}
