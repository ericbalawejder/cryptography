package com.examples.cryptography.file;

import com.examples.cryptography.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptor {
    private static final Logger LOG = Logger.getLogger(FileEncryptor.class.getSimpleName());

    private static final String ALGORITHM = "AES";
    private static final String CIPHER = "AES/CBC/PKCS5PADDING";

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IOException {

        final SecureRandom secureRandom = new SecureRandom();
        final byte[] key = new byte[16];
        secureRandom.nextBytes(key); // 128 bit key
        final byte[] initializationVector = new byte[16];
        secureRandom.nextBytes(initializationVector); // 16 bytes IV
        System.out.println("Random key= " + Util.bytesToHex(key));
        System.out.println("initVector= " + Util.bytesToHex(initializationVector));

        final IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Look for files here
        final Path tempDir = Files.createTempDirectory(
                Paths.get("/Users/ericbalawejder/Development/cryptography/" +
                        "src/main/resources/com/examples/cryptography/file"),
                "cryptography-temp-files");

        final Path encryptedPath = tempDir.resolve("Squidward-encrypted.pdf");
        try (InputStream inputStream = FileEncryptor.class.getResourceAsStream("Squidward.pdf");
             OutputStream outputStream = Files.newOutputStream(encryptedPath);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher) {
             }) {
            final byte[] bytes = new byte[1024];
            for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes)) {
                cipherOutputStream.write(bytes, 0, length);
            }
        } catch (IOException e) {
            LOG.log(Level.INFO, "Unable to encrypt", e);
        }

        LOG.info("Encryption finished, saved at " + encryptedPath);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        final Path decryptedPath = tempDir.resolve("Squidward-decrypted.pdf");
        try (InputStream encryptedData = Files.newInputStream(encryptedPath);
             CipherInputStream decryptStream = new CipherInputStream(encryptedData, cipher);
             OutputStream decryptedOut = Files.newOutputStream(decryptedPath)) {

            final byte[] bytes = new byte[1024];
            for (int length = decryptStream.read(bytes); length != -1; length = decryptStream.read(bytes)) {
                decryptedOut.write(bytes, 0, length);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileEncryptor.class.getName())
                    .log(Level.SEVERE, "Unable to decrypt", ex);
        }
        LOG.info("Decryption complete, open " + decryptedPath);
    }

}
