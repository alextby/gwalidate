package com.github.alextby.ui.gwt.gwalidate.core.convert;

/**
 * GIN Converter Factory
 */
public interface ConverterFactory {

    /**
     * Constructs {@code TextToStringConverter}
     *
     * @return - {@code TextToStringConverter}
     */
    TextToStringConverter forString();

    /**
     * Constructs {@code IntegerConverter}
     *
     * @return - {@code IntegerConverter}
     */
    IntegerConverter forInteger();

    /**
     * Constructs {@code LongConverter}
     *
     * @return - {@code LongConverter}
     */
    LongConverter forLong();

    /**
     * Constructs {code BigInteger}
     *
     * @return - {@code BigIntegerConverter}
     */
    BigIntegerConverter forBigInteger();

    /**
     * Constructs {@code DoubleConverter}
     *
     * @return - {@code DoubleConverter}
     */
    DoubleConverter forDouble();

    /**
     * Constructs {@code BigDecimalConverter}
     *
     * @return - {@code BigDecimalConverter}
     */
    BigDecimalConverter forBigDecimal();
}
