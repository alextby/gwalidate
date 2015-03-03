package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.github.alextby.ui.gwt.gwalidate.core.engine.HasValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import javax.inject.Inject;

/**
 * Validated Sub-Form
 * 
 * @author Alex Tsikhanovich
 */
public class ValidatedSimpleForm extends Composite implements HasValidatorDelegate {

	interface ValidatedSubFormBinder extends UiBinder<HTMLPanel, ValidatedSimpleForm> {
	}

	@UiField(provided = true)
	ValidationPanel validationPanel;

	ValidatorDelegate delegate;
	
	@UiField
	Button validateButton;
	
	private final ValidatedSubFormBinder BINDER = GWT.create(ValidatedSubFormBinder.class);
	
	@Inject
	public ValidatedSimpleForm(ValidationPanel panel) {
		this.validationPanel = panel;
		panel.scanOnAttach(true);
		initWidget(BINDER.createAndBindUi(this));
	}

	@Override
	public void setValidatorDelegate(ValidatorDelegate delegate) {
		this.delegate = delegate;
	}
	
	@UiHandler("validateButton")
	void onValidateSubform(ClickEvent event) {
		if (validationPanel.validate().isValid()) {
			validateButton.setType(ButtonType.SUCCESS);
		} else {
			validateButton.setType(ButtonType.DANGER);
		}
	}
}
