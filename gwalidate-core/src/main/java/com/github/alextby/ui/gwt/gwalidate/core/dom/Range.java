package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.rule.RangeRule;

/**
 * Range rule supporting up to 8-byte(long) range boundaries.<br/>
 * Min = 0; Max=Long.MAX_VALUE by default.
 *
 * @see com.github.alextby.ui.gwt.gwalidate.core.dom.Rule
 */
public class Range extends Rule {

    private long min = Long.MIN_VALUE;

    private long max = Long.MAX_VALUE;

    private boolean minOut = RangeRule.DEFAULT_EXCLUSIVE;

    private boolean maxOut = RangeRule.DEFAULT_EXCLUSIVE;

    public long getMin() {
        return min;
    }

    public void setMinS(String strMin) {
        this.min = Long.parseLong(strMin);
    }

    public void setMinL(long min) {
        this.min = min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMaxS(String strMax) {
        this.max = Long.parseLong(strMax);
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMaxL(long max) {
        this.max = max;
    }

    public boolean isExclusive() {
        return minOut && maxOut;
    }

    public void setExclusive(boolean exclusive) {
        this.minOut = exclusive;
        this.maxOut = exclusive;
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

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
