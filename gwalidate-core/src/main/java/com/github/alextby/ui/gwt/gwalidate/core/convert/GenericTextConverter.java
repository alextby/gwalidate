package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.shared.AbstractRenderer;

import java.text.ParseException;

/**
 * Base class for all {@code TextConverter}s for String-to-<T> conversions.
 *
 * @param <T> - parameter type
 */
public abstract class GenericTextConverter<T> extends AbstractRenderer<T> implements TextConverter<T> {

    public static final String ID = "gwt_client_validate_convert_Generic";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public abstract T parse(CharSequence text) throws ParseException;

    @Override
    public String render(T object) {
        return object != null ? object.toString() : "";
    }
}
