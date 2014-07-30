package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigIntegerConverter;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ValueBoxBase;

import java.math.BigInteger;

/**
 * Simpliest possible {@code ValueBoxBase} for {@code BigInteger}s.
 */
public class BigIntegerBox extends ValueBoxBase<BigInteger> {

    private static final BigIntegerConverter CONVERTER = new BigIntegerConverter();

    public BigIntegerBox() {
        super(Document.get().createTextInputElement(), CONVERTER, CONVERTER);
    }
}

