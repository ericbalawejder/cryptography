package com.examples.cryptography.hashing;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Argon2Hash {

    public static void main(String[] args) {
        final Argon2 argon2 = Argon2Factory.create();
        final char[] password = "HelloWorld".toCharArray();
        final Instant start = Instant.now();

        try {
            final String hash = argon2.hash(22, 65536, 1, password);
            System.out.println(hash);

            if (argon2.verify(hash, password)) {
                System.out.println("Hash matches password");
            } else {
                System.out.println("Hash does not match password");
            }

            final int iterations = Argon2Helper.findIterations(argon2, 1000, 65536, 1);
            System.out.println(iterations);

        } finally {
            argon2.wipeArray(password);
        }
        final Instant end = Instant.now();
        System.out.printf("Hashing took %s ms%n", ChronoUnit.MILLIS.between(start, end));
    }

}
