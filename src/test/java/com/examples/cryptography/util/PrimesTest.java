package com.examples.cryptography.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class PrimesTest {

    @Test
    public void testFactorialZero() {
        assertEquals(Primes.factorial(BigInteger.ZERO), BigInteger.ONE);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialOne() {
        assertEquals(Primes.factorial(BigInteger.ONE), BigInteger.ONE);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialNegative() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class,
                        () -> Primes.factorial(new BigInteger("-1")));

        assertThat(expected)
                .hasMessage("input must be >= 0");
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorialFive() {
        assertEquals(Primes.factorial(new BigInteger("5")), new BigInteger("120"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorial19() {
        assertEquals(Primes.factorial(new BigInteger("19")),
                new BigInteger("121645100408832000"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFirstPrime() {
        assertEquals(Primes.findNth(1), 2);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFirstPrimeBigInteger() {
        assertEquals(Primes.findNthPrime(1L), BigInteger.TWO);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSecondPrime() {
        assertEquals(Primes.findNth(2), 3);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSecondPrimeBigInteger() {
        assertEquals(Primes.findNthPrime(2L), new BigInteger("3"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSixthPrime() {
        assertEquals(Primes.findNth(6), 13);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSixthPrimeBigInteger() {
        assertEquals(Primes.findNthPrime(6), new BigInteger("13"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testBigPrime() {
        assertEquals(Primes.findNth(10001), 104743);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testBigPrimeBigInteger() {
        assertEquals(Primes.findNthPrime(10001), new BigInteger("104743"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testUndefinedPrime() {
                assertThrows(IllegalArgumentException.class,
                        () -> Primes.findNth(0));
    }

    //@Disabled("Remove to run test")
    @Test
    public void noPrimesUnder2() {
        List<Long> expectedOutput = Collections.emptyList();

        assertEquals(expectedOutput, Primes.sieve(1));
    }

    //@Disabled("Remove to run test")
    @Test
    public void noPrimesUnder2BigInteger() {
        List<BigInteger> expectedOutput = Collections.emptyList();

        assertEquals(expectedOutput, Primes.sieve(BigInteger.ONE));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findFirstPrime() {
        List<Long> expectedOutput = Collections.singletonList(2L);

        assertEquals(expectedOutput, Primes.sieve(2));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findFirstPrimeBigInteger() {
        List<BigInteger> expectedOutput = Collections.singletonList(BigInteger.TWO);

        assertEquals(expectedOutput, Primes.sieve(BigInteger.TWO));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpTo10() {
        List<Long> expectedOutput = Arrays.asList(2L, 3L, 5L, 7L);

        assertEquals(expectedOutput, Primes.sieve(10));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpToBigIntegerTen() {
        List<BigInteger> expectedOutput = Arrays.asList(BigInteger.TWO,
                new BigInteger("3"), new BigInteger("5"), new BigInteger("7"));

        assertEquals(expectedOutput, Primes.sieve(BigInteger.TEN));
    }

    //@Disabled("Remove to run test")
    @Test
    public void limitIsPrime() {
        List<Long> expectedOutput = Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L);

        assertEquals(expectedOutput, Primes.sieve(13));
    }

    //@Disabled("Remove to run test")
    @Test
    public void limitIsPrimeBigInteger() {
        List<BigInteger> expectedOutput = Arrays.asList(BigInteger.TWO,
                new BigInteger("3"), new BigInteger("5"), new BigInteger("7"),
                new BigInteger("11"), new BigInteger("13"));

        assertEquals(expectedOutput, Primes.sieve(new BigInteger("13")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpTo1000() {
        List<Long> expectedOutput = Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L,
                23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L, 73L, 79L,
                83L, 89L, 97L, 101L, 103L, 107L, 109L, 113L, 127L, 131L, 137L, 139L,
                149L, 151L, 157L, 163L, 167L, 173L, 179L, 181L, 191L, 193L, 197L, 199L,
                211L, 223L, 227L, 229L, 233L, 239L, 241L, 251L, 257L, 263L, 269L, 271L,
                277L, 281L, 283L, 293L, 307L, 311L, 313L, 317L, 331L, 337L, 347L, 349L,
                353L, 359L, 367L, 373L, 379L, 383L, 389L, 397L, 401L, 409L, 419L, 421L,
                431L, 433L, 439L, 443L, 449L, 457L, 461L, 463L, 467L, 479L, 487L, 491L,
                499L, 503L, 509L, 521L, 523L, 541L, 547L, 557L, 563L, 569L, 571L, 577L,
                587L, 593L, 599L, 601L, 607L, 613L, 617L, 619L, 631L, 641L, 643L, 647L,
                653L, 659L, 661L, 673L, 677L, 683L, 691L, 701L, 709L, 719L, 727L, 733L,
                739L, 743L, 751L, 757L, 761L, 769L, 773L, 787L, 797L, 809L, 811L, 821L,
                823L, 827L, 829L, 839L, 853L, 857L, 859L, 863L, 877L, 881L, 883L, 887L,
                907L, 911L, 919L, 929L, 937L, 941L, 947L, 953L, 967L, 971L, 977L, 983L,
                991L, 997L);

        assertEquals(expectedOutput, Primes.sieve(1000));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpToBigInteger1000BigInteger() {
        List<BigInteger> expectedOutput = Arrays.asList(BigInteger.TWO, new BigInteger("3"),
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

        assertEquals(expectedOutput, Primes.sieve(new BigInteger("1000")));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testNoFactors() {
        assertEquals(Collections.emptyList(), Primes.decompose(1L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testPrimeNumber() {
        assertEquals(Collections.singletonList(2L), Primes.decompose(2L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSquareOfAPrime() {
        assertEquals(Arrays.asList(3L, 3L), Primes.decompose(9L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testCubeOfAPrime() {
        assertEquals(Arrays.asList(2L, 2L, 2L), Primes.decompose(8L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testProductOfPrimesAndNonPrimes() {
        assertEquals(Arrays.asList(2L, 2L, 3L), Primes.decompose(12L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testProductOfPrimes() {
        assertEquals(Arrays.asList(5L, 17L, 23L, 461L), Primes.decompose(901255L));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testFactorsIncludingALargePrime() {
        assertEquals(Arrays.asList(11L, 9539L, 894119L), Primes.decompose(93819012551L));
    }

}
