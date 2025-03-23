package me.kristiyandinev.PhoneSystem;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import me.kristiyandinev.PhoneSystem.repos.IPhoneRepo;
import me.kristiyandinev.PhoneSystem.repos.IUserRepo;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhoneSystemApplicationTests {


	@Test
	void contextLoads() {
	}
}
