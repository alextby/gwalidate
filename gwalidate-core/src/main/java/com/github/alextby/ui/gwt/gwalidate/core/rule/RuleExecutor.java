package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;

/**
 * Rule executor
 */
public interface RuleExecutor {

    /**
     * Executes a {@code CrossFieldRule}
     *
     * @param crossFieldRule - cross field rule
     * @param target         - target
     */
    void execute(CrossFieldRule crossFieldRule, ValidatableWidget target);

    /**
     * Executes a {@code SingleFieldRule}
     *
     * @param singleFieldRule - single field rule
     * @param target          - target
     */
    <T> void execute(ValidationRule<T> singleFieldRule, ValidatableWidget target);

    /**
     * Executes a {@code TextConversionRule}
     *
     * @param converterRule - converter rule
     * @param target        - target
     */
    <T> void execute(TextConversionRule<T> converterRule, ValidatableWidget target);

    /**
     * Executes a {@code RequiredRule}
     *
     * @param requiredRule - required rule
     * @param target       - validatable target
     */
    void execute(RequiredRule requiredRule, ValidatableWidget target);
}
