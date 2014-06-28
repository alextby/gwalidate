package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.rule.Patterns;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * Defines an email regexp pattern rule
 */
public final class Match extends Rule {

    private Patterns pattern;

    @UiConstructor
    public Match(Patterns pattern) {
        this.pattern = pattern;
    }

    public Patterns getPattern() {
        return pattern;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
