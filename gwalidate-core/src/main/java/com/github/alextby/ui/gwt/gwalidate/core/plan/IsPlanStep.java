package com.github.alextby.ui.gwt.gwalidate.core.plan;

import com.github.alextby.ui.gwt.gwalidate.core.model.RulePriority;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ExecutableRule;

import java.util.Set;

/**
 * Extends {@code ValidationRule} with categories and weight support.
 */
public interface IsPlanStep {

    /**
     * Returns the wrapped validation rule
     *
     * @return - {@code ValidationRule}
     */
    ExecutableRule getRule();

    /**
     * Returns the supported categories
     *
     * @return - list of supported categories
     */
    Set<String> getCategories();

    /**
     * Returns the rule priority
     *
     * @return - {@code RulePriority}
     */
    RulePriority getPriority();
}
