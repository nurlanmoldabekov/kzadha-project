package com.kzadha.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringRegex {
    private static final Logger logger = LoggerFactory.getLogger(StringRegex.class);
    private static final String ONLY_NUMBERS_SMALL_LETTER = "([a-z0-9_])";

    public static boolean validateUserName(String userName){
        final String res = userName.replaceAll(ONLY_NUMBERS_SMALL_LETTER, "");
        if (res.length() > 0){
            logger.info("login after replacement - {}", res);
            return false;
        }

        return true;
    }
}
