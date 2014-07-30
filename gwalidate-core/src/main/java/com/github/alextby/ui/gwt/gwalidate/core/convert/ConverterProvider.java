package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Default converter provider for built-in types.
 */
public class ConverterProvider {

    private static final Map<Class<? extends TextConverter>, TextConverter>
            stateless = new HashMap<Class<? extends TextConverter>, TextConverter>();

    @Inject
    public ConverterProvider(final ConverterFactory factory) {

        stateless.put(TextToStringConverter.class, factory.forString());
        stateless.put(IntegerConverter.class, factory.forInteger());
        stateless.put(LongConverter.class, factory.forLong());
        stateless.put(DoubleConverter.class, factory.forDouble());
        stateless.put(BigIntegerConverter.class, factory.forBigInteger());
        stateless.put(BigDecimalConverter.class, factory.forBigDecimal());
    }

    @SuppressWarnings("unchecked")
    public <T extends TextConverter> T byClazz(Class<T> clazzOfConverter) {
        return (T) stateless.get(clazzOfConverter);
    }
}
