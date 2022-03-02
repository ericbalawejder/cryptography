package com.examples.cryptography.hashing;

import org.springframework.security.crypto.codec.Hex;

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
        System.out.println(hashEncodeBase64("hello world"));
        System.out.println(hashClassFile());
        System.out.println(hashEncodeFileBase64(new File("src/test/resources/sample.txt")));
    }

    // Uses Spring Security dependency Hex class
    public static String hashToHex(String clearText) throws NoSuchAlgorithmException {
        final byte[] bytes = MessageDigest.getInstance("SHA-256")
                .digest(clearText.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(bytes));
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
        return new String(Base64.getEncoder().encode(messageDigest.digest()));
    }

    /**
     * Spring Boot MultipartFile type example:
     *
     * public static String encodeFileBase64(MultipartFile multipartFile) throws NoSuchAlgorithmException {
     *     final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
     *     try (InputStream inputStream = multipartFile.getInputStream()) {
     *         final byte[] bytes = new byte[1024];
     *         for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes)) {
     *             messageDigest.update(bytes, 0, length);
     *         }
     *     } catch (IOException ignored) {
     *     }
     *     return Base64.getEncoder().encodeToString(messageDigest.digest());
     * }
     */

}
