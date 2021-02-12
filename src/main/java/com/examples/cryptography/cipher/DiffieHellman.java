package com.examples.cryptography.cipher;

import java.math.BigInteger;
import java.security.SecureRandom;

class DiffieHellman {

    BigInteger privateKey(BigInteger prime) {
        return new BigInteger(prime.bitLength(), new SecureRandom())
                .mod(prime.subtract(BigInteger.ONE))
                .add(BigInteger.ONE);
    }

    BigInteger publicKey(BigInteger primeA, BigInteger primeB, BigInteger privateKey) {
        return primeB.modPow(privateKey, primeA);
    }

    BigInteger secret(BigInteger prime, BigInteger publicKey, BigInteger privateKey) {
        return publicKey.modPow(privateKey, prime);
    }

}
