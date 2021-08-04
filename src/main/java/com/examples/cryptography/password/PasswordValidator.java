package com.examples.cryptography.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *   ^                                 # start of line
 *   (?!.*\\s)                         # negative lookahead, white space
 *   (?=.*[0-9])                       # positive lookahead, digit [0-9]
 *   (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
 *   (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
 *   (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
 *   .                                 # matches anything
 *   {8,128}                           # length at least 8 characters and maximum of 128 characters
 *   $                                 # end of line
 */

public class PasswordValidator {
    private static final String PASSWORD_PATTERN =
            "^(?!.*\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,128}$";

    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(String password) {
        final Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }

}
