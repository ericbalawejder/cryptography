package com.examples.cryptography.deepviolet;

import com.mps.deepviolet.api.DVFactory;
import com.mps.deepviolet.api.IDVCipherSuite;
import com.mps.deepviolet.api.IDVEng;
import com.mps.deepviolet.api.IDVSession;

import java.net.URL;
import java.util.HashMap;

/**
 * Code copied from
 * https://github.com/spoofzu/DeepViolet/blob/master/src/main/java/com/mps/deepviolet/api/samples/PrintServerCiphersuites.java
 *
 * Any HTTPS URL can be used, so just change the address and it will demonstrate a different connection.
 */
public class PrintServerCiphersuites {

    public static void main(String[] args) {
        try {
            new PrintServerCiphersuites();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public PrintServerCiphersuites() throws Exception {

        final URL url = new URL("https://github.com/");
        final IDVSession idvSession = DVFactory.initializeSession(url);
        final IDVEng eng = DVFactory.getDVEng(idvSession);
        final IDVCipherSuite[] ciphers = eng.getCipherSuites();
        final HashMap<IDVCipherSuite, IDVCipherSuite> tmap = new HashMap<>();

        // Print out a list of ciphersuites supported by the server.
        System.out.println("Ciphers supported by host " + url.toString());
        for (IDVCipherSuite cipher : ciphers) {
            // If cipher's in the map then skip since we already printed it.  We only want a unique
            // list of ciphers.  API will return ciphers enumerated by handshake protocol (TLS1.0,TLS1.1,etc)
            // Handy if you need to know cipher suite support by protocol.
            if (!tmap.containsKey(cipher)) {
                String buff = cipher.getSuiteName() +
                        '(' +
                        cipher.getStrengthEvaluation() +
                        ',' +
                        cipher.getHandshakeProtocol() +
                        ')';
                System.out.println(buff);
                tmap.put(cipher, cipher);
            }
        }
        System.out.flush();
    }

}
