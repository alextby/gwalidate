package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ToStringRenderer;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Arrays;

@Singleton
public class BasicUseUseCaseView extends Composite implements IBasicUseCaseView {

    interface SimpleUseCaseBinder extends UiBinder<Widget, BasicUseUseCaseView> {
    }

    private Presenter presenter;

    @UiField(provided = true)
    ValidationPanel validationPanel;

    @UiField(provided = true)
    ValueListBox<String> genderField;

    @UiField
    SubmitButton validateBtn;

    @Inject
    public BasicUseUseCaseView(SimpleUseCaseBinder binder, ValidationPanel validationPanel) {
        this.validationPanel = validationPanel;
        this.validationPanel.scanOnAttach(true);
        this.genderField = new ValueListBox<String>(new ToStringRenderer<String>());
        initWidget(binder.createAndBindUi(this));
        genderField.setAcceptableValues(Arrays.asList("male", "female"));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("validateBtn")
    public void onValidateBtnCick(ClickEvent event) {
        if (validationPanel.validate().isValid()) {
            Window.alert("Validation went well!");
        }
    }
}
