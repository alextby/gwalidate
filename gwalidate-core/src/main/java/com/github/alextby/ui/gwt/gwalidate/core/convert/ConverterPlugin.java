package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * Plugs-in the given coverter to the given {@code ValueBoxBase}.
 *
 * @author Alex Tsikhanovich
 */
public interface ConverterPlugin {

    public static final String FIELD_RENDERER = "renderer";

    public static final String FIELD_PARSER = "parser";

    /**
     * Plugs given {@code TextConverter} into the given widget
     * @param box - widget
     * @param converter - converter
     */
    void plugIn(ValueBoxBase box, TextConverter converter);
}
