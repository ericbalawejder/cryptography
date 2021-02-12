package com.examples.cryptography.cipher;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationalCipherTest {

    private RotationalCipher rotationalCipher;

    @Test
    public void rotateSingleCharacterBy0() {
        rotationalCipher = new RotationalCipher(0);
        assertEquals("a", rotationalCipher.rotate("a"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateSingleCharacterBy1() {
        rotationalCipher = new RotationalCipher(1);
        assertEquals("b", rotationalCipher.rotate("a"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateSingleCharacterBy26() {
        rotationalCipher = new RotationalCipher(26);
        assertEquals("a", rotationalCipher.rotate("a"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateSingleCharacterBy13() {
        rotationalCipher = new RotationalCipher(13);
        assertEquals("z", rotationalCipher.rotate("m"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateSingleCharacterWithWrapAround() {
        rotationalCipher = new RotationalCipher(13);
        assertEquals("a", rotationalCipher.rotate("n"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateCapitalLetters() {
        rotationalCipher = new RotationalCipher(5);
        assertEquals("TRL", rotationalCipher.rotate("OMG"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateSpaces() {
        rotationalCipher = new RotationalCipher(5);
        assertEquals("T R L", rotationalCipher.rotate("O M G"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateNumbers() {
        rotationalCipher = new RotationalCipher(4);
        assertEquals("Xiwxmrk 1 2 3 xiwxmrk", rotationalCipher.rotate("Testing 1 2 3 testing"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotatePunctuation() {
        rotationalCipher = new RotationalCipher(21);
        assertEquals("Gzo'n zvo, Bmviyhv!", rotationalCipher.rotate("Let's eat, Grandma!"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void rotateAllLetters() {
        rotationalCipher = new RotationalCipher(13);
        assertEquals("The quick brown fox jumps over the lazy dog.",
                rotationalCipher.rotate("Gur dhvpx oebja sbk whzcf bire gur ynml qbt."));
    }

}
