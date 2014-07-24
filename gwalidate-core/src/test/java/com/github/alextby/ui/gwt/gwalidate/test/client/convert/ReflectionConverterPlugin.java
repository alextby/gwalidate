package com.github.alextby.ui.gwt.gwalidate.test.client.convert;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;

/**
 * Reflection-based text converter plugin
 *
 * @author Alex Tsikhanovich
 */
public class ReflectionConverterPlugin implements ConverterPlugin {

    private static final String FIELD_RENDERER = "renderer";

    private static final String FIELD_PARSER = "parser";

    @Override
    public void plugIn(ValueBoxBase box, TextConverter converter) {

        GwtReflectionUtils.setPrivateFieldValue(box, FIELD_RENDERER, converter);
        GwtReflectionUtils.setPrivateFieldValue(box, FIELD_PARSER, converter);
    }
}
