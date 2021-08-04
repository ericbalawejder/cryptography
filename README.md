## cryptography
A collection of cryptography and security examples from the Java 
[security](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/package-summary.html) 
API to help understand the implementation. The algorithm names can be found in the 
[Standard Names docs](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html).

* Hashing

* Hashing with salt

* Key Generator

* Keypair Generator

* KeyStore
* ```
        Warning:
        The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 
        which is an industry standard format.
    ```

* KeyTool:
  * [Self-signed certificates for a known community](https://blogs.oracle.com/java-platform-group/self-signed-certificates-for-a-known-community)

* Symmetric encryption

* Asymmetric encryption

* Certificate thumbprint using Java
    * Using openssl on command line:
    ```
    $ cat file.crt.pem | openssl x509 -sha1 -fingerprint
    ```

* Certificate chain

* [Certificate Pinning](https://docs.broadcom.com/doc/certificate-pinning-en)

    * https://stackoverflow.com/questions/62823792/how-to-get-crt-and-key-from-cert-pem-and-key-pem

    * The following command will create the keystore needed for Certificate pinning:
    ```
    $ keytool -keystore keystore -importcert -file stackoverflow.crt -alias stackoverflow -storepass changeit -noprompt
    ```
    Delete the certificate with:
    ```
    $ keytool -keystore keystore -delete -alias stackoverflow -storepass changeit -noprompt
    ```

* Decompliation tools
  * Finding keys, IV's and other sensitive data in bytecode.
  * javap, IntelliJ FernFlower

* File encryption and decryption

* Passwords
  * Password generation
  * Password validation
  * Password hashing

* Ciphers
  * Affine
  * Atbash
  * Diffie Hellman
  * Rotational

* Deconstruct [java.util.Random](https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Random.java)
  * Generate two random numbers and then guess the third.

Additional resources:<br>
[Diagnosing TLS, SSL, and HTTPS](https://blogs.oracle.com/java-platform-group/diagnosing-tls,-ssl,-and-https)

[encryption-and-cryptology](https://security.stackexchange.com/questions/2202/lessons-learned-and-misconceptions-regarding-encryption-and-cryptology)

[Keylength](https://www.keylength.com/) implements mathematical formulas and summarizes reports 
from well-known organizations allowing you to quickly evaluate the minimum security requirements for your system.

SSL Labs.
[SSL Server Test](https://www.ssllabs.com/ssltest/)
