package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.model.FieldRegistry;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Defines an immutable static validation configuration.<br/>
 * The configuration typically consists of a number of {@code ValidatableWidget}s
 * with {@code ValidationPlan}s.<br/>
 * Implements {@code FieldRegistry} contract for the fast validatable lookup.
 *
 * @see FieldRegistry
 */
public class ValidationConfig implements FieldRegistry {

    // validatables
    private LinkedHashMap<ValidatableWidget, ValidationPlan> validatables;

    // those having delegate
    private Set<HasValidatorDelegate> withDelegates;

    // dom-configured
    private Set<ValidatableWidget> domScanned;

    // inner validators
    private Set<Validator> validators;

    // by-alias index
    private Map<String, ValidatableWidget> fieldsByAlias;

    public ValidationConfig() {
        validatables = new LinkedHashMap<ValidatableWidget, ValidationPlan>();
        validators = new HashSet<Validator>();
        fieldsByAlias = new HashMap<String, ValidatableWidget>();
        domScanned = new HashSet<ValidatableWidget>();
        withDelegates = new HashSet<HasValidatorDelegate>();
    }

    public LinkedList<ValidatableWidget> getValidatables() {
        return new LinkedList<ValidatableWidget>(validatables.keySet());
    }

    public boolean isValidated(ValidatableWidget widget) {
        return validatables.containsKey(widget);
    }

    public ValidationPlan getPlan(ValidatableWidget target) {
        return validatables.get(target);
    }

    public boolean isDomConfigured(ValidatableWidget validatee) {
        return domScanned.contains(validatee);
    }

    void injectDelegate(ValidatorDelegate delegate, HasValidatorDelegate withDelegate) {
        if (!withDelegates.contains(withDelegate)) {
            // DebugUtils.trace("Validator: ValidatorDelegate injected");
            withDelegate.setValidatorDelegate(delegate);
            withDelegates.add(withDelegate);
        }
        // ignore the previously injected ones
    }

    /**
     * Extends a possibly existing {@code ValidationPlan} with the given {@code ValidationPlan}.
     *
     * @param plan    - {@code ValidationPlan}
     * @param scanned - has this element been dom-scanned?
     */
    public void mergePlanIn(ValidationPlan plan, boolean scanned) {

        assert plan != null;

        ValidatableWidget target = plan.getTarget();
        if (validatables.containsKey(target)) {
            validatables.get(target).merge(plan);

        } else {
            // this is a completely new target
            validatables.put(target, plan);
        }

        if (!StringUtils.isBlank(plan.getAlias())) {
            fieldsByAlias.put(plan.getAlias(), target);
        }

        // mark as non-DOM-revisitable
        if (scanned) {
            domScanned.add(target);
        }
    }

    public void acceptValidator(ValidationDriver validator) {
        validators.add(validator);
        // append the inner validator's field registry
        // warn: we may don't want to do this
        fieldsByAlias.putAll(validator.getConfig().fieldsByAlias);
    }

    public void reset() {
        validators.clear();
        validatables.clear();
        domScanned.clear();
    }

    @Override
    public ValidatableWidget byAlias(String alias) {
        return fieldsByAlias.get(alias);
    }

    public Set<Validator> getValidators() {
        return Collections.unmodifiableSet(validators);
    }

    public void unconfigure(ValidatableWidget widget) {
        validatables.remove(widget);
        domScanned.remove(widget);
    }
}
