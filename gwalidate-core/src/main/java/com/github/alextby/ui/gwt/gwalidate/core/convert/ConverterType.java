package com.github.alextby.ui.gwt.gwalidate.core.convert;

/**
 * Supported Converters
 */
public enum ConverterType {

    STRING(TextToStringConverter.class),

    INTEGER(IntegerConverter.class),

    LONG(LongConverter.class),

    BIG_INTEGER(BigIntegerConverter.class),

    DOUBLE(DoubleConverter.class),

    BIG_DECIMAL(BigDecimalConverter.class);

    private Class<? extends TextConverter> converterClass;

    ConverterType(Class<? extends TextConverter> converterClass) {
        this.converterClass = converterClass;
    }

    public Class<? extends TextConverter> getConverterClass() {
        return converterClass;
    }
}
