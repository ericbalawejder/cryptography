package com.examples.cryptography.keys;

import com.examples.cryptography.util.Util;
import org.springframework.security.crypto.codec.Hex;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class KeyPairGeneratorDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        final KeyPair keyPair = generateKeyPair();
        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Public key is " + publicKey);
        System.out.println("Our Hex-Encoded public key is:\n" +
                Util.bytesToHex(publicKey.getEncoded(), 21));
        System.out.println("Private key is " + privateKey);
        System.out.println(Hex.encode(publicKey.getEncoded()));
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }

    public static KeyPair generateECKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidAlgorithmParameterException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        final ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("prime192v1");
        keyPairGenerator.initialize(ecGenParameterSpec, secureRandom);

        return keyPairGenerator.generateKeyPair();
    }

}
