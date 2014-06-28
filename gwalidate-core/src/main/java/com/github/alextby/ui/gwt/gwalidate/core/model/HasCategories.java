package com.github.alextby.ui.gwt.gwalidate.core.model;

import java.util.Set;

/**
 * Read-only categories container.
 */
public interface HasCategories {

    /**
     * Returns the currently set categories
     *
     * @return - categories {@code Set}
     */
    Set<String> getCategories();
}
