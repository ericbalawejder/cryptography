package com.examples.cryptography.cipher;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AffineCipher {

    private static final int ALPHABET = 26;
    private static final int ASCII_VALUE = 'a';

    public String encode(String text, int keyA, int keyB) {
        modularMultiplicativeInverse(keyA, ALPHABET);

        final String filteredText = text.toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "");

        final List<String> groups = group(filteredText, 5);

        return groups.stream()
                .map(word -> word.chars()
                        .mapToObj(c -> (char) c)
                        .map(c -> encrypt(c, keyA, keyB))
                        .collect(Collector.of(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append,
                                StringBuilder::toString)))
                .collect(Collectors.joining(" "));
    }

    public String decode(String encoding, int keyA, int keyB) {
        return encoding.replaceAll("\\s+", "")
                .chars()
                .mapToObj(c -> (char) c)
                .map(c -> decrypt(c, keyA, keyB))
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    private Character encrypt(Character character, int keyA, int keyB) {
        final int index = character - ASCII_VALUE;
        final int encryptionFunction = Math.floorMod((keyA * index + keyB), ALPHABET);
        return Character.isAlphabetic(character) ?
                (char) (encryptionFunction + ASCII_VALUE) : character;
    }

    private Character decrypt(Character character, int keyA, int keyB) {
        final int inverse = modularMultiplicativeInverse(keyA, ALPHABET);
        final int index = character - ASCII_VALUE;
        final int decryptionFunction = Math.floorMod(inverse * (index - keyB), ALPHABET);
        return Character.isAlphabetic(character) ?
                (char) (decryptionFunction + ASCII_VALUE) : character;
    }

    private int modularMultiplicativeInverse(int n, int modulus) {
        try {
            BigInteger a = new BigInteger(String.valueOf(n));
            BigInteger m = new BigInteger(String.valueOf(modulus));
            return a.modInverse(m).intValue();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }
    }

    private List<String> group(String text, int size) {
        return IntStream.iterate(0, n -> n < text.length(), n -> n + size)
                .mapToObj(n -> text.substring(n, Math.min(text.length(), n + size)))
                .collect(Collectors.toUnmodifiableList());
    }

}
