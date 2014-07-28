package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Allows to specify a set of supported categories (comma-separated)
 */
public class Category extends SimplePanel implements HasDomConfiguration {

    private String in;

    @UiConstructor
    public Category(String in) {
        this.in = in;
        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        // ignore
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
