package me.kristiyandinev.PhoneSystem.repos;

import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockRepoTest {


    @Mock
    public UserRepo userRepo;

    @Test
    public void test() {
        User user = new User(null,
                "1", "2",
                "3", null);


    }
}
