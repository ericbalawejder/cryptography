package com.examples.cryptography.util;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Primes {

    public static List<Long> sieve(long limit) {
        final Set<Long> notPrime = LongStream.rangeClosed(2, (long) Math.sqrt(limit))
                .flatMap(x -> LongStream.iterate(x * x, n -> n <= limit, n -> x + n))
                .boxed()
                .collect(Collectors.toUnmodifiableSet());

        return LongStream.rangeClosed(2, limit)
                .filter(x -> !notPrime.contains(x))
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Long> decompose(long number) {
        final OptionalLong lowestPrime = LongStream.rangeClosed(2, number)
                .filter(i -> number % i == 0)
                .findFirst();

        final List<Long> primeFactors = new ArrayList<>();
        if (lowestPrime.isPresent()) {
            primeFactors.add(lowestPrime.getAsLong());
            primeFactors.addAll(decompose(number / lowestPrime.getAsLong()));
        }
        return List.copyOf(primeFactors);
    }

    public static long findNth(long nth) {
        return LongStream.iterate(2, i -> i + 1)
                .filter(Primes::isPrime)
                .limit(nth)
                .max()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isPrime(long number) {
        if (number <= 1) throw new IllegalArgumentException("Number must be > 1");
        if (number == 2 || number == 3) return true;
        if (number % 2 == 0) return false;
        return LongStream.iterate(3, i -> i <= (long) Math.sqrt(number), i -> i + 2)
                .noneMatch(n -> number % n == 0);
    }

}
