package me.kristiyandinev.PhoneSystem;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.repositories.PhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.UserRepository;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public abstract class TestSetup {

    @Autowired
    public PhoneRepository phoneRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public EncryptionUtil encryptionUtil;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        phoneRepository.deleteAll();
        userRepository.deleteAll();
    }
}
