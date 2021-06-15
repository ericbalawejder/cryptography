package com.examples.cryptography.encryption;

import com.examples.cryptography.util.Util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairGeneratorDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair(); //The same as genKeyPair()

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Public key is " + publicKey);

        System.out.println("Our Hex-Encoded public key is:\n" +
                Util.bytesToHexFormatted(publicKey.getEncoded(), 21));

        System.out.println("Private key is " + privateKey);
    }

}
