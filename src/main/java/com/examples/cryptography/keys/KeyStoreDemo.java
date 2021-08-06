package com.examples.cryptography.keys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

// $ keytool -list -keystore /path/to/file/filename.jks
public class KeyStoreDemo {

    public static void main(String[] args) throws IOException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException, OperatorCreationException {

        final File keyStoreLocation;
        final KeyStore keyStore = KeyStore.getInstance("JKS");

        if (args.length == 0) {
            keyStoreLocation = File.createTempFile("keystore", ".jks");
            keyStore.load(null, "changeit".toCharArray());
        } else {
            keyStoreLocation = new File(args[0]);
            keyStore.load(new FileInputStream(keyStoreLocation), "changeit".toCharArray());
        }

        System.out.println("Stored keystore to " + keyStoreLocation);

        System.out.println("Making a new KeyPair to put in it.");
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        final KeyPair keyPair = keyPairGenerator.generateKeyPair();
        final Certificate certificate = generateCertificate(keyPair);
        final Entry entry = new PrivateKeyEntry(keyPair.getPrivate(),
                new Certificate[]{certificate});

        keyStore.setEntry("eric-cert", entry,
                new KeyStore.PasswordProtection("changeit".toCharArray()));

        keyStore.store(new FileOutputStream(keyStoreLocation),
                "changeit".toCharArray());
    }

    // KeyPairs must be wrapped with Certificates for identification purposes.
    public static Certificate generateCertificate(KeyPair keyPair) throws
            CertificateException, OperatorCreationException {

        // https://docs.oracle.com/cd/B14099_19/idmanage.1012/b15569/oracle/security/crypto/cert/X500Name.html#X500Name_java_lang_String_
        final X500Name x500Name = new X500Name("cn=eric-cert-wrapper");
        final SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo
                .getInstance(keyPair.getPublic().getEncoded());

        final Date start = new Date();
        final Date until = Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC));

        final X509v3CertificateBuilder x509v3CertificateBuilder =
                new X509v3CertificateBuilder(x500Name,
                new BigInteger(160, new SecureRandom()), // has to fit in 20 bytes
                start,
                until,
                x500Name,
                subjectPublicKeyInfo
        );
        final ContentSigner contentSigner =
                new JcaContentSignerBuilder("SHA256WithRSA")
                .setProvider(new BouncyCastleProvider())
                .build(keyPair.getPrivate());

        final X509CertificateHolder x509CertificateHolder =
                x509v3CertificateBuilder.build(contentSigner);

        return new JcaX509CertificateConverter()
                .setProvider(new BouncyCastleProvider())
                .getCertificate(x509CertificateHolder);
    }

}
