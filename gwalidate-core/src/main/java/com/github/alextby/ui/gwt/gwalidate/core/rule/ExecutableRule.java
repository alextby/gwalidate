package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;

/**
 * Marks a rule executable with {@code RuleExecutor}.
 */
public interface ExecutableRule {

    /**
     * Executes this {@code ValidationRule} with the given {@code RuleExecutor}
     *
     * @param executor - {@code RuleExecutor}
     * @param target   - {@code ValidatableWidget}
     */
    void execute(RuleExecutor executor, ValidatableWidget target);
}
