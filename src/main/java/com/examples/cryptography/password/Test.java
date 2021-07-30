package com.examples.cryptography.password;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private static final String LOWERCASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHAR = LOWERCASE_CHAR.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String PUNCTUATION = "!@#&()–[{}]:;',?/*";
    private static final String SYMBOL = "~$^+=<>";
    private static final String SPECIAL_CHAR = PUNCTUATION + SYMBOL;

    private static final String PASSWORD_POLICY =
            LOWERCASE_CHAR + UPPERCASE_CHAR + DIGIT + SPECIAL_CHAR;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) {
        System.out.println(generateStrongPassword(64));

    }

    public static String generateStrongPassword(int length) {
        if (length <= 8) {
            throw new IllegalArgumentException("password length must be greater than 8 characters");
        }
        StringBuilder result = new StringBuilder(length);

        // at least 2 chars (lowercase)
        final String lowercaseString = generateRandomString(LOWERCASE_CHAR, 2);
        System.out.format("%-20s: %s%n", "Chars (Lowercase)", lowercaseString);
        result.append(lowercaseString);

        // at least 2 chars (uppercase)
        final String uppercaseString = generateRandomString(UPPERCASE_CHAR, 2);
        System.out.format("%-20s: %s%n", "Chars (Uppercase)", uppercaseString);
        result.append(uppercaseString);

        // at least 2 digits
        final String digits = generateRandomString(DIGIT, 2);
        System.out.format("%-20s: %s%n", "Digits", digits);
        result.append(digits);

        // at least 2 special characters (punctuation + symbols)
        final String specialChar = generateRandomString(SPECIAL_CHAR, 2);
        System.out.format("%-20s: %s%n", "Special chars", specialChar);
        result.append(specialChar);

        // remaining, just random
        final String randomCharacters = generateRandomString(PASSWORD_POLICY, length - 8);
        System.out.format("%-20s: %s%n", "Others", randomCharacters);
        result.append(randomCharacters);

        String password = result.toString();
        // combine all
        System.out.format("%-20s: %s%n", "Password", password);
        // shuffle again
        System.out.format("%-20s: %s%n", "Final Password", shuffleString(password));
        System.out.format("%-20s: %s%n%n", "Password Length", password.length());

        return password;
    }

    // generate a random char[], based on `input`
    private static String generateRandomString(String input, int size) {

        if (input == null || input.length() <= 0) {
            throw new IllegalArgumentException("Invalid input.");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Invalid size.");
        }
        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // produce a random order
            int index = secureRandom.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }

    private static String shuffleString(String input) {
        final List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        return String.join("", result);
    }

    // use char[]
    private static List<Character> generateNoPolicyRandomPassword(int length) {
        return new SecureRandom()
                .ints(length, 33, 127)
                .mapToObj(i -> (char) i)
                .collect(Collectors.toUnmodifiableList());

    }
}
