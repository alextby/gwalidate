package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite visitor for extracting the top-most widget of a {@code Composite}.
 */
public interface CompositeAdapter {

    /**
     * Extracts the topmost widget of the given {@code Composite}
     * @param composite - composite
     * @return - {@code Widget} of the given {@code Composite}
     */
    Widget getCompositeWidget(Composite composite);
}
