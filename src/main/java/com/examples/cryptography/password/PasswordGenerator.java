package com.examples.cryptography.password;

import com.examples.cryptography.util.Util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Current character set is defined in the ASCII table: https://www.asciitable.com/
 * The special symbols are not contiguous in the number range 33 - 127 and
 * only the range 33 - 48 are explicit in the generator.
 * <p>
 * TODO: Create a policy.
 * <p>
 * private static final String LOWERCASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
 * private static final String UPPERCASE_CHAR = LOWERCASE_CHAR.toUpperCase();
 * private static final String DIGIT = "0123456789";
 * private static final String PUNCTUATION = "!@#&()–[{}]:;',?/*";
 * private static final String SYMBOL = "~$^+=<>";
 * private static final String SPECIAL_CHAR = PUNCTUATION + SYMBOL;
 * <p>
 * private static final String PASSWORD_POLICY =
 * LOWERCASE_CHAR + UPPERCASE_CHAR + DIGIT + SPECIAL_CHAR;
 * <p>
 * Passwords should be stored in char[] but does .toCharArray() leave a copy of the String
 * in memory?
 * <p>
 * Should generateRandomPassword() return char[], List<Character> or String?
 */
public class PasswordGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println(generateRandomPassword(20));

        // Always store a password in char[]?
        final char[] password = generateRandomPassword(32).toCharArray();
        System.out.println(password);

        System.out.println(Util.bytesToHex(hashPassword(generateRandomPassword(32))));
    }

    // 1 upper case, 1 lower case, 2 digits, 2 symbols in ascii range 33 - 48
    private static String generateRandomPassword(int length) {
        if (length < 8) throw new IllegalArgumentException("password must be at least 8 characters");

        final Stream<Character> upperCaseCharacter = new SecureRandom()
                .ints(1, 65, 91)
                .mapToObj(i -> (char) i);

        final Stream<Character> lowerCaseCharacter = new SecureRandom()
                .ints(1, 97, 123)
                .mapToObj(i -> (char) i);

        final Stream<Character> numbers = new SecureRandom()
                .ints(2, 48, 58)
                .mapToObj(i -> (char) i);

        final Stream<Character> symbols = new SecureRandom()
                .ints(2, 33, 48)
                .mapToObj(i -> (char) i);

        final Stream<Character> remaining = new SecureRandom()
                .ints(length - 6, 33, 127)
                .mapToObj(i -> (char) i);

        final List<Character> password =
                Stream.of(upperCaseCharacter, lowerCaseCharacter, numbers, symbols, remaining)
                        .flatMap(i -> i)
                        .collect(Collectors.toList());

        Collections.shuffle(password);

        return password.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static List<Character> generateNoPolicyRandomPassword(int length) {
        return new SecureRandom()
                .ints(length, 33, 127)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toUnmodifiableList());
    }

    private static byte[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final byte[] salt = new SecureRandom().generateSeed(64);

        final PBEKeySpec pbeKeySpecWithRandomSalt =
                new PBEKeySpec(password.toCharArray(), salt, 65536, 512);

        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        return secretKeyFactory.generateSecret(pbeKeySpecWithRandomSalt).getEncoded();
    }

}
