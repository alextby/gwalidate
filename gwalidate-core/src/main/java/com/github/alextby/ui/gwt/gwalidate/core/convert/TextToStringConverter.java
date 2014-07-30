package com.github.alextby.ui.gwt.gwalidate.core.convert;

import java.text.ParseException;

/**
 * Null-safe String-to-String (translator) converter.<br/>
 * It is not supposed to throw any exceptions at runtime.<br/>
 * While parsing it normalizes empty or null values to an empty string; performs {@link Object#toString()} otherwise;</br>
 * Rendering simply passes the given value next with no extra formatting applied.
 */
public class TextToStringConverter extends GenericTextConverter<String> {

    @Override
    public Class<String> getTypeClass() {
        return String.class;
    }

    @Override
    public String getId() {
        // this is intentional since we never throw exceptions anyway
        return "";
    }

    @Override
    public String parse(CharSequence text) throws ParseException {
        if (text == null || text.length() == 0) {
            return "";
        }
        return text.toString();
    }

    @Override
    public String render(String object) {
        if (object != null && object.length() != 0) {
            return object;
        } else return "";
    }
}
