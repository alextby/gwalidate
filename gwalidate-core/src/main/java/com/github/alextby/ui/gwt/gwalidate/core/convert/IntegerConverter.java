package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.text.client.IntegerRenderer;

import java.text.ParseException;

/**
 * Converts {@code Integer}s
 */
public class IntegerConverter extends TextToNumberConverter<Integer> {

    public static final String ID = "gwt_client_validate_convert_Integer";

    @Override
    public Integer parse(CharSequence text) throws ParseException {
        return IntegerParser.instance().parse(text);
    }

    @Override
    public String render(Integer object) {
        return IntegerRenderer.instance().render(object);
    }

    @Override
    public Class<Integer> getTypeClass() {
        return Integer.class;
    }

    @Override
    public String getId() {
        return ID;
    }
}
