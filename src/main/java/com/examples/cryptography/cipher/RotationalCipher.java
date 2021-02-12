package com.examples.cryptography.cipher;

import java.util.stream.Collector;

class RotationalCipher {

    private final int shiftKey;
    private static final int ALPHABET = 26;

    RotationalCipher(int shiftKey) {
        this.shiftKey = shiftKey;
    }

    String rotate(String data) {
        return data.chars()
                .mapToObj(c -> (char) c)
                .map(this::rotateCharacter)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    private Character rotateCharacter(Character character) {
        final int letterCase = Character.isUpperCase(character) ? 'A' : 'a';
        final int rotation = (character + shiftKey - letterCase) % ALPHABET + letterCase;
        return Character.isAlphabetic(character) ? (char) (rotation) : character;
    }

}
