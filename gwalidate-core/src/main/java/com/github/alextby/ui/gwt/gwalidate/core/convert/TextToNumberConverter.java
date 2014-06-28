package com.github.alextby.ui.gwt.gwalidate.core.convert;

/**
 * Base class for all String-to-Number conversions.
 *
 * @param <T> - ? extends Number
 */
public abstract class TextToNumberConverter<T extends Number> extends GenericTextConverter<T> {

    protected TextToNumberConverter() {
    }
}
