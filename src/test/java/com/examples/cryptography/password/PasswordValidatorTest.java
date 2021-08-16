package com.examples.cryptography.password;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"cH&1dxj9", "banAna23(", "@rangeYOUbusyt0day"})
    void testPasswordsAreValid(String arg) {
        assertTrue(PasswordValidator.isValid(arg));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @ValueSource(strings = {"ccdfes&1dxj9", "banAna23poikJJ", "rangeYOUbusyt0day",
            "Sh0r+", "sda#3SSaaw vL"})
    void testPasswordsAreNotValid(String arg) {
        assertFalse(PasswordValidator.isValid(arg));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
                "AAAbbbccc@123",
                "Helloworld$123",
                "A!@#&()–a1",               // test punctuation part 1
                "A[{}]:;',?/*a1",           // test punctuation part 2
                "A~$^+=<>a1",               // test symbols
                "0123456789$abcdefgAB",     // test 20 chars
                "123Aa$Aa"                  // test 8 chars
        );
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("validPasswordProvider")
    void test_password_regex_valid(String password) {
        assertTrue(PasswordValidator.isValid(password));
    }

    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "12345678",                 // invalid, only digit
                "abcdefgh",                 // invalid, only lowercase
                "ABCDEFGH",                 // invalid, only uppercase
                "Cb123&^ dfu8I",            // invalid, contains white space
                "abc123$$$",                // invalid, at least one uppercase
                "ABC123$$$",                // invalid, at least one lowercase
                "ABC$$$$$$",                // invalid, at least one digit
                "javaREGEX123",             // invalid, at least one special character
                "javaREGEX123%",            // invalid, % is not in the special character group []
                "________",                 // invalid
                "--------",                 // invalid
                " ",                        // empty space
                "");                        // empty
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        assertFalse(PasswordValidator.isValid(password));
    }

    @Test
    void testPasswordHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String expected = "L1O1lcbZBbk492ZOahzy4l4f4txbg9mvwLRqagRgoFpiCADjk0EHCHxhvnnPTZg8ue4of33+fMQEPBoAcYq2Zw==";

        final char[] password = "He()0World".toCharArray();
        String actual = PasswordGenerator.hashPassword(password, "usuallyUser@email.com");

        assertEquals(expected, actual);
    }

    @Test
    void testBadPasswordHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String expected = "92ZOahzy4l4f4txbg9mvwLRqagRgoFpiCADjk0EHCHxhvnnPTZg8ue4o";

        final char[] password = "He()0World".toCharArray();
        String actual = PasswordGenerator.hashPassword(password, "usuallyUser@email.com");

        assertNotEquals(expected, actual);
    }

}
