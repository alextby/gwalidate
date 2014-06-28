package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Marks a widget as generally consumable by {@code DomPlanScanner}
 */
public interface HasDomConfiguration extends IsWidget {

    /**
     * @param planBuilder - {@code DomPlanScanner}
     */
    void read(DomPlanScanner planBuilder);
}
