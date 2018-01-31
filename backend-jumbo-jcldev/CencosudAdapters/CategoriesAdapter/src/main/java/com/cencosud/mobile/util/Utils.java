package com.cencosud.mobile.util;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.springframework.web.util.UriUtils;

public final class Utils {

    private Utils() { }

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    public static String encodePathUTF8(String s) {
        String result = s;
        try {
            result = UriUtils.encodePath(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.warning("ERROR : free text '".concat(s).concat("' no pudo ser codificado"));
        }
        return result;
    }
}
