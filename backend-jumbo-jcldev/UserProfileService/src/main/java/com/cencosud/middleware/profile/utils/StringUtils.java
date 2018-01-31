package com.cencosud.middleware.profile.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
    private static final String SLASH = "/";
    private static final String GUION = "-";
    private static final String NULL = "null";
    public static final String DATE_FORMAT_SLASH = "yyyy/MM/dd";
    public static final String DATE_FORMAT_GUION = "yyyy-MM-dd";

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getStringValue(Object val) {
        LOGGER.debug("getStringValue val::{}", val);
        if (val != null && !NULL.equalsIgnoreCase(val.toString()))
            return val.toString();
        return "";
    }

    public static boolean isNull(String val) {
        LOGGER.debug("isNull val::{}", val);
        return val == null || "null".equals(val) ? true : false;
    }

    public static Date convertToDate(String dateStr) {
        LOGGER.debug("convertToDate dateStr::{}", dateStr);
        try {
            if (isValidDate(dateStr) && dateStr.contains(SLASH))
                return getTimeZoneDefault(SLASH).parse(dateStr);

            if (isValidDate(dateStr) && dateStr.contains(GUION))
                return getTimeZoneDefault(GUION).parse(dateStr);
            
            if (dateStr != null && dateStr.matches("\\d+")) {
            	Calendar calendar = Calendar.getInstance();
            	calendar.setTimeInMillis(Long.parseLong(dateStr));
            	return calendar.getTime();
            }

            return null;
        } catch (ParseException e) {
            LOGGER.warn("Error when parsing Date", e);
            return null;
        }
    }

    public static String convertToString(Date date) {
        String dateStr = null;
        if (date == null)
            return dateStr;
        LOGGER.debug("convertToString date::{}", date);
        dateStr = getTimeZoneDefault(SLASH).format(date);
        return dateStr;
    }

    public static boolean isValidDate(String dateStr) {
        try {
            if (dateStr == null)
                return false;
            if (dateStr.contains(SLASH)) {
                getTimeZoneDefault(SLASH).setLenient(false);
                getTimeZoneDefault(SLASH).parse(dateStr);
            } else {
                getTimeZoneDefault(GUION).setLenient(false);
                getTimeZoneDefault(GUION).parse(dateStr);
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidDate(Date date) {
        String dateStr = convertToString(date);
        if (dateStr == null)
            return false;
        return isValidDate(dateStr);
    }

    public static SimpleDateFormat getTimeZoneDefault(String separatorDate) {
        SimpleDateFormat df = new SimpleDateFormat();
        if (separatorDate != null && SLASH.equals(separatorDate))
            df = new SimpleDateFormat(DATE_FORMAT_SLASH);
        if (separatorDate != null && GUION.equals(separatorDate))
            df = new SimpleDateFormat(DATE_FORMAT_GUION);
        df.setTimeZone(TimeZone.getDefault());
        return df;
    }

}
