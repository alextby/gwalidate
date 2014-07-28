package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.model.CategoryManager;
import com.github.alextby.ui.gwt.gwalidate.core.model.CrossFieldContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.FieldRegistry;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.plan.IsPlanStep;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RuleExecutor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validation Context (c) Capt.
 *
 * @see ValidationStatus
 * @see CategoryManager
 * @see CrossFieldContext
 * @see RuleContext
 * @see FieldRegistry
 */
public class ValidationContext
       implements ValidationStatus, CategoryManager, CrossFieldContext, RuleContext, FieldRegistry {

    // by cause grouping
    private final Map<ValidatableWidget, List<Violation>> byCauseViolations;

    // violations
    private final List<Violation> violations;

    // validatable-memento mapping
    private final Map<ValidatableWidget, Memento> mapping;

    // validaiton categories
    private Set<String> activeCategories;

    // validation services
    private final ValidationServices services;

    // validation config
    private final ValidationConfig config;

    // rule executor
    private RuleExecutor ruleExecutor;

    ValidationContext(ValidationConfig config, ValidationServices validationServices) {
        this.config = config;
        mapping = new HashMap<ValidatableWidget, Memento>();
        byCauseViolations = new LinkedHashMap<ValidatableWidget, List<Violation>>();
        violations = new LinkedList<Violation>();
        activeCategories = new HashSet<String>();
        services = validationServices;
    }

    public void reset(boolean hard) {
        clearViolations();
        mapping.clear();
        for (ValidatableWidget widget : config.getValidatables()) {
            mapping.put(widget, new Memento());
        }
        ruleExecutor = new SafeRuleExecutor(this);
        if (hard) {
            clearCategories();
        }
    }

    public void reset() {
        this.reset(false);
    }

    @Override
    public MessagesResolver messages() {
        return services.getMessageResolver();
    }

    @Override
    public ConverterFactory converters() {
        return services.getConverterFactory();
    }

    // -------------------------------------------------------------------------------------
    //  Violation methods
    // -------------------------------------------------------------------------------------

    public void clearViolations() {
        for (ValidatableWidget cause : byCauseViolations.keySet()) {
            cause.putViolations(null);
        }
        byCauseViolations.clear();
        violations.clear();
    }

    public void reportViolation(Violation violation) {
        if (violation == null) {
            throw new IllegalArgumentException();
        }
        acceptViolation(violation);
    }

    Map<ValidatableWidget, List<Violation>> getViolationsGrouped() {
        return Collections.unmodifiableMap(byCauseViolations);
    }

    @Override
    public List<Violation> getViolations() {
        return Collections.unmodifiableList(violations);
    }

    @Override
    public boolean isValid() {
        return violations.size() == 0;
    }

    // -------------------------------------------------------------------------------------
    //  Category Methods
    // -------------------------------------------------------------------------------------

    @Override
    public void clearCategories() {
        this.activeCategories.clear();
    }

    @Override
    public void unsetCategories(String... categories) {
        if (categories != null) {
            this.activeCategories.removeAll(Arrays.asList(categories));
        }
    }

    @Override
    public void setCategories(String... categories) {
        if (categories != null) {
            this.activeCategories.addAll(Arrays.asList(categories));
        }
    }

    @Override
    public Set<String> getCategories() {
        return Collections.unmodifiableSet(activeCategories);
    }

    /**
     * Sets/unsets the given array of categories
     *
     * @param set        - set or unset (true or false)
     * @param categories - array of categories
     */
    @Override
    public void ensureCategories(boolean set, String... categories) {
        if (set) {
            setCategories(categories);
        } else {
            unsetCategories(categories);
        }
    }

    public boolean isValid(ValidatableWidget target) {
        return !byCauseViolations.containsKey(target);
    }

    public boolean isCategoryActive(ValidatableWidget widget) {
        ValidationPlan plan = config.getPlan(widget);
        Set<String> fieldCategories = plan.getCategories();
        return !(!fieldCategories.isEmpty() &&
                Collections.disjoint(fieldCategories, activeCategories));
    }

    public boolean isStepCategoryActive(IsPlanStep planStep) {
        Set<String> fieldCategories = planStep.getCategories();
        return !(!fieldCategories.isEmpty() &&
                Collections.disjoint(fieldCategories, activeCategories));
    }

    void setFinished(ValidatableWidget validatable) {
        mapping.get(validatable).setFinished(true);
    }

    /**
     * Checks whether the given validatable target "is finished":
     * <ul>
     * <li>if it's been explicitly marked as finished</li>
     * <li>or any of the associated rules has failed</li>
     * </ul>
     *
     * @param validatable - validatable widget
     * @return - true or false
     */
    boolean isFinished(ValidatableWidget validatable) {
        return mapping.get(validatable).isFinished() || !isValid(validatable);
    }

    void setConverted(ValidatableWidget validatable, Object convertedValue) {
        mapping.get(validatable).setConvertedValue(convertedValue);
    }

    /**
     * Returns the converted value for the given widget or null if none.
     *
     * @param validatable - {@code ValidatableWidget}
     * @return - converted value
     */
    public Object getConverted(ValidatableWidget validatable) {
        if (validatable == null) throw new IllegalArgumentException();
        Memento memento = mapping.get(validatable);
        return (memento != null && memento.getConvertedValue() != null) ?
                memento.getConvertedValue() : null;
    }

    @Override
    public ValidatableWidget byAlias(String alias) {
        return config.byAlias(alias);
    }

    RuleExecutor getRuleExecutor() {
        return ruleExecutor;
    }

    // -------------------------------------------------------------------------------------
    //  Private Methods
    // -------------------------------------------------------------------------------------

    private void acceptViolation(Violation violation) {

        assert violation != null;

        ValidatableWidget target = violation.getCause();
        if (byCauseViolations.containsKey(target)) {
            byCauseViolations.get(target).add(violation);
        } else {
            List<Violation> newBag = new LinkedList<Violation>();
            newBag.add(violation);
            byCauseViolations.put(target, newBag);
        }

        violations.add(violation);
    }

    /**
     * Per-widget validation state holder
     */
    private class Memento {

        private Object convertedValue;

        private boolean finished;

        void setConvertedValue(Object convertedValue) {
            this.convertedValue = convertedValue;
        }

        public Object getConvertedValue() {
            return convertedValue;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }
    }
}
