package com.examples.cryptography.encryption;

import com.examples.cryptography.util.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

// In this example, Alice is writing a message to herself. Not to Bob.
public class AsymmetricAlice {

    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, SignatureException {

        // Data must not be longer than 190 bytes. Throws IllegalBlockSizeException.
        final String original = "As your flight slowly drifts through the " +
                "sky, the Elves at the Mythical Information Bureau...";

        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair alice = keyPairGenerator.generateKeyPair();

        final String cipherName = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
        final Cipher cipher = Cipher.getInstance(cipherName);
        cipher.init(Cipher.ENCRYPT_MODE, alice.getPublic());

        // Can use cipher inputStream cipher outputStream
        final byte[] originalBytes = original.getBytes(StandardCharsets.UTF_8);
        // cipher.update for long message
        final byte[] cipherTextBytes = cipher.doFinal(originalBytes);

        final Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(alice.getPrivate());
        signature.update(originalBytes);
        final byte[] signatureBytes = signature.sign();

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, alice.getPrivate());
        final byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);
        final String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + Util.bytesToHex(cipherTextBytes));
        System.out.println("Decrypted: " + decryptedString);
        System.out.println();
        if (!decryptedString.equals(original)) {
            throw new IllegalArgumentException("Encrypted and decrypted text do not match");
        }

        System.out.println("Checking signature...");
        signature.initVerify(alice.getPublic());
        signature.update(decryptedBytes);
        final boolean signatureValid = signature.verify(signatureBytes);
        if (signatureValid) {
            System.out.println("Signature checks out. Written by key owner.");
        } else {
            throw new IllegalArgumentException("Signature does not match");
        }
    }

}
