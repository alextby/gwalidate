package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite visitor for extracting the top-most widget of a {@code Composite}.
 */
public interface CompositeVisitor {

    Widget getWidgeOfComposite(Composite composite);
}
