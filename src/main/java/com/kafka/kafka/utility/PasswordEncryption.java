package com.kafka.kafka.utility;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class PasswordEncryption {
    private static final String ALGORITHM = "AES";
    private static final String STATIC_KEY = "1234567890123456"; // 16-byte static key

    // Generate a static secret key from the predefined string
    public static SecretKey getStaticKey() {
        return new SecretKeySpec(STATIC_KEY.getBytes(), ALGORITHM);
    }

    // Encrypt the password
    public static String encrypt(String password, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the password
    public static String decrypt(String encryptedPassword, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }


}
