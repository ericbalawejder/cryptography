## cryptography
A collection of cryptography and security examples from the Java 
[security](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/package-summary.html) 
API to help understand the implementation. The algorithm names can be found in the 
[Standard Names docs](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html).

Hashing

Hashing with salt

Keypair generation

KeyStore
```
Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 
which is an industry standard format.
```
KeyTool:
[Self-signed certificates for a known community](https://blogs.oracle.com/java-platform-group/self-signed-certificates-for-a-known-community)

Symmetric encryption

Asymmetric encryption

Certificate [thumbprint](https://github.com/ericbalawejder/cryptography/blob/main/src/main/java/com/examples/cryptography/thumbprint/X509Thumbprint.java) 
using Java.
Using openssl on command line:
```
$ cat file.crt.pem | openssl x509 -sha1 -fingerprint
```

