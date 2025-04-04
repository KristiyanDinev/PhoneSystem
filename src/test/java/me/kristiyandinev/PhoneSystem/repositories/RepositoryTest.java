package me.kristiyandinev.PhoneSystem.repositories;


import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.TestSetup;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class RepositoryTest extends TestSetup {


    @Test
    public void testUserAndPhone() throws Exception  {
        UserEntity userEntity = new UserEntity(null, "John",
                "ds@example.com",
                encryptionUtil.hash256String("123"), null);

        UserEntity savedUserEntity = userRepository.save(userEntity);
        assertThat(savedUserEntity).isNotNull();

        PhoneEntity phoneEntity = new PhoneEntity("+3591231241", savedUserEntity, null);
        PhoneEntity savedPhoneEntity = phoneRepository.save(phoneEntity);

        assertThat(savedPhoneEntity).isNotNull();
    }
}
