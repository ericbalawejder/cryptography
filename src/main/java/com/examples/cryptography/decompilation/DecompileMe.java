package com.examples.cryptography.decompilation;

// Decompile with:
// javap -c -p -l -constants build/classes/java/main/com/examples/cryptography/decompilation/DecompileMe.class
public class DecompileMe {
    private static final String SECRET_PASSWORD = "this is not secret.";

    public static void main(String[] args) {
        final String thisIsNotSecretEither = "right";

        System.out.println("Hello");
    }

}
