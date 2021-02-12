package com.examples.cryptography.cipher;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtbashTest {

    private Atbash atbash;

    @BeforeEach
    public void setup() {
        atbash = new Atbash();
    }

    @Test
    public void testEncodeYes() {
        assertEquals("bvh", atbash.encode("yes"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeNo() {
        assertEquals("ml", atbash.encode("no"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeOmgInCapital() {
        assertEquals("lnt", atbash.encode("OMG"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeOmgWithSpaces() {
        assertEquals("lnt", atbash.encode("O M G"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeMindBlowingly() {
        assertEquals("nrmwy oldrm tob", atbash.encode("mindblowingly"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeNumbers() {
        assertEquals("gvhgr mt123 gvhgr mt", atbash.encode("Testing,1 2 3, testing."));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeDeepThought() {
        assertEquals("gifgs rhurx grlm", atbash.encode("Truth is fiction."));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testEncodeAllTheLetters() {
        assertEquals("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt",
                atbash.encode("The quick brown fox jumps over the lazy dog."));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeExercism() {
        assertEquals("exercism", atbash.decode("vcvix rhn"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeASentence() {
        assertEquals("anobstacleisoftenasteppingstone", atbash.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeNumbers() {
        assertEquals("testing123testing", atbash.decode("gvhgr mt123 gvhgr mt"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeAllTheLetters() {
        assertEquals("thequickbrownfoxjumpsoverthelazydog",
                atbash.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeWithTooManySpaces() {
        assertEquals("exercism", atbash.decode("vc vix    r hn"));
    }

    //@Disabled("Remove to run test")
    @Test
    public void testDecodeWithNoSpaces() {
        assertEquals("anobstacleisoftenasteppingstone",
                atbash.decode("zmlyhgzxovrhlugvmzhgvkkrmthglmv"));
    }

}
