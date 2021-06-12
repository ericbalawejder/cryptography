package com.examples.cryptography.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimesTest {

    //@Disabled("Remove to run test")
    @Test
    public void testFirstPrime() {
        assertEquals(Primes.findNth(1), 2);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSecondPrime() {
        assertEquals(Primes.findNth(2), 3);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testSixthPrime() {
        assertEquals(Primes.findNth(6), 13);
    }

    //@Disabled("Remove to run test")
    @Test
    public void testBigPrime() {
        assertEquals(Primes.findNth(10001), 104743);
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
    public void findFirstPrime() {
        List<Long> expectedOutput = Collections.singletonList(2L);

        assertEquals(expectedOutput, Primes.sieve(2));
    }

    //@Disabled("Remove to run test")
    @Test
    public void findPrimesUpTo10() {
        List<Long> expectedOutput = Arrays.asList(2L, 3L, 5L, 7L);

        assertEquals(expectedOutput, Primes.sieve(10));
    }

    //@Disabled("Remove to run test")
    @Test
    public void limitIsPrime() {
        List<Long> expectedOutput = Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L);

        assertEquals(expectedOutput, Primes.sieve(13));
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
