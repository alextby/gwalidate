package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides automatic implicit converter mapping for the standard input elements.
 */
public class ValueBoxConverters {

    private static final Map<Class<? extends ValueBoxBase<?>>, TextConverter>
            widgetConverters = new HashMap<Class<? extends ValueBoxBase<?>>, TextConverter>();

    @Inject
    public ValueBoxConverters(final ConverterFactory factory) {

        widgetConverters.put(com.google.gwt.user.client.ui.TextBox.class, factory.forString());
        widgetConverters.put(com.google.gwt.user.client.ui.IntegerBox.class, factory.forInteger());
        widgetConverters.put(com.google.gwt.user.client.ui.LongBox.class, factory.forLong());
        widgetConverters.put(com.google.gwt.user.client.ui.DoubleBox.class, factory.forDouble());
    }

    protected void register(Class<? extends ValueBoxBase<?>> clazz, TextConverter converter) {
        widgetConverters.put(clazz, converter);
    }

    public TextConverter<?> forBox(Class<? extends ValueBoxBase<?>> clazzOfWidget) {
        return widgetConverters.get(clazzOfWidget);
    }

    public <T extends ValueBoxBase<?>> TextConverter<?> forBox(T widget) {
        return widgetConverters.get(widget.getClass());
    }
}
