package com.library.catalogue.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfLong() {
        Long number = Long.MAX_VALUE;
        assertEquals(19, Util.numberOfDigits(number));
    }

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfInteger() {
        Integer number = 0;
        assertEquals(1, Util.numberOfDigits(number));
    }

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfDouble() {
        Double number = 1234567.0;
        assertEquals(7, Util.numberOfDigits(number));
    }

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfFloat() {
        Float number = 1f;
        assertEquals(1, Util.numberOfDigits(number));
    }

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfShort() {
        Short number = -1;
        assertEquals(1, Util.numberOfDigits(number));
    }

    @Test
    void testNumberOfDigitsReturnsCorrectDigitLengthOfByte() {
        Byte number = Byte.MIN_VALUE;
        assertEquals(3, Util.numberOfDigits(number));
    }
}