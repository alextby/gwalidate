package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.rule.SizeRule;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * A tiny wrapper around {@code Range} setting the min = 0 by default.<br/>
 *
 * @see com.github.alextby.ui.gwt.gwalidate.core.dom.Rule
 */
public class Size extends Range {

    @UiConstructor
    public Size(int max) {
        setMax(max);
        setMin(SizeRule.DEFAULT_MIN);
        setExclusive(SizeRule.DEFAULT_EXCLUSIVE);
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
