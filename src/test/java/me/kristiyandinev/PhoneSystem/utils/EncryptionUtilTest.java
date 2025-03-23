package me.kristiyandinev.PhoneSystem.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptionUtilTest {


    private String testText = "Hello";

    @Autowired
    private EncryptionUtil encryptionUtil;


    @Test
    public void testEncryptionAndDecryption() throws Exception {
        encryptionUtil.decrypt(encryptionUtil.encrypt(testText));
    }
}
