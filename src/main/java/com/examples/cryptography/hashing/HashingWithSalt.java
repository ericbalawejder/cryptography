package com.examples.cryptography.hashing;

import com.examples.cryptography.util.Util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

// TODO https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
public class HashingWithSalt {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        final String password = "p@ssw0rd";
        final String salt = "usuallyUser@email.com";

        final PBEKeySpec pbeKeySpecWithExplicitSalt = new PBEKeySpec(password.toCharArray(),
                        salt.getBytes(), 32, 256);

        final PBEKeySpec pbeKeySpecWithRandomSalt = new PBEKeySpec(password.toCharArray(),
                        new SecureRandom().generateSeed(64), 64, 512);

        final byte[] hashedWithEmail = secretKeyFactory
                .generateSecret(pbeKeySpecWithExplicitSalt)
                .getEncoded();

        System.out.println("The SHA-" + pbeKeySpecWithExplicitSalt.getKeyLength() +
                " value salted with " + secretKeyFactory.getAlgorithm() +
                " is: \n" + Util.bytesToHex(hashedWithEmail, 16) +
                "\nbase64 encoded: " + new String(Base64.getEncoder().encode(hashedWithEmail)));

        System.out.println();

        final byte[] hashedWithRandomSalt = secretKeyFactory
                .generateSecret(pbeKeySpecWithRandomSalt)
                .getEncoded();

        System.out.println("The SHA-" + pbeKeySpecWithRandomSalt.getKeyLength() +
                " value salted with " + secretKeyFactory.getAlgorithm() +
                " is: \n" + Util.bytesToHex(hashedWithRandomSalt, 16) +
                "\nbase64 encoded: " + new String(Base64.getEncoder().encode(hashedWithRandomSalt)));
    }

}
