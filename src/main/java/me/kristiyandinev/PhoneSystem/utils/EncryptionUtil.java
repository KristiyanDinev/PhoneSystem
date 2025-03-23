package me.kristiyandinev.PhoneSystem.utils;

import me.kristiyandinev.PhoneSystem.PhoneSystemApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private SecretKey _secretKey;

    @Value("${encryption.salt}")
    private String _salt;

    @Value("${encryption.key}")
    private String _key;

    private final String _algo = "AES/CBC/PKCS5Padding";
    private final String _algoName = "AES";

    public EncryptionUtil() {
        try {
            _secretKey = new SecretKeySpec(hash256(_key + _salt), _algoName);

        } catch (Exception _) {
            _secretKey = null;
        }
    }

    public byte[] hash256(String text) throws Exception {
        text += _salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(text.getBytes(StandardCharsets.UTF_8));
    }

    public String hash256String(String text) throws Exception {
        text += _salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digest.digest(text.getBytes(StandardCharsets.UTF_8)));
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static IvParameterSpec readIV(final int ivSizeBytes, final InputStream is) throws Exception {
        final byte[] iv = new byte[ivSizeBytes];
        int offset = 0;
        while (offset < ivSizeBytes) {
            final int read = is.read(iv, offset, ivSizeBytes - offset);
            if (read == -1) {
                throw new Exception("Too few bytes for IV in input stream");
            }
            offset += read;
        }
        return new IvParameterSpec(iv);
    }

    private static byte[] readFromCipherUsingStream(int wholeBytesLength,
                                                int blockSize,
                                                ByteArrayInputStream bais,
                                                Cipher cipher) throws Exception {
        final byte[] decrypted;
        final byte[] buf = new byte[wholeBytesLength - blockSize];
        try (final CipherInputStream cis = new CipherInputStream(bais, cipher);
             final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int read;
            while ((read = cis.read(buf)) != -1) {
                baos.write(buf, 0, read);
            }
            decrypted = baos.toByteArray();
        }
        return decrypted;
    }

    private static byte[] writeIV_and_TextToCipherStream(ByteArrayOutputStream baos,
                                                        IvParameterSpec iv,
                                                        String text,
                                                        Cipher cipher) throws Exception {
        baos.write(iv.getIV());

        try (final CipherOutputStream cos = new CipherOutputStream(baos, cipher)) {
            cos.write(text.getBytes(StandardCharsets.UTF_8));
        }

        return baos.toByteArray();
    }

    public String encrypt(String text) throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Cipher cipher = Cipher.getInstance(_algo);

        IvParameterSpec iv = generateIv();
        cipher.init(Cipher.ENCRYPT_MODE, _secretKey, iv);

        return Base64.getEncoder().encodeToString(
                writeIV_and_TextToCipherStream(baos, iv, text, cipher));
    }

    public String decrypt(String text) throws Exception {
        final byte[] wholeBytes = Base64.getDecoder().decode(text);
        final ByteArrayInputStream bais = new ByteArrayInputStream(wholeBytes);

        Cipher cipher = Cipher.getInstance(_algo);

        cipher.init(Cipher.DECRYPT_MODE, _secretKey, readIV(cipher.getBlockSize(), bais));

        return new String(readFromCipherUsingStream(wholeBytes.length,
                cipher.getBlockSize(), bais, cipher), StandardCharsets.UTF_8);
    }
}
