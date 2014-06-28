package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Defines a subset of validation context which is visible to the cross field validation rules.
 */
public interface CrossFieldContext {

    /**
     * Is the given {@code ValidatableWidget} valid ?
     *
     * @param target - {@code ValidatableWidget}
     * @return - true or false
     */
    boolean isValid(ValidatableWidget target);

    /**
     * Returns the value for the given {@code ValidatableWidget}.
     * It's either a converted value (if the field has a converter assigned) or null.
     *
     * @param validatable - {@code ValidatableWidget}
     * @return - value
     */
    Object getConverted(ValidatableWidget validatable);

    /**
     * Looks up a {@code ValidatableWidget} by the given string alias
     *
     * @param alias - string alias
     * @return - {@code ValidatableWidget}
     */
    ValidatableWidget byAlias(String alias);
}
