package com.examples.cryptography.password;

import com.examples.cryptography.util.Util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Passwords should be stored in char[] but does .toCharArray() leave a copy of the String
 * in memory?
 *
 * Should generateRandomPassword() return char[], List<Character> or String?
 */
public class PasswordGenerator {

    private static final String LOWERCASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHAR = LOWERCASE_CHAR.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String PUNCTUATION = "!@#&()–[{}]:;',?/*";
    private static final String SYMBOL = "~$^+=<>";
    private static final String SPECIAL_CHAR = PUNCTUATION + SYMBOL;

    private static final String PASSWORD_POLICY =
            LOWERCASE_CHAR + UPPERCASE_CHAR + DIGIT + SPECIAL_CHAR;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Always store a password in char[]?
        final char[] password = generateRandomPassword(32).toCharArray();
        System.out.println(password);

        System.out.println(Util.bytesToHex(hashPassword(password)));
    }

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("password length must be greater than 8 characters");
        }

        final String password = generateRandomString(LOWERCASE_CHAR, 2) +
                generateRandomString(UPPERCASE_CHAR, 2) +
                generateRandomString(DIGIT, 2) +
                generateRandomString(SPECIAL_CHAR, 2) +
                generateRandomString(PASSWORD_POLICY, length - 8);

        return shuffleString(password);
    }

    private static String generateRandomString(String alphabet, int size) {
        if (alphabet == null || alphabet.length() <= 0) throw new IllegalArgumentException("Invalid alphabet.");
        if (size < 0) throw new IllegalArgumentException("Invalid size.");

        return IntStream.range(0, size)
                .map(i -> secureRandom.nextInt(alphabet.length()))
                .mapToObj(alphabet::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String shuffleString(String input) {
        final List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        return String.join("", result);
    }

    private static List<Character> generateNoPolicyRandomPassword(int length) {
        return new SecureRandom()
                .ints(length, 33, 127)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toUnmodifiableList());
    }

    private static byte[] hashPassword(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final byte[] salt = new SecureRandom().generateSeed(64);

        final PBEKeySpec pbeKeySpecWithRandomSalt =
                new PBEKeySpec(password, salt, 65536, 512);

        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        return secretKeyFactory.generateSecret(pbeKeySpecWithRandomSalt).getEncoded();
    }

}
