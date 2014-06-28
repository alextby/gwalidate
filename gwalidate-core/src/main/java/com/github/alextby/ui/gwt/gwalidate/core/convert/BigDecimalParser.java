package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.gwt.text.shared.Parser;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Parses {@code BigDecimal}
 */
public class BigDecimalParser implements Parser<BigDecimal> {

    private static BigDecimalParser INSTANCE;

    public static Parser<BigDecimal> instance() {
        if (INSTANCE == null) {
            INSTANCE = new BigDecimalParser();
        }
        return INSTANCE;
    }

    public BigDecimal parse(CharSequence object) throws ParseException {

        if (StringUtils.isBlank(object.toString())) {
            return null;
        }
        try {
            return new BigDecimal(object.toString());
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
