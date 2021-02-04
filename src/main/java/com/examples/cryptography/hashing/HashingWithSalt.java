package com.examples.cryptography.hashing;

import com.examples.cryptography.util.Util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

// TODO https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
public class HashingWithSalt {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html
        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        final String password = "p@ssw0rd";
        final String salt = "usuallyUser@email.com";
        final int iterations = 32;
        final int keyLength = 512;

        final PBEKeySpec pbeKeySpecWithExplicitSalt =
                new PBEKeySpec(password.toCharArray(),
                        salt.getBytes(),
                        iterations,
                        keyLength);


        final PBEKeySpec pbeKeySpecWithRandomSalt =
                new PBEKeySpec(password.toCharArray(),
                        new SecureRandom().generateSeed(64),
                        64,
                        512);

        final byte[] hashedWithEmail = secretKeyFactory
                .generateSecret(pbeKeySpecWithExplicitSalt)
                .getEncoded();

        System.out.println("The SHA-" + pbeKeySpecWithExplicitSalt.getKeyLength() +
                " value salted with " + secretKeyFactory.getAlgorithm() +
                " is: \n" + Util.bytesToHexFormatted(hashedWithEmail, 16));

        final byte[] hashedWithRandomSalt = secretKeyFactory
                .generateSecret(pbeKeySpecWithRandomSalt)
                .getEncoded();

        System.out.println("The SHA-" + pbeKeySpecWithRandomSalt.getKeyLength() +
                " value salted with " + secretKeyFactory.getAlgorithm() +
                " is: \n" + Util.bytesToHexFormatted(hashedWithRandomSalt, 16));
    }

}
