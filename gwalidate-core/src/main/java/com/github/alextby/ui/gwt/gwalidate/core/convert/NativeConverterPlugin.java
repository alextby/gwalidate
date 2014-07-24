package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * JSNI-based version of {@code ConverterPlugin}
 *
 * @author Alex Tsikhanovich
 */
public class NativeConverterPlugin implements ConverterPlugin {

    @Override
    public void plugIn(ValueBoxBase box, TextConverter converter) {
        nativePlugIn(box, converter);
    }

    /**
     * Hacks visibility of the private fields of the given {@code ValueBoxBase}
     * by injecting the given {@code TextConverter} into the corresponding renderer/parser fields
     *
     * @param box       - {@code ValueBoxBase}
     * @param converter - {@code TextConverter}
     */
    private native void nativePlugIn(ValueBoxBase box, TextConverter converter) /*-{
        if (box && converter) {
            box.@com.google.gwt.user.client.ui.ValueBoxBase::renderer = converter;
            box.@com.google.gwt.user.client.ui.ValueBoxBase::parser = converter;
        }
    }-*/;
}
