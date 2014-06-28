package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Validation rule API
 *
 * @param <T> type of the value
 *
 */
public interface ValidationRule<T> {

    void check(T value, Validatable target, RuleContext context) throws RuleException;
}
