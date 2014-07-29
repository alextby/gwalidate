package com.github.alextby.ui.gwt.gwalidate.core.plan;

import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.RulePriority;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.rule.CrossFieldRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.SingleFieldRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConversionRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConverterRule;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Per-widget validation plan including an alias (optional), a {@code TextConversionRule},
 * [0..N] {@code ValidationRule}s and [0..M] supported categories.
 */
public class ValidationPlan {

    // alias
    private String alias;

    // field-level categories
    private Set<String> categories;

    // plan owner
    private ValidatableWidget target;

    // converterRule
    private TextConversionRule converterRule;

    // set of plan steps
    private Set<IsPlanStep> steps;

    // set of cross-field plan steps
    private Set<IsPlanStep> crossSteps;

    private ValidationPlan(ValidatableWidget target) {

        if (target == null) {
            throw new IllegalArgumentException();
        }
        this.target = target;
        this.crossSteps = new HashSet<IsPlanStep>();

        final Comparator<IsPlanStep> byPriorityCmp = new Comparator<IsPlanStep>() {

            @Override
            public int compare(IsPlanStep o1, IsPlanStep o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                if (o1.getPriority() != o2.getPriority()) {
                    return o1.getPriority().compareTo(o2.getPriority());
                } else {
                    return 1;
                }
            }
        };

        this.steps = new TreeSet<IsPlanStep>(byPriorityCmp);
        this.crossSteps = new LinkedHashSet<IsPlanStep>();
        this.categories = new HashSet<String>();
    }

    public String getAlias() {
        return alias;
    }

    public ValidatableWidget getTarget() {
        return target;
    }

    public TextConversionRule getConverterRule() {
        return converterRule;
    }

    public Set<IsPlanStep> getPlanSteps() {
        return Collections.unmodifiableSet(steps);
    }

    public Set<IsPlanStep> getCrossPlanSteps() {
        return Collections.unmodifiableSet(crossSteps);
    }

    public Set<String> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    /**
     * Builds a brand new {@code ValidationPlan} for the given target
     *
     * @param target - taget
     * @return - plan builder
     */
    public static Builder build(ValidatableWidget target) {
        return new Builder(target);
    }

    /**
     * Extends the given {@code ValidationPlan}
     *
     * @param plan - plan
     * @return - plan builder
     */
    public static Builder extend(ValidationPlan plan) {
        return new Builder(plan);
    }

    /**
     * Merges the given {@code ValidationPlan} with the current plan.
     *
     * @param plan - {@code ValidationPlan}
     * @throws IllegalArgumentException if the targets don't match
     */
    public void merge(ValidationPlan plan) {

        if (plan == null || plan.getTarget() != target) {
            throw new IllegalArgumentException();
        }

        // merge the steps
        steps.addAll(plan.getPlanSteps());
        if (plan.converterRule != null) {
            converterRule = plan.converterRule;
        }

        // merge the categories
        categories.addAll(plan.getCategories());

        // reset the alias (if any)
        if (!StringUtils.isBlank(plan.alias)) {
            alias = plan.alias;
        }
    }

    /**
     * Validation Plan Builder
     */
    public static class Builder {

        private ValidationPlan plan;

        public Builder(ValidationPlan existingPlan) {
            if (existingPlan == null || existingPlan.getTarget() == null) {
                throw new IllegalArgumentException();
            }
            plan = existingPlan;
        }

        public Builder(ValidatableWidget target) {
            if (target == null) {
                throw new IllegalArgumentException();
            }
            plan = new ValidationPlan(target);
        }

        public <T> Builder convert(TextConverter<T> converter) {
            if (converter == null) {
                throw new IllegalArgumentException();
            }
            return convert(new TextConverterRule<T>(converter));
        }

        public Builder convert(TextConversionRule converterRule) {
            if (converterRule == null) {
                throw new IllegalArgumentException();
            }
            plan.converterRule = converterRule;
            return this;
        }

        public Builder rule(SingleFieldRule rule, String... categories) {
            if (rule == null) {
                throw new IllegalArgumentException();
            }
            plan.addPlanStep(
                new PlanStep(
                    rule,
                    new HashSet<String>(Arrays.asList(categories != null ? categories : new String[] { } )),
                    RulePriority.DEFAULT
                )
            );
            return this;
        }

        public Builder unrule() {
            plan.steps.clear();
            return this;
        }

        public Builder crossrule(CrossFieldRule rule, String... categories) {
            if (rule == null) {
                throw new IllegalArgumentException();
            }
            plan.addCrossFieldRule(
                new PlanStep(
                    rule,
                    new HashSet<String>(Arrays.asList(categories != null ? categories : new String[] { } ))
                )
            );
            return this;
        }

        public Builder alias(String alias) {
            plan.alias = alias;
            return this;
        }

        public Builder category(String... list) {
            plan.categories.clear();
            if (list != null) {
                plan.categories.addAll(Arrays.asList(list));
            }
            return this;
        }

        public ValidationPlan done() {
            return plan;
        }
    }

    private void addCrossFieldRule(IsPlanStep crossFieldRule) {
        assert crossFieldRule != null;
        crossSteps.add(crossFieldRule);
    }

    private void addPlanStep(PlanStep step) {
        assert step != null;
        steps.add(step);
    }
}
