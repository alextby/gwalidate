package com.github.alextby.ui.gwt.gwalidate.test.client.util;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Test Utils
 */
public class TestUtils {

    public static BigDecimal bdEqual(String value) {
        return new BigDecimal(value);
    }

    public static BigDecimal bdLess(String value) {
        return new BigDecimal(value).subtract(BigDecimal.ONE);
    }

    public static BigDecimal bdGreater(String value) {
        return new BigDecimal(value).add(BigDecimal.ONE);
    }

    /**
     * Decrements the given value
     */
    public static long less(long value) {
        return value - 1;
    }

    /**
     * Increments the given value
     */
    public static long greater(long value) {
        return value + 1;
    }

    /**
     * Decrements the given value
     */
    public static int less(int value) {
        return value - 1;
    }

    public static String sLess(int value) {
        return String.valueOf(value - 1);
    }

    /**
     * Increments the given value
     */
    public static int greater(int value) {
        return value + 1;
    }

    public static String sGreater(int value) {
        return String.valueOf(value + 1);
    }

    /**
     * Generates a random string one symbol longer than the given length
     */
    public static String longerString(int maxLength) {
        final char[] generated = new char[maxLength + 1];
        Arrays.fill(generated, 'z');
        return String.valueOf(generated);
    }

    private TestUtils() {
    }
}
