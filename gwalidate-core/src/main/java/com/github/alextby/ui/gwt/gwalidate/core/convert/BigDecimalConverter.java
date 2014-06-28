package com.github.alextby.ui.gwt.gwalidate.core.convert;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Converts {@code BigDecimal}s. Allows setting scale/rounding mode.
 */
public class BigDecimalConverter extends TextToNumberConverter<BigDecimal> {

    public static final String ID = "gwt_client_validate_convert_BigDecimal";

    // rounding is off by default
    private int roundingMode = BigDecimal.ROUND_UNNECESSARY;

    // no scale
    private Integer scale;

    @Override
    public Class<BigDecimal> getTypeClass() {
        return BigDecimal.class;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public BigDecimal parse(CharSequence text) throws ParseException {
        BigDecimal result = BigDecimalParser.instance().parse(text);
        return postParse(result);
    }

    @Override
    public String render(BigDecimal object) {
        return BigDecimalRenderer.instance().render(prepareRender(object));
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * Returns the {@code BigDecimal} rounding mode.
     *
     * @return {@link java.math.BigDecimal#ROUND_UNNECESSARY} by default
     */
    public int getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(int roundingMode) {
        this.roundingMode = roundingMode;
    }

    /**
     * Scales the given {@code BigDecimal} to the current scale and rounding mode
     * (applies if only the scale is non-null).
     *
     * @param bigDecimal - {@code BigDecimal}
     * @return - {@code BigDecimal}
     */
    protected BigDecimal postParse(BigDecimal bigDecimal) {
        if (bigDecimal != null && scale != null) {
            bigDecimal = bigDecimal.setScale(scale, roundingMode);
        }
        return bigDecimal;
    }

    protected BigDecimal prepareRender(BigDecimal bigDecimal) {
        assert bigDecimal != null;
        if (scale != null) {
            bigDecimal = bigDecimal.setScale(scale, roundingMode);
        }
        return bigDecimal;
    }
}
