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

public class AsymmetricAliceToBob {

    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, SignatureException {

        final String original = "Welcome to the House of Misery. May I take your order? - Squidward.";
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair alice = keyPairGenerator.generateKeyPair();
        final KeyPair bob = keyPairGenerator.generateKeyPair();

        // Can use other cipher names, like "RSA/ECB/PKCS1Padding"
        final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, bob.getPublic());

        final byte[] originalBytes = original.getBytes(StandardCharsets.UTF_8);
        final byte[] cipherTextBytes = cipher.doFinal(originalBytes);

        final Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(alice.getPrivate());
        signature.update(originalBytes);
        final byte[] signatureBytes = signature.sign();

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, bob.getPrivate());
        final byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);
        final String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + Util.bytesToHex(cipherTextBytes));
        System.out.println("Decrypted: " + decryptedString);

        if (!decryptedString.equals(original)) {
            throw new IllegalArgumentException("Encrypted and decrypted text do not match");
        }

        System.out.println("Checking signature...");
        signature.initVerify(alice.getPublic());
        signature.update(decryptedBytes);

        if (signature.verify(signatureBytes)) {
            System.out.println("Yes, Alice wrote this. Notice where Alice/Bob keys are used.");
        } else {
            throw new IllegalArgumentException("Signature does not match");
        }
    }

}
