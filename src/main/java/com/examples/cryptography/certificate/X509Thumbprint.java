package com.examples.cryptography.certificate;

import com.examples.cryptography.encryption.KeyStoreDemo;
import com.examples.cryptography.util.Util;
import org.bouncycastle.operator.OperatorCreationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

// https://stackoverflow.com/questions/1270703/how-to-retrieve-compute-an-x509-certificates-thumbprint-in-java
// Edit configurations -> Program arguments
// src/main/java/com/examples/cryptography/thumbprint/stackoverflow.crt.pem
public class X509Thumbprint {

    public static void main(String[] args) throws FileNotFoundException,
            CertificateException, NoSuchAlgorithmException, OperatorCreationException {

        // Create a certificate from a KeyPair and generate thumbprint.
        if (args.length == 0) {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            final KeyPair keyPair = keyPairGenerator.generateKeyPair();
            final X509Certificate x509Certificate =
                    (X509Certificate) KeyStoreDemo.generateCertificate(keyPair);
            final String thumbprint = generateThumbprint(x509Certificate);
            System.out.println(thumbprint);
        } else {
            // Generate thumbprint from provided certificate.
            final FileInputStream fileInputStream = new FileInputStream(args[0]);
            final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            final X509Certificate x509Certificate =
                    (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            final String thumbprint = generateThumbprint(x509Certificate);
            System.out.println(thumbprint);
        }
    }

    private static String generateThumbprint(X509Certificate certificate) throws
            NoSuchAlgorithmException, CertificateEncodingException {

        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        final byte[] ans1derEncoding = certificate.getEncoded();
        messageDigest.update(ans1derEncoding);
        final byte[] digest = messageDigest.digest();
        return Util.bytesToHex(digest);
    }

}
