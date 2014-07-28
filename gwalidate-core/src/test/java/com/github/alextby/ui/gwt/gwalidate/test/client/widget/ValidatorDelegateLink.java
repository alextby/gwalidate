package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.github.alextby.ui.gwt.gwalidate.core.engine.HasValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.google.gwt.user.client.ui.Anchor;

/**
 * Simple holder for {@code ValidatorDelegate}
 */
public class ValidatorDelegateLink extends Anchor implements HasValidatorDelegate {

    private ValidatorDelegate validator;

    public ValidatorDelegateLink() {
        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(false);
    }

    @Override
    public void setDelegate(ValidatorDelegate delegate) {
        this.validator = delegate;
    }

    public ValidatorDelegate getDelegate() {
        return this.validator;
    }
}
