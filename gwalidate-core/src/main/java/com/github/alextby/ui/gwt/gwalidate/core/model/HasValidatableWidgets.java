package com.github.alextby.ui.gwt.gwalidate.core.model;

import java.util.Set;

/**
 * Marks element as a container for {@code ValidatableWidget}s.
 */
public interface HasValidatableWidgets {

    /**
     * Returns a set of dependant validatable widgets
     *
     * @return - {@code Set} of {@code ValidatableWidget}
     */
    Set<ValidatableWidget> getValidatables();
}
