package com.examples.cryptography.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

// TODO BigInteger
public class Primes {

    public static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("input must be >= 0");
        }
        return Stream.iterate(BigInteger.ONE, BigInteger.ONE::add)
                .limit(Integer.parseInt(n.toString()))
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

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

    public static List<BigInteger> sieve(BigInteger limit) {
        final Set<BigInteger> notPrime = Stream.iterate(BigInteger.TWO,
                    i -> i.compareTo(limit.sqrt()) <= 0,
                    BigInteger.ONE::add)
                .flatMap(x -> Stream.iterate(x.multiply(x), n -> n.compareTo(limit) <= 0, x::add))
                .collect(Collectors.toUnmodifiableSet());

        return Stream.iterate(BigInteger.TWO, x -> x.compareTo(limit) <= 0, BigInteger.ONE::add)
                .filter(x -> !notPrime.contains(x))
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

    public static BigInteger findNthPrime(long nth) {
        return Stream.iterate(BigInteger.TWO, BigInteger.ONE::add)
                .filter(Primes::isPrime)
                .limit(nth)
                .max(BigInteger::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isPrime(long number) {
        if (number <= 1) throw new IllegalArgumentException("Number must be > 1");
        if (number == 2 || number == 3) return true;
        if (number % 2 == 0) return false;
        return LongStream.iterate(3, i -> i <= (long) Math.sqrt(number), i -> i + 2)
                .noneMatch(n -> number % n == 0);
    }

    public static boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Number must be > 1");
        } else if (number.equals(BigInteger.TWO) || number.equals(BigInteger.valueOf(3))) {
            return true;
        } else if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        } else {
            return Stream.iterate(BigInteger.valueOf(3),
                        i -> i.compareTo(number.sqrt()) <= 0,
                    BigInteger.TWO::add)
                    .noneMatch(n -> number.mod(n).equals(BigInteger.ZERO));
        }
    }

}
