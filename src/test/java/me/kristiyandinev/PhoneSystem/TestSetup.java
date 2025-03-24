package me.kristiyandinev.PhoneSystem;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.repos.PhoneRepo;
import me.kristiyandinev.PhoneSystem.repos.UserRepo;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public abstract class TestSetup {

    @Autowired
    public PhoneRepo phoneRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public EncryptionUtil encryptionUtil;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        phoneRepo.deleteAll();
        userRepo.deleteAll();
    }
}
