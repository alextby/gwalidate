package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.model.CategoryManager;
import com.github.alextby.ui.gwt.gwalidate.core.model.FieldRegistry;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRules;
import com.google.inject.Inject;

import java.util.Collections;
import java.util.List;

/**
 * Conditionally inactive {@code Validator} implementation.<br/>
 * Performs NO real validation always returning positive status for {@link #run()}.<br/>
 * Most of the API methods are stubbed for inactivity.
 * This variant of {@code Validator} is for the development purposes mostly.
 */
public class InactiveValidator implements Validator {

    private ValidationServices services;

    private ValidationContext context;

    private ValidationConfig config;

    @Inject
    public InactiveValidator(final ValidationServices validationServices) {
        this.config = new ValidationConfig();
        this.context = new ValidationContext(config, validationServices);
        this.services = validationServices;
    }

    /**
     * Performs no real validation. Permanently returns true (valid form).
     *
     * @return - "true" validation status
     */
    @Override
    public ValidationStatus run() {

        return new ValidationStatus() {

            @Override
            public List<Violation> getViolations() {
                return Collections.emptyList();
            }

            @Override
            public boolean isValid() {
                return true;
            }
        };
    }

    @Override
    public void setSkipHidden(boolean skipHidden) {
    }

    @Override
    public void reset() {
    }

    @Override
    public ValidationRules rules() {
        return services.getRules();
    }

    @Override
    public CategoryManager categories() {
        return context;
    }

    @Override
    public FieldRegistry fields() {
        return config;
    }

    /**
     * Builds plan for the given field but never injects it into the internal validation config
     *
     * @param target - {@code ValidatableWidget}
     * @return - validation plan
     */
    @Override
    public ValidationPlan.Builder planFor(ValidatableWidget target) {
        return new ValidationPlan.Builder(target);
    }

    /**
     * Performs no actions (since there is nothing in the config)
     *
     * @param target - {@code ValidatableWidget}
     */
    @Override
    public void evict(ValidatableWidget target) {
    }

    /**
     * Nothing is validated
     *
     * @param target - {@code ValidatableWidget}
     * @return - false
     */
    @Override
    public boolean isValidated(ValidatableWidget target) {
        return false;
    }

    @Override
    public void setScanOnAttach(final boolean scanOnAttach) {
    }

    @Override
    public void rescan() {
    }

    @Override
    public void rescan(final boolean force) {
    }
}
