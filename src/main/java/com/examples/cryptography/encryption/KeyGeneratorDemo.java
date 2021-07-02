package com.examples.cryptography.encryption;

import com.examples.cryptography.util.Util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;


// KeyGenerator is for symmetric, but lots of people use it for passwords.
public class KeyGeneratorDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        final SecretKey secretKey = keyGenerator.generateKey();

        System.out.println("Hex-encoded Secret key is:\n" +
                Util.bytesToHex(secretKey.getEncoded()));
    }

}
