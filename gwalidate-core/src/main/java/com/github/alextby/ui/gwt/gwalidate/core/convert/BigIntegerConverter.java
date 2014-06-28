package com.github.alextby.ui.gwt.gwalidate.core.convert;

import java.math.BigInteger;
import java.text.ParseException;

/**
 * Converts {@code BigInteger}s
 */
public class BigIntegerConverter extends TextToNumberConverter<BigInteger> {

    public static final String ID = "gwt_client_validate_convert_BigInteger";

    @Override
    public Class<BigInteger> getTypeClass() {
        return BigInteger.class;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public BigInteger parse(CharSequence text) throws ParseException {
        return BigIntegerParser.instance().parse(text);
    }

    @Override
    public String render(BigInteger object) {
        return BigIntegerRenderer.instance().render(object);
    }
}
