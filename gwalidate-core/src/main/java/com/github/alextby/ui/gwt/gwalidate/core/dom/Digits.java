package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.rule.DigitsRule;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * DOM-based configurator for {@code DigitsRule}
 */
public class Digits extends Rule {

    private int integer = DigitsRule.UNLIMITED;

    private int fraction = DigitsRule.UNLIMITED;

    @UiConstructor
    public Digits() {
    }

    public Digits(int intDigits, int frDigits) {
        this.integer = intDigits;
        this.fraction = frDigits;
    }

    public int getInt() {
        return integer;
    }

    public void setInt(int integer) {
        this.integer = integer;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
