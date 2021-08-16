package com.examples.cryptography.hashing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hashing {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.println(hashClassFile());
        System.out.println(hashEncodeBase64("hello world"));
        final String path = "/Users/ericbalawejder/Development/cryptography/build/classes/java/main/com/examples/cryptography/hashing/Hashing.class";
        final File file = new File(path);
        System.out.println(hashEncodeFileBase64(file));

        System.out.println(hashClassFile().equals(hashEncodeFileBase64(file)));
    }

    public static String hashEncodeBase64(String clearText) throws NoSuchAlgorithmException {
        final byte[] messageDigest = MessageDigest.getInstance("SHA-256")
                .digest(clearText.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(messageDigest));
    }

    public static String hashEncodeFileBase64(File file) throws NoSuchAlgorithmException, IOException {
        final byte[] data = Files.readAllBytes(file.toPath());
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data);
        return Base64.getEncoder().encodeToString(messageDigest.digest());
    }

    public static String hashClassFile() throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        try (InputStream inputStream = Hashing.class.getResourceAsStream(
                Hashing.class.getSimpleName() + ".class")) {

            final byte[] bytes = new byte[1024];

            for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes)) {
                messageDigest.update(bytes, 0, length);
            }
        } catch (IOException ignored) {
        }
        final byte[] hashed = messageDigest.digest();
        return new String(Base64.getEncoder().encode(hashed));
    }

}
