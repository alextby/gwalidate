package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Reusable panel buttons composite
 * 
 * @author Alex Tsikhanovich
 */
public class PanelButtons extends Composite {

	@UiField
	SubmitButton validateBtn;
	
	@UiField
	Button nullifyBtn;
	
	@UiField
	Button hardRescanBtn;
	
	@UiField
	Button rescanBtn;
	
	private ValidationPanel validationPanel;
	
	interface PanelButtonsBinder extends UiBinder<HTMLPanel, PanelButtons> {
	}

	private static PanelButtonsBinder uiBinder = GWT.create(PanelButtonsBinder.class);
	
	public PanelButtons() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiConstructor
	public PanelButtons(ValidationPanel validationPanel) {
		this();
		this.validationPanel = validationPanel;
	}

	public SubmitButton getValidateBtn() {
		return validateBtn;
	}

	public Button getNullifyBtn() {
		return nullifyBtn;
	}

	public Button getHardRescanBtn() {
		return hardRescanBtn;
	}

	public Button getRescanBtn() {
		return rescanBtn;
	}

	@UiHandler("validateBtn")
	public void onValidateBtnCick(ClickEvent event) {
		if (validationPanel.validate().isValid()) {
			validateBtn.setType(ButtonType.SUCCESS);
		} else {
			validateBtn.setType(ButtonType.DANGER);
		}
	}
	
	@UiHandler("nullifyBtn")
	public void onResetBtnClick(ClickEvent event) {
		validationPanel.nullify();
	}

	@UiHandler("hardRescanBtn")
	public void onHardRescanBtnClick(ClickEvent event) {
		validationPanel.rescan(true);
	}

	@UiHandler("rescanBtn")
	public void onRescanBtnClick(ClickEvent event) {
		validationPanel.rescan(false);
	}
}
