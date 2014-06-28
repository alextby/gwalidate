package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;

/**
 * <p>
 * Allows for verifiying all sorts of "interval" rules.<br/>
 * An "interval" could be a range, size etc; it's defined through
 * setting 2 {@code Number} values (min and max).<br/>
 * Min/Max/Both exclusions are supported (both included by default).
 * </p>
 *
 * @param <T> type of the rule
 * @param <X> type of the interval
 *
 * @see ValidationRule
 */
public abstract class IntervalRule<T, X extends Number> extends SingleFieldRule implements ValidationRule<T> {

    public static final String ID = "gwt_client_validate_Range";

    public static final String EXCLUSIVE = ID + "_1";

    public static final String MIN_INCLUSIVE = ID + "_2";

    public static final String MAX_INCLUSIVE = ID + "_3";

    public static final String INCLUSIVE = ID + "_4";

    public static final boolean DEFAULT_EXCLUSIVE = false;

    private X min;

    private X max;

    private boolean minOut = DEFAULT_EXCLUSIVE;

    private boolean maxOut = DEFAULT_EXCLUSIVE;

    IntervalRule() {
    }

    IntervalRule(X min, X max) {
        this.min = min;
        this.max = max;
    }

    IntervalRule(X min, X max, boolean exclusive) {
        this.min = min;
        this.max = max;
        setExclusive(exclusive);
    }

    public X getMin() {
        return min;
    }

    public void setMin(X min) {
        this.min = min;
    }

    public X getMax() {
        return max;
    }

    public void setMax(X max) {
        this.max = max;
    }

    public boolean isMinOut() {
        return minOut;
    }

    public void setMinOut(boolean minOut) {
        this.minOut = minOut;
    }

    public boolean isMaxOut() {
        return maxOut;
    }

    public void setMaxOut(boolean maxOut) {
        this.maxOut = maxOut;
    }

    public void setExclusive(boolean exclusive) {
        this.minOut = exclusive;
        this.maxOut = exclusive;
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }

    protected String getMessageKey() {

        if (isMinOut() && isMaxOut()) {
            // (min, max)
            return EXCLUSIVE;

        } else if (!isMinOut() && isMaxOut()) {
            // [min, max)
            return MIN_INCLUSIVE;

        } else if (isMinOut()) {
            // (min, max]
            return MAX_INCLUSIVE;

        } else {
            // [min, max]
            return INCLUSIVE;
        }
    }
}
