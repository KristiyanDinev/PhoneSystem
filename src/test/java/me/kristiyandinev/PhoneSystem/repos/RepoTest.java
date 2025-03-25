package me.kristiyandinev.PhoneSystem.repos;


import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.TestSetup;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class RepoTest extends TestSetup {


    @Test
    public void testUserAndPhone() throws Exception  {
        User user = new User(null, "John",
                "ds@example.com",
                encryptionUtil.hash256String("123"), null);

        User savedUser = userRepo.save(user);
        assertThat(savedUser).isNotNull();

        Phone phone = new Phone("+3591231241", savedUser, null);
        Phone savedPhone = phoneRepo.save(phone);

        assertThat(savedPhone).isNotNull();
    }
}
