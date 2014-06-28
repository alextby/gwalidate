package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;

import java.math.BigDecimal;

/**
 * Renders {@code BigDecimal} with the default
 * {@link com.google.gwt.i18n.client.NumberFormat#getDecimalFormat()}.
 */
public class BigDecimalRenderer extends AbstractRenderer<BigDecimal> {

    private static BigDecimalRenderer INSTANCE;

    public static Renderer<BigDecimal> instance() {
        if (INSTANCE == null) {
            INSTANCE = new BigDecimalRenderer();
        }
        return INSTANCE;
    }

    public String render(BigDecimal object) {
        if (object == null) {
            return "";
        }
        return NumberFormat.getDecimalFormat().format(object);
    }
}
