package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.client.LongParser;
import com.google.gwt.text.client.LongRenderer;

import java.text.ParseException;

/**
 * Converts {@code Long}s
 */
public class LongConverter extends TextToNumberConverter<Long> {

    public static final String ID = "gwt_client_validate_convert_Long";

    @Override
    public Long parse(CharSequence text) throws ParseException {
        return LongParser.instance().parse(text);
    }

    @Override
    public String render(Long object) {
        return LongRenderer.instance().render(object);
    }

    @Override
    public Class<Long> getTypeClass() {
        return Long.class;
    }

    @Override
    public String getId() {
        return ID;
    }
}
