package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ToStringRenderer;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Arrays;

@Singleton
public class BasicUseUseCaseView extends BaseUseCaseView implements IBasicUseCaseView {

    interface SimpleUseCaseBinder extends UiBinder<Widget, BasicUseUseCaseView> {
    }

    private Presenter presenter;

    @UiField(provided = true)
    ValueListBox<Gender> genderListBox;

    @Override
    public void setPresenter(IBasicUseCaseView.Presenter presenter) {
        this.presenter = presenter;
    }

    @Inject
    public BasicUseUseCaseView(SimpleUseCaseBinder binder, ValidationPanel validationPanel) {
        super(validationPanel);
        this.validationPanel.scanOnAttach(true);
        this.genderListBox = new ValueListBox<Gender >(new ToStringRenderer<Gender>());
        initWidget(binder.createAndBindUi(this));
        genderListBox.setAcceptableValues(Arrays.asList(Gender.values()));
    }
}
