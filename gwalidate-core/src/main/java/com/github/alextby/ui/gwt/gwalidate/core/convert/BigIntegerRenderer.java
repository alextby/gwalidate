package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

import java.math.BigInteger;

/**
 * Renders {@code BigInteger}s using the default
 * {@link com.google.gwt.i18n.client.NumberFormat#getDecimalFormat()}.
 */
public class BigIntegerRenderer extends AbstractRenderer<BigInteger> {

    private static BigIntegerRenderer INSTANCE;

    public static Renderer<BigInteger> instance() {
        if (INSTANCE == null) {
            INSTANCE = new BigIntegerRenderer();
        }
        return INSTANCE;
    }

    public String render(BigInteger object) {
        if (object == null) {
            return "";
        }
        return NumberFormat.getDecimalFormat().format(object);
    }
}
