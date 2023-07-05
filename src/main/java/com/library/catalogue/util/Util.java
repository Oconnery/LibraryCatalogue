package com.library.catalogue.util;

public class Util {
    public static Integer numberOfDigits(Number number) {
        if (number.equals(0))
            return 1;
        double numberAsDouble = number.doubleValue();
        if (number.doubleValue() < 0)
            numberAsDouble = -numberAsDouble;
        double result = (Math.log10(numberAsDouble) + 1);
        return (int) result;
    }
}
