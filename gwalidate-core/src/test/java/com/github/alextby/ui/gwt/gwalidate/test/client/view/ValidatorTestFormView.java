package com.github.alextby.ui.gwt.gwalidate.test.client.view;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.ValidatedTestForm;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Form View with {@code ValidationPanel}.
 */
public class ValidatorTestFormView extends Composite {

    interface ValidatorViewBinder extends UiBinder<HTMLPanel, ValidatorTestFormView> {
    }

    private static ValidatorViewBinder UI_BINDER = GWT.create(ValidatorViewBinder.class);

    @UiField(provided = true)
    ValidationPanel validationPanel;

    @UiField
    SimplePanel formPanel;

    ValidatedTestForm form;

    @UiField
    Button validateButton;

    public ValidatorTestFormView(ValidatedTestForm form, ValidationPanel validationPanel) {
        this.validationPanel = validationPanel;
        this.form = form;
        initWidget(UI_BINDER.createAndBindUi(this));
        formPanel.setWidget(form);
        this.validationPanel.rescan();
    }

    public ValidatedTestForm getForm() {
        return form;
    }
}
