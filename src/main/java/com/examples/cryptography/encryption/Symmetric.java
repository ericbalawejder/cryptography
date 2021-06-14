package com.examples.cryptography.encryption;

import com.examples.cryptography.util.Util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

// https://dzone.com/articles/jdpr-java-data-protection-recommendations
public class Symmetric {

    // https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html
    private static final String ALGORITHM = "AES";
    private static final String CIPHER = "AES/CBC/PKCS5PADDING";

    public static void main(String[] args) {
        try {
            // Note: Generates a new Key and initVector each time the program runs.
            // In a real implementation you would need to store the key and initVector
            // as secrets to later decrypt.
            final SecureRandom secureRandom = new SecureRandom();

            // AES key sizes: 128, 192, 256
            final byte[] key = new byte[32];
            secureRandom.nextBytes(key); // 256 bit key
            final byte[] initVector = new byte[16];
            secureRandom.nextBytes(initVector); // Must be 16 bytes IV

            System.out.println("Random key=" + Util.bytesToHex(key));
            System.out.println("initVector=" + Util.bytesToHex(initVector));

            final String payload = "As your flight slowly drifts through the " +
                    "sky, the Elves at the Mythical Information Bureau at the " +
                    "North Pole contact you. They'd like some help debugging a " +
                    "malfunctioning experimental energy source aboard one of " +
                    "their super-secret imaging satellites. The experimental " +
                    "energy source is based on cutting-edge technology: a set " +
                    "of Conway Cubes contained in a pocket dimension! When you " +
                    "hear it's having problems, you can't help but agree to take " +
                    "a look.";
            System.out.println("Original text= " + payload);

            final String encrypted = encrypt(key, initVector, payload);
            System.out.println("Encrypted text= " + encrypted);

            final String decrypted = decrypt(key, initVector, encrypted);
            System.out.println("Decrypted text= " + decrypted);

            final String result = decrypted.equals(payload) ? "It works!" : "Something is not right.";
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(byte[] key, byte[] initVector, String value) throws Exception {
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        final byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(byte[] key, byte[] initVector, String encrypted) throws Exception {
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        final byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);
    }

}
