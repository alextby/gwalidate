package com.github.alextby.ui.gwt.gwalidate.core.dom;

/**
 * Defines a DOM config element for
 * {@code com.radiumone.nextgen.gwt.core.client.ui.client.rule.UrlRule}
 */
public class Url extends Rule {

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
