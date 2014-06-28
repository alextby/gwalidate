package com.github.alextby.ui.gwt.gwalidate.core.convert;

import java.text.ParseException;

/**
 * Extended {@code ParseException} that holds full conversion error messages.<br/>
 * Is typically thrown by converters wishing to provide their own messages to the user
 * (instead of relying on the defaults).
 */
public class ConverterException extends ParseException {

    public ConverterException(String s) {
        super(s, 0);
    }

    public ConverterException() {
        super("", 0);
    }
}
