package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SubmitButton;

/**
 * Base Use-Case View
 */
public abstract class BaseUseCaseView extends Composite {

    @UiField(provided = true)
    ValidationPanel validationPanel;

    ValidatorDelegate delegate;

    @UiField
    SubmitButton validateBtn;

    public BaseUseCaseView(ValidationPanel panel) {
        this.validationPanel = panel;
    }

    @UiHandler("validateBtn")
    public void onValidateBtnCick(ClickEvent event) {
        if (validationPanel.validate().isValid()) {
            Window.alert("Validation went well!");
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
