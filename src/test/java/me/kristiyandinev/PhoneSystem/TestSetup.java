package me.kristiyandinev.PhoneSystem;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import me.kristiyandinev.PhoneSystem.repos.IPhoneRepo;
import me.kristiyandinev.PhoneSystem.repos.IUserRepo;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public abstract class TestSetup {

    @Autowired
    public IPhoneRepo phoneRepo;

    @Autowired
    public IUserRepo userRepo;

    @Autowired
    public EncryptionUtil encryptionUtil;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("SetUp");
        User user = new User(null, "John",
                "ds@example.com",
                encryptionUtil.hash256String("123"), null);

        userRepo.save(user);
        user.id = 1;
        Phone phone = new Phone("0878661224", user, null);
        phoneRepo.save(phone);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("TearDown");
        //userRepo.deleteAll();
        //phoneRepo.deleteAll();
    }
}
