package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.inject.Inject;

/**
 * Handy {@code FlowPanel} with a built-in {@code Validator}.
 */
public final class ValidationPanel extends FlowPanel {

    final Validator validator;

    @Inject
    public ValidationPanel(Validator validator) {
        this.validator = validator;
        this.validator.setSkipHidden(true);
        initDriver();
    }

    public ValidationStatus validate() {
        return validator.run();
    }

    public void nullify() {
        validator.reset();
    }

    public void skipHidden(boolean skipHidden) {
        validator.setSkipHidden(skipHidden);
    }

    private void initDriver() {
        if (validator != null && validator instanceof ValidationDriver) {
            ((ValidationDriver) validator).setScanpoint(this);
        }
    }

    public void scanOnAttach(boolean scanOnAttach) {
        this.validator.setScanOnAttach(scanOnAttach);
    }

    public void rescan() {
        this.validator.rescan(true);
    }

    public void rescan(boolean force) {
        this.validator.rescan(force);
    }

    public ValidatorDelegate getDelegate() {
        return this.validator;
    }
}
