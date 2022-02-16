package com.examples.cryptography.random;

import java.util.Random;

public class DeconstructJavaUtilRandom {

    // https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Random.java
    public static void main(String[] args) {

        final Random random = new Random();

        // We are going to guess the thirdRandom
        final int firstRandom = random.nextInt();
        final int secondRandom = random.nextInt();

        // These are constants from the java.util.Random source code
        final long multiplier = 0x5DEECE66DL;
        final long addend = 0xBL;

        // 16 -> 48 - 32 = 16
        // java.util.Random generates 48-bit random numbers
        final long startOfSeed = (long) firstRandom << 16;
        long nextSeed = 0;

        // We do not know the last 16-bits, so we try all. 0xFFFF = (2^16 - 1)
        for (int i = 0; i < (int) Math.pow(2, 16) - 1; i++) {
            //  java.util.Random algorithm: (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
            final long nextSeedGuess = ((startOfSeed + i) * multiplier + addend) & ((1L << 48) - 1);
            final long secondRandomGuess = (int) (nextSeedGuess >>> 16);

            if (secondRandomGuess == secondRandom) {
                nextSeed = nextSeedGuess;
                break;
            }
        }
        if (nextSeed != 0) {
            System.out.println("seed found");
            // this algorithm is same as java.util.Random
            nextSeed = (nextSeed * multiplier + addend) & ((1L << 48) - 1);
            final int thirdRandomGuess = (int) (nextSeed >>> 16);
            final int thirdRandom = random.nextInt();
            if (thirdRandomGuess == thirdRandom) {
                System.out.println("guessed third random correctly: " + thirdRandomGuess + " = " + thirdRandom);
            } else {
                System.out.println("wrong third random guess");
            }
        } else {
            System.out.println("seed not found");
        }
    }

}
