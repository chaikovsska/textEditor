package com.example.textEditor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Component
public class EncryptionService {

    private static final String ALGO = "AES";
    private final SecretKeySpec keySpec;

    public EncryptionService(@Value("${app.crypto.key}") String secretKey) {
        byte[] key = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 16);
        this.keySpec = new SecretKeySpec(key, ALGO);
    }

    public String encrypt(String plain) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] enc = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(enc);
        } catch (Exception e) {
            throw new RuntimeException("Encrypt error", e);
        }
    }

    public String decrypt(String encoded) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] dec = cipher.doFinal(Base64.getDecoder().decode(encoded));
            return new String(dec, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decrypt error", e);
        }
    }
}


