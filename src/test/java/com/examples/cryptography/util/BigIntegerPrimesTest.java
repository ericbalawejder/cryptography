package com.examples.cryptography.util;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BigIntegerPrimesTest {

    @Test
    public void testFactorialZero() {
        assertEquals(BigIntegerPrimes.factorial(BigInteger.ZERO), BigInteger.ONE);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialOne() {
        assertEquals(BigIntegerPrimes.factorial(BigInteger.ONE), BigInteger.ONE);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialNegative() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class,
                        () -> BigIntegerPrimes.factorial(new BigInteger("-1")));

        assertThat(expected)
                .hasMessage("input must be >= 0");
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialFive() {
        assertEquals(BigIntegerPrimes.factorial(new BigInteger("5")),
                new BigInteger("120"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorial19() {
        assertEquals(BigIntegerPrimes.factorial(new BigInteger("19")),
                new BigInteger("121645100408832000"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFirstPrimeBigInteger() {
        assertEquals(BigIntegerPrimes.findNthPrime(1L), BigInteger.TWO);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSecondPrimeBigInteger() {
        assertEquals(BigIntegerPrimes.findNthPrime(2L), new BigInteger("3"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSixthPrimeBigInteger() {
        assertEquals(BigIntegerPrimes.findNthPrime(6), new BigInteger("13"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testBigPrimeBigInteger() {
        assertEquals(BigIntegerPrimes.findNthPrime(10001), new BigInteger("104743"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void noPrimesUnder2BigInteger() {
        final List<BigInteger> expected = Collections.emptyList();

        assertEquals(expected, BigIntegerPrimes.sieve(BigInteger.ONE));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findFirstPrimeBigInteger() {
        final List<BigInteger> expected = Collections.singletonList(BigInteger.TWO);

        assertEquals(expected, BigIntegerPrimes.sieve(BigInteger.TWO));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpToBigIntegerTen() {
        final List<BigInteger> expected = Arrays.asList(BigInteger.TWO,
                new BigInteger("3"), new BigInteger("5"),
                new BigInteger("7"));

        assertEquals(expected, BigIntegerPrimes.sieve(BigInteger.TEN));
    }

    //@Disabled("Remove to run test")
    @Test
    public void limitIsPrimeBigInteger() {
        final List<BigInteger> expected = Arrays.asList(
                BigInteger.TWO, new BigInteger("3"),
                new BigInteger("5"), new BigInteger("7"),
                new BigInteger("11"), new BigInteger("13")
        );

        assertEquals(expected, BigIntegerPrimes.sieve(new BigInteger("13")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpToBigInteger1000BigInteger() {
        final List<BigInteger> expected = Arrays.asList(BigInteger.TWO, new BigInteger("3"),
                new BigInteger("5"), new BigInteger("7"), new BigInteger("11"),
                new BigInteger("13"), new BigInteger("17"), new BigInteger("19"),
                new BigInteger("23"), new BigInteger("29"), new BigInteger("31"),
                new BigInteger("37"), new BigInteger("41"), new BigInteger("43"),
                new BigInteger("47"), new BigInteger("53"), new BigInteger("59"),
                new BigInteger("61"), new BigInteger("67"), new BigInteger("71"),
                new BigInteger("73"), new BigInteger("79"), new BigInteger("83"),
                new BigInteger("89"), new BigInteger("97"), new BigInteger("101"),
                new BigInteger("103"), new BigInteger("107"), new BigInteger("109"),
                new BigInteger("113"), new BigInteger("127"), new BigInteger("131"),
                new BigInteger("137"), new BigInteger("139"), new BigInteger("149"),
                new BigInteger("151"), new BigInteger("157"), new BigInteger("163"),
                new BigInteger("167"), new BigInteger("173"), new BigInteger("179"),
                new BigInteger("181"), new BigInteger("191"), new BigInteger("193"),
                new BigInteger("197"), new BigInteger("199"), new BigInteger("211"),
                new BigInteger("223"), new BigInteger("227"), new BigInteger("229"),
                new BigInteger("233"), new BigInteger("239"), new BigInteger("241"),
                new BigInteger("251"), new BigInteger("257"), new BigInteger("263"),
                new BigInteger("269"), new BigInteger("271"), new BigInteger("277"),
                new BigInteger("281"), new BigInteger("283"), new BigInteger("293"),
                new BigInteger("307"), new BigInteger("311"), new BigInteger("313"),
                new BigInteger("317"), new BigInteger("331"), new BigInteger("337"),
                new BigInteger("347"), new BigInteger("349"), new BigInteger("353"),
                new BigInteger("359"), new BigInteger("367"), new BigInteger("373"),
                new BigInteger("379"), new BigInteger("383"), new BigInteger("389"),
                new BigInteger("397"), new BigInteger("401"), new BigInteger("409"),
                new BigInteger("419"), new BigInteger("421"), new BigInteger("431"),
                new BigInteger("433"), new BigInteger("439"), new BigInteger("443"),
                new BigInteger("449"), new BigInteger("457"), new BigInteger("461"),
                new BigInteger("463"), new BigInteger("467"), new BigInteger("479"),
                new BigInteger("487"), new BigInteger("491"), new BigInteger("499"),
                new BigInteger("503"), new BigInteger("509"), new BigInteger("521"),
                new BigInteger("523"), new BigInteger("541"), new BigInteger("547"),
                new BigInteger("557"), new BigInteger("563"), new BigInteger("569"),
                new BigInteger("571"), new BigInteger("577"), new BigInteger("587"),
                new BigInteger("593"), new BigInteger("599"), new BigInteger("601"),
                new BigInteger("607"), new BigInteger("613"), new BigInteger("617"),
                new BigInteger("619"), new BigInteger("631"), new BigInteger("641"),
                new BigInteger("643"), new BigInteger("647"), new BigInteger("653"),
                new BigInteger("659"), new BigInteger("661"), new BigInteger("673"),
                new BigInteger("677"), new BigInteger("683"), new BigInteger("691"),
                new BigInteger("701"), new BigInteger("709"), new BigInteger("719"),
                new BigInteger("727"), new BigInteger("733"), new BigInteger("739"),
                new BigInteger("743"), new BigInteger("751"), new BigInteger("757"),
                new BigInteger("761"), new BigInteger("769"), new BigInteger("773"),
                new BigInteger("787"), new BigInteger("797"), new BigInteger("809"),
                new BigInteger("811"), new BigInteger("821"), new BigInteger("823"),
                new BigInteger("827"), new BigInteger("829"), new BigInteger("839"),
                new BigInteger("853"), new BigInteger("857"), new BigInteger("859"),
                new BigInteger("863"), new BigInteger("877"), new BigInteger("881"),
                new BigInteger("883"), new BigInteger("887"), new BigInteger("907"),
                new BigInteger("911"), new BigInteger("919"), new BigInteger("929"),
                new BigInteger("937"), new BigInteger("941"), new BigInteger("947"),
                new BigInteger("953"), new BigInteger("967"), new BigInteger("971"),
                new BigInteger("977"), new BigInteger("983"), new BigInteger("991"),
                new BigInteger("997"));

        assertEquals(expected, BigIntegerPrimes.sieve(new BigInteger("1000")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testNoFactors() {
        final List<BigInteger> expected = Collections.emptyList();

        assertEquals(expected, BigIntegerPrimes.decompose(BigInteger.ONE));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testPrimeNumber() {
        final List<BigInteger> expected = Collections.singletonList(BigInteger.TWO);

        assertEquals(expected, BigIntegerPrimes.decompose(BigInteger.TWO));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSquareOfAPrime() {
        final List<BigInteger> expected = Arrays.asList(new BigInteger("3"),
                new BigInteger("3"));

        assertEquals(expected, BigIntegerPrimes.decompose(new BigInteger("9")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testCubeOfAPrime() {
        final List<BigInteger> expected = Arrays.asList(BigInteger.TWO,
                BigInteger.TWO, BigInteger.TWO);

        assertEquals(expected, BigIntegerPrimes.decompose(new BigInteger("8")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testProductOfPrimesAndNonPrimes() {
        final List<BigInteger> expected = Arrays.asList(BigInteger.TWO,
                BigInteger.TWO, new BigInteger("3"));

        assertEquals(expected, BigIntegerPrimes.decompose(new BigInteger("12")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testProductOfPrimes() {
        final List<BigInteger> expected = Arrays.asList(new BigInteger("5"),
                new BigInteger("17"), new BigInteger("23"),
                new BigInteger("461"));

        assertEquals(expected, BigIntegerPrimes.decompose(new BigInteger("901255")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorsIncludingALargePrime() {
        final List<BigInteger> expected = Arrays.asList(new BigInteger("11"),
                new BigInteger("9539"), new BigInteger("894119"));

        assertEquals(expected, BigIntegerPrimes.decompose(new BigInteger("93819012551")));
    }

}
