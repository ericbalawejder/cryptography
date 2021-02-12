package com.examples.cryptography.cipher;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Atbash {

    String encode(String text) {
        final String filteredText = text.toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "");

        final List<String> groups = group(filteredText, 5);

        return groups.stream()
                .map(word -> word.chars()
                        .mapToObj(c -> (char) c)
                        .map(this::cipher)
                        .collect(Collector.of(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append,
                                StringBuilder::toString)))
                .collect(Collectors.joining(" "));
    }

    String decode(String encoding) {
        return encoding.replaceAll("\\s+", "")
                .chars()
                .mapToObj(c -> (char) c)
                .map(this::cipher)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    private Character cipher(Character character) {
        return Character.isAlphabetic(character) ?
                (char) ((int) 'z' - ((int) character - 'a')) : character;
    }

    private List<String> group(String text, int size) {
        return IntStream.iterate(0, n -> n < text.length(), n -> n + size)
                .mapToObj(n -> text.substring(n, Math.min(text.length(), n + size)))
                .collect(Collectors.toUnmodifiableList());
    }

}
