package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * DOM configurator for specifying field markers
 */
public class Marker extends SimplePanel implements HasDomConfiguration {

    private String alias;

    @UiConstructor
    public Marker(String alias) {
        this.alias = alias;
        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        // ignore
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }
}
