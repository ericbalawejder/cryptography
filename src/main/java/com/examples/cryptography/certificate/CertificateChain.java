package com.examples.cryptography.certificate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://blogs.oracle.com/java-platform-group/diagnosing-tls,-ssl,-and-https
public class CertificateChain {

    public static void main(String[] args) throws IOException,
            CertificateNotYetValidException {

        final URL url = new URL("https://www.stackoverflow.com");
        final HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.connect();
        final Certificate[] certificates = connection.getServerCertificates();

        Arrays.stream(certificates).forEach(CertificateChain::printCertificate);

        System.out.println("There are " + certificates.length + " certificates.");

        Arrays.stream(certificates)
                .map(cert -> (X509Certificate) cert)
                .forEach(x509 -> System.out.println(x509.getIssuerX500Principal().getName()));

        System.out.println("The final certificate is for: " + connection.getPeerPrincipal());
    }

    private static void printCertificate(Certificate certificate) {
        System.out.println("Certificate is: " + certificate);
        if (certificate instanceof X509Certificate) {
            try {
                ((X509Certificate) certificate).checkValidity();
                System.out.println("Certificate is active for current date");
            } catch (CertificateExpiredException e) {
                Logger.getLogger(CertificateChain.class.getName())
                        .log(Level.SEVERE, "Expired", e);
            } catch (CertificateNotYetValidException e) {
                Logger.getLogger(CertificateChain.class.getName())
                        .log(Level.SEVERE, "Not yet valid", e);
            }
        } else {
            System.err.println("Looks like there is a new type of certificate.");
        }
    }

}
