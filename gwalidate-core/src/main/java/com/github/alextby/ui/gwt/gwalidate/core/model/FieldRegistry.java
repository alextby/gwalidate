package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Inventory for validatable widgets.
 */
public interface FieldRegistry {

    /**
     * Returns a {@code Validatable} for the given alias (if exists).
     *
     * @param alias - alias
     * @return - {@code Validatable}
     */
    ValidatableWidget byAlias(String alias);
}
