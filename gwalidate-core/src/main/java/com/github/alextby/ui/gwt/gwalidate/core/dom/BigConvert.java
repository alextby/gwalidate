package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigDecimalConverter;

/**
 * Base number converter with the target type extending {@code Number}
 *
 * @see Convert
 */
public class BigConvert extends Convert {

    private final Class<? extends BigDecimalConverter> type;

    // unset by default (null)
    private Integer roundingMode;

    // unset by default (null)
    private Integer scale;

    public BigConvert(Class<? extends BigDecimalConverter> type) {
        super(type);
        this.type = type;
    }

    @Override
    public Class<? extends BigDecimalConverter> getType() {
        return type;
    }

    public Integer getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(Integer roundingMode) {
        this.roundingMode = roundingMode;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
