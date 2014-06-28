package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Checks the number of allowed digits for the given value (integer and fraction parts).
 *
 * @see SingleFieldRule
 */
public class DigitsRule extends SingleFieldRule implements ValidationRule<Object> {

    public static final String ID = "gwt_client_validate_Digits";

    public static final String ID_INTEGER = ID + "_Integer";

    public static final String ID_FRACTION = ID + "_Fraction";

    public static final byte UNLIMITED = -1;

    private int integer;

    private int fraction;

    @Inject
    DigitsRule(@Assisted("integer") Integer integer, @Assisted("fraction") Integer fraction) {
        this.integer = integer;
        this.fraction = fraction;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    @Override
    public void check(Object value, Validatable target, RuleContext context) throws RuleException {

        if (value == null) return;

        if (value instanceof String) {
            checkValid(new BigDecimal((String) value).stripTrailingZeros(), context);

        } else if (value instanceof BigDecimal) {
            checkValid((BigDecimal) value, context);

        } else if (value instanceof BigInteger) {
            checkValid(new BigDecimal((BigInteger) value), context);

        } else if (value instanceof Number) {
            checkValid(new BigDecimal(value.toString()).stripTrailingZeros(), context);
        }
    }

    private void checkValid(BigDecimal bigDecimal, RuleContext context) throws RuleException {

        final int intPart = bigDecimal.precision() - bigDecimal.scale();
        final int fractionPart = bigDecimal.scale() < 0 ? 0 : bigDecimal.scale();

        if ((integer > UNLIMITED && intPart > integer) ||
                (fraction > UNLIMITED && fractionPart > fraction)) {

            String msg;
            if (integer <= UNLIMITED) {
                msg = deriveMessage(context.messages(), ID_FRACTION, fraction);
            } else if (fraction <= UNLIMITED) {
                msg = deriveMessage(context.messages(), ID_INTEGER, integer);
            } else {
                msg = deriveMessage(context.messages(), ID, integer, fraction);
            }
            throw new RuleException(msg);
        }
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }
}
