package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigDecimalConverter;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ValueBoxBase;

import java.math.BigDecimal;

/**
 * Simpliest possible {@code ValueBoxBase} for {@code BigDecimal}s.
 */
public class BigDecimalBox extends ValueBoxBase<BigDecimal> {

    private static final BigDecimalConverter CONVERTER = new BigDecimalConverter();

    public BigDecimalBox() {
        super(Document.get().createTextInputElement(), CONVERTER, CONVERTER);
    }
}

