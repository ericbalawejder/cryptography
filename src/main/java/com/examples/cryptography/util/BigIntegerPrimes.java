package com.examples.cryptography.util;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigIntegerPrimes {

    public static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("input must be >= 0");
        }
        return Stream.iterate(BigInteger.ONE, BigInteger.ONE::add)
                .limit(Integer.parseInt(n.toString()))
                .reduce(BigInteger.ONE, BigInteger::multiply);
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

    public static BigInteger findNthPrime(long nth) {
        return Stream.iterate(BigInteger.TWO, BigInteger.ONE::add)
                .filter(BigIntegerPrimes::isPrime)
                .limit(nth)
                .max(BigInteger::compareTo)
                .orElseThrow(IllegalArgumentException::new);
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
