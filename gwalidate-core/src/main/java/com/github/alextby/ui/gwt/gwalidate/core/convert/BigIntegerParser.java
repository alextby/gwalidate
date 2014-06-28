package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.gwt.text.shared.Parser;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * Parses {@code BigInteger}s
 */
public class BigIntegerParser implements Parser<BigInteger> {

    private static BigIntegerParser INSTANCE;

    public static Parser<BigInteger> instance() {
        if (INSTANCE == null) {
            INSTANCE = new BigIntegerParser();
        }
        return INSTANCE;
    }

    public BigInteger parse(CharSequence object) throws ParseException {

        if (StringUtils.isBlank(object.toString())) {
            return null;
        }
        try {
            return new BigInteger(object.toString());
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}