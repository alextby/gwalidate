package com.github.alextby.ui.gwt.gwalidate.test.client.view.form;

import com.github.alextby.ui.gwt.gwalidate.core.engine.HasValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Validated Test Form
 */
public interface ValidatedTestForm extends HasValidatorDelegate, IsWidget {

    void initFields();

    void initRules();

    ValidatorDelegate getValidatorDelegate();
}
