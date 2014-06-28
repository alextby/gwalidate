package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.rule.RangeRule;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * Defines a numeric range rule with big-decimal min&max (string-represented)
 */
public class BigRange extends Rule {

    private String min;

    private String max;

    private boolean maxOut = RangeRule.DEFAULT_EXCLUSIVE;

    private boolean minOut = RangeRule.DEFAULT_EXCLUSIVE;

    @UiConstructor
    public BigRange(String min, String max) {
        this.max = max;
        this.min = min;
    }

    public BigRange(int min, int max) {
        this.min = String.valueOf(min);
        this.max = String.valueOf(max);
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public void setExclusive(boolean exclusive) {
        this.minOut = exclusive;
        this.maxOut = exclusive;
    }

    public boolean isExclusive() {
        return minOut && maxOut;
    }

    public void setMaxOut(boolean maxOut) {
        this.maxOut = maxOut;
    }

    public void setMinOut(boolean minOut) {
        this.minOut = minOut;
    }

    public boolean isMaxOut() {
        return maxOut;
    }

    public boolean isMinOut() {
        return minOut;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
