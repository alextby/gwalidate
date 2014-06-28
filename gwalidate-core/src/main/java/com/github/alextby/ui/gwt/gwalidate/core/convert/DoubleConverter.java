package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.client.DoubleParser;
import com.google.gwt.text.client.DoubleRenderer;

import java.text.ParseException;

/**
 * Converts {@code Double}s
 */
public class DoubleConverter extends TextToNumberConverter<Double> {

    public static final String ID = "gwt_client_validate_convert_Double";

    @Override
    public Double parse(CharSequence text) throws ParseException {
        return DoubleParser.instance().parse(text);
    }

    @Override
    public String render(Double object) {
        return DoubleRenderer.instance().render(object);
    }

    @Override
    public Class<Double> getTypeClass() {
        return Double.class;
    }

    @Override
    public String getId() {
        return ID;
    }
}
