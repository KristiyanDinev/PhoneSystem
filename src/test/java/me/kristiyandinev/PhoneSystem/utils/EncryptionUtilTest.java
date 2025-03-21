package me.kristiyandinev.PhoneSystem.utils;


import me.kristiyandinev.PhoneSystem.PhoneSystemApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EncryptionUtilTest {


    @Mock
    private EncryptionUtil encryptionUtil;

    @Test
    public void testEncryption() throws Exception{

        PhoneSystemApplication.logger.info(encryptionUtil.encrypt("Hello"));
    }

    @Test
    public void testDecryption() throws Exception{
        encryptionUtil.decrypt("");
    }
}
