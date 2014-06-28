package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.model.CategoryManager;
import com.github.alextby.ui.gwt.gwalidate.core.model.FieldRegistry;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRules;

/**
 * Public validator delegate API.
 */
public interface ValidatorDelegate {

    /**
     * Returns a factory for the built-in validation rules
     *
     * @return - {@code ValidationRules}
     */
    ValidationRules rules();

    /**
     * Returns the {@code CategoryManager}
     *
     * @return - {@code CategoryManager}
     */
    CategoryManager categories();

    /**
     * Returns the fields registry (by alias)
     *
     * @return - {@code FieldRegistry}
     */
    FieldRegistry fields();

    /**
     * Builds a new {@code ValidationPlan} for the given {@code ValidatableWidget}
     *
     * @param target - {@code ValidatableWidget}
     * @return - plan builder
     */
    ValidationPlan.Builder planFor(ValidatableWidget target);

    /**
     * Evicts the given validation target from the validation config
     *
     * @param target - {@code ValidatableWidget}
     */
    void evict(ValidatableWidget target);

    /**
     * Tells whether this element has already been added to the validation config.
     *
     * @param target - {@code ValidatableWidget}
     * @return - true or false
     */
    boolean isValidated(ValidatableWidget target);
}
