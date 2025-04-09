package me.kristiyandinev.PhoneSystem.repositories;


import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.ISetup;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.database.repositories.IPhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.IUserRepository;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class RepositoryTest implements ISetup {

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Test
    @DisplayName("UserEntity being saved and adding 1 phone number to the user.")
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


    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void setUp() {}

    @Override
    @AfterEach
    public void tearDown() {
        phoneRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }
}
