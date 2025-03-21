package me.kristiyandinev.PhoneSystem.utils;


import me.kristiyandinev.PhoneSystem.PhoneSystemApplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {

    private SecretKey _secretKey;
    private String _salt;
    private String _algo = "AES";

    public EncryptionUtil(String key, String salt) throws Exception {
        _secretKey = new SecretKeySpec(hash256(key + salt), _algo);
        _salt = salt;
    }

    public byte[] hash256(String text) throws Exception {
        text += _salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(text.getBytes(StandardCharsets.UTF_8));
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        PhoneSystemApplication.logger.info("IV: "+iv.toString());
        return new IvParameterSpec(iv);
    }

    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(_algo);
        cipher.init(Cipher.ENCRYPT_MODE, _secretKey, generateIv());
        return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String text) throws Exception {

        byte[] wholeBytes = Base64.getDecoder().decode(text);

        byte[] iv = new byte[16];

        byte[] textBytes = new byte[wholeBytes.length - 16];



        System.arraycopy(wholeBytes, 0, iv, 0, iv.length);
        System.arraycopy(wholeBytes, iv.length, textBytes, 0, textBytes.length);

        PhoneSystemApplication.logger.info("IV: "+iv.toString());
        Cipher cipher = Cipher.getInstance(_algo);
        //cipher.init(Cipher.DECRYPT_MODE, _secretKey, new IvParameterSpec(wholeBytes, 0, iv.length));
        cipher.init(Cipher.DECRYPT_MODE, _secretKey, new IvParameterSpec(iv));

        //return new String(cipher.doFinal(wholeBytes, iv.length, wholeBytes.length - iv.length));
        return new String(cipher.doFinal(textBytes));
    }
}
