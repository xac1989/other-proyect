package com.cencosud.middleware.profile.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilsTest {

    @Test
    public void getStringValueTest() {
        String result = StringUtils.getStringValue("Hello! world");
        assertNotNull("Result value must not be null", result);
        assertThat("Result value must not be empty", result, not(""));
    }

    @Test
    public void getStringValueWithStringNullTest() {
        String result = StringUtils.getStringValue("null");
        assertNotNull("Result value must not be null", result);
        assertThat("Result value must not be empty", result, is(""));
    }

    @Test
    public void getStringValueNullTest() {
        String result = StringUtils.getStringValue(null);
        assertNotNull("Result value must not be null", result);
        assertThat("Result value must not be empty", result, is(""));
    }

    @Test
    public void isNullTest(){
        Boolean result = StringUtils.isNull("Hello! world");
        assertNotNull("Result value must not be null", result);
        assertTrue("Result must be false", !result);
    }

    @Test
    public void isNullWithStringEmptyTest(){
        Boolean result = StringUtils.isNull("");
        assertNotNull("Result value must not be null", result);
        assertTrue("Result must be false", !result);
    }

    @Test
    public void isNullWithStringNullTest(){
        Boolean result = StringUtils.isNull("null");
        assertNotNull("Result value must not be null", result);
        assertTrue("Result must be true", result);
    }

    @Test
    public void isNullWithNullTest(){
        Boolean result = StringUtils.isNull(null);
        assertNotNull("Result value must not be null", result);
        assertTrue("Result must be true", result);
    }

    @Test
    public void convertToDateWithSlashTest(){
        String dateString = "2017/09/29";
        Date date = StringUtils.convertToDate(dateString);
        assertNotNull("Date must not be null", date);
        String newDate = (new SimpleDateFormat("yyyy/MM/dd")).format(date);
        assertEquals("Dates must be equals", dateString, newDate);
    }

    @Test
    public void convertToDateWithGuionTest(){
        String dateString = "2017-09-29";
        Date date = StringUtils.convertToDate(dateString);
        assertNotNull("Date must not be null", date);
        String newDate = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        assertEquals("Dates must be equals", dateString, newDate);
    }

    @Test
    public void convertToDateWithParseExceptionTest(){
        String dateString = "aaaa-09-29";
        Date date = StringUtils.convertToDate(dateString);
        assertNull("Date must be null", date);
    }

    @Test
    public void convertToDateWithoutMatchTest(){
        String dateString = "2017$09$29";
        Date date = StringUtils.convertToDate(dateString);
        assertNull("Date must be null", date);
    }

    @Test
    public void convertToDateWithNullTest(){
        String dateString = null;
        Date date = StringUtils.convertToDate(dateString);
        assertNull("Date must be null", date);
    }
}
