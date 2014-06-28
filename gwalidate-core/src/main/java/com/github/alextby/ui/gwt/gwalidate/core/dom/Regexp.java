package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.uibinder.client.UiConstructor;

/**
 * DOM-based configurator for regexp rules
 */
public class Regexp extends Rule {

    private String pattern;

    @UiConstructor
    public Regexp(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
