## cryptography
This is a reference of cryptography and security examples from the Java 
[security](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/package-summary.html) 
API to help understand the implementation. The algorithm names can be found in the 
[Standard Names docs](https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html).

* [Hashing](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/hashing)

* [Key, Keypair Generation and KeyStore](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/keys)

  * ```
          Warning:
          The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 
          which is an industry standard format.
      ```

* KeyTool:
  * [Self-signed certificates for a known community](https://blogs.oracle.com/java-platform-group/self-signed-certificates-for-a-known-community)

* [Symmetric and Asymmetric encryption](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/encryption)

* [Certificate thumbprint using Java](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/certificate)
    * Using openssl on command line:
    ```
    $ cat file.crt.pem | openssl x509 -sha1 -fingerprint
    ```

* [Certificate Chain and Pinning](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/certificate)

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

* [Decompliation tools](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/decompilation)
  * Finding keys, IV's and other sensitive data in bytecode.
  * javap, IntelliJ FernFlower

* [File encryption and decryption](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/file)

* [Passwords](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/password)
  * Password generation
  * Password validation
  * Password hashing

* [Ciphers](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/cipher)
  * Affine
  * Atbash
  * Diffie Hellman
  * Rotational

* [Deconstruct Random Number Generation](https://github.com/ericbalawejder/cryptography/tree/main/src/main/java/com/examples/cryptography/random) 
  * [java.util.Random](https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Random.java)
  * Generate two random numbers and then guess the third.

Additional resources:<br>
[RSA keys](https://hackernoon.com/public-key-cryptography-rsa-keys-izda3ylv)

[How to store a salt](https://security.stackexchange.com/questions/17421/how-to-store-salt)

[Argon2-jvm](https://github.com/phxql/argon2-jvm)

[Diagnosing TLS, SSL, and HTTPS](https://blogs.oracle.com/java-platform-group/diagnosing-tls,-ssl,-and-https)

[encryption-and-cryptology](https://security.stackexchange.com/questions/2202/lessons-learned-and-misconceptions-regarding-encryption-and-cryptology)

[Keylength](https://www.keylength.com/) implements mathematical formulas and summarizes reports 
from well-known organizations allowing you to quickly evaluate the minimum security requirements for your system.

SSL Labs.
[SSL Server Test](https://www.ssllabs.com/ssltest/)
