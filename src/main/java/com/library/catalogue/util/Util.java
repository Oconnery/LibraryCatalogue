package com.library.catalogue.util;

public class Util {
    public static Integer numberOfDigits(Number number) {
        double result = (Math.log10(number.doubleValue()) + 1);
        return (int) result;
    }
}
