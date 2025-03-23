package me.kristiyandinev.PhoneSystem.repos;


import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.TestSetup;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class IRepoTest extends TestSetup {

    @Test
    public void testUser() throws Exception {
        System.out.println("testUser");
        User user = new User(null, "John",
                "ds@example.com",
                encryptionUtil.hash256String("123"), null);
        userRepo.save(user);
    }

    @Test
    public void testPhone() {
        System.out.println("Phone");

        User user = new User();
        user.id = 1;
        user.name = "John";
        user.email = "ds@example.com";
        user.password = "jzaICFTJOoUx3C1VrsgHWPHwQ6aeNmUBmUmji8TYj8Q=";
        Phone phone = new Phone("+3591231241", user, null);
        phoneRepo.save(phone);
    }
}
