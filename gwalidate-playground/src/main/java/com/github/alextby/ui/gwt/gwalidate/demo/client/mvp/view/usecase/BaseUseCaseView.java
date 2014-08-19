package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SubmitButton;

/**
 * Base Use-Case View
 */
public abstract class BaseUseCaseView extends Composite {

    @UiField(provided = true)
    ValidationPanel validationPanel;

    ValidatorDelegate delegate;

    @UiField(provided = true)
    SubmitButton validateBtn;

    private Element validateBtnElement;

    public BaseUseCaseView(ValidationPanel panel) {
        this.validationPanel = panel;
        validateBtn = new SubmitButton();
        validateBtn.setText("Validate");
        validateBtnElement = validateBtn.getElement();
        validateBtnElement.addClassName("btn btn-large btn-primary");
    }

    @UiHandler("validateBtn")
    public void onValidateBtnCick(ClickEvent event) {
        validateBtnElement.removeClassName("btn-primary");
        if (validationPanel.validate().isValid()) {
            validateBtnElement.removeClassName("btn-danger");
            validateBtnElement.addClassName("btn-success");
        } else {
            validateBtnElement.removeClassName("btn-success");
            validateBtnElement.addClassName("btn-danger");
        }
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        this.delegate = validationPanel.getDelegate();
        onValidationReady();
    }

    protected void onValidationReady() {
    }
}
