package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * JSNI-based {@code CompositeAdapter}.
 */
public class NativeCompositeAdapter implements CompositeAdapter {

    @Override
    public Widget getCompositeWidget(Composite composite) {
        return nativeGetCompositeWidget(composite);
    }

    /**
     * Forcedly grabs the top-most widget of a {@code Composite} skipping
     * the visibility rules of {@code Composite#getWidget}.
     *
     * @param composite - {@code Composite}
     * @return - {@code Widget}
     */
    private native Widget nativeGetCompositeWidget(Composite composite) /*-{
        return composite.@com.google.gwt.user.client.ui.Composite::getWidget()();
    }-*/;
}
