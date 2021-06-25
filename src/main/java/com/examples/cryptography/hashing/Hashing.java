package com.examples.cryptography.hashing;

import com.examples.cryptography.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    // Produce the hash of this compiled class file.
    public static void main(String[] args) throws NoSuchAlgorithmException {

        //final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

        try (InputStream inputStream = Hashing.class.getResourceAsStream(
                Hashing.class.getSimpleName() + ".class")) {

            final byte[] bytes = new byte[1024];

            for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes)) {
                messageDigest.update(bytes, 0, length);
            }
        } catch (IOException ignored) {
        }

        final byte[] hashed = messageDigest.digest();

        System.out.println("The " + messageDigest.getAlgorithm() +
                " value is:\n" + Util.bytesToHex(hashed, 16));
    }

}
