package org.orlounge.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public class Validation {

    private static Pattern emailPattern;
    private static Matcher emailMatcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static {
        emailPattern = Pattern.compile(EMAIL_PATTERN);
    }


    public static boolean validateEmail(final String hex) {

        emailMatcher = emailPattern.matcher(hex);
        return emailMatcher.matches();

    }


}
