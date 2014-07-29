package com.github.alextby.ui.gwt.gwalidate.test.client.view;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Form View with {@code ValidationPanel}.
 */
public class ValidatorTestFormView extends Composite {

    interface ValidatorViewBinder extends UiBinder<HTMLPanel, ValidatorTestFormView> {
    }

    private static ValidatorViewBinder uiBinder = GWT.create(ValidatorViewBinder.class);

    @UiField(provided = true)
    ValidationPanel validationPanel;

    @UiField
    SimplePanel formPanel;

    @UiField
    Button validateButton;

    public ValidatorTestFormView(Widget form, ValidationPanel validationPanel) {
        this.validationPanel = validationPanel;
        initWidget(uiBinder.createAndBindUi(this));
        formPanel.setWidget(form);
        this.validationPanel.rescan();
    }
}
