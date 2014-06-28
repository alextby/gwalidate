package com.github.alextby.ui.gwt.gwalidate.core.plan;

import com.github.alextby.ui.gwt.gwalidate.core.model.RulePriority;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ExecutableRule;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of the {@code IsPlanStep} interface.
 */
public class PlanStep implements IsPlanStep {

    public static final RulePriority DEFAULT_PRIORITY = RulePriority.DEFAULT;

    private Set<String> categories = new HashSet<String>();

    private ExecutableRule rule;

    private RulePriority priority;

    PlanStep(ExecutableRule rule) {
        this(rule, null, DEFAULT_PRIORITY);
    }

    PlanStep(ExecutableRule rule, Set<String> categories) {
        this(rule, categories, DEFAULT_PRIORITY);
    }

    PlanStep(ExecutableRule rule, Set<String> categories, RulePriority priority) {
        if (rule == null) {
            throw new IllegalArgumentException();
        }
        this.rule = rule;
        this.priority = priority != null ? priority : DEFAULT_PRIORITY;
        if (categories != null) {
            this.categories = new HashSet<String>(categories);
        }
    }

    @Override
    public Set<String> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    @Override
    public RulePriority getPriority() {
        return priority;
    }

    @Override
    public ExecutableRule getRule() {
        return rule;
    }
}
