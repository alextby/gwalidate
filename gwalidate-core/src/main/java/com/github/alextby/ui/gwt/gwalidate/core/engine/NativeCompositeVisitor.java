package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * JSNI-based {@code CompositeVisitor}.
 */
public class NativeCompositeVisitor implements CompositeVisitor {

    @Override
    public Widget getWidgetOfComposite(Composite composite) {
        return nativeGetWidgetOfComposite(composite);
    }

    /**
     * Forcedly grabs the top-most widget of a {@code Composite} skipping
     * the visibility rules of {@code Composite#getWidget}.
     *
     * @param composite - {@code Composite}
     * @return - {@code Widget}
     */
    private native Widget nativeGetWidgetOfComposite(Composite composite) /*-{
        return composite.@com.google.gwt.user.client.ui.Composite::getWidget()();
    }-*/;
}
