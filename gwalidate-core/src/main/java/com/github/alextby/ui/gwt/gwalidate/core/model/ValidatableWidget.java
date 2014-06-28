package com.github.alextby.ui.gwt.gwalidate.core.model;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>
 * Marker interface which is scanned by the
 * validation driver in order to detect the validatable widgets.
 * </p>
 *
 * @see Validatable
 * @see ShowsViolations
 */
public interface ValidatableWidget extends IsWidget, Validatable, ShowsViolations {

    /**
     * Returns the widget which sources the validated value
     *
     * @return - {@code Widget}
     */
    Widget getSourceWidget();
}
