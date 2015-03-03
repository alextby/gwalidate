package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.ValidatedSimpleForm;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.ValidatedCompositeForm;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HierarchyUseCaseView extends BaseUseCaseView implements IHierarchyUseCaseView {

    interface SimpleUseCaseBinder extends UiBinder<Widget, HierarchyUseCaseView> {
    }

    private Presenter presenter;

    @UiField(provided = true)
    ValidatedCompositeForm form1;
    
    @UiField(provided = true)
    ValidatedSimpleForm form2;
    
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Inject
    public HierarchyUseCaseView(SimpleUseCaseBinder binder, ValidationPanel validationPanel, 
                                ValidatedCompositeForm form1, ValidatedSimpleForm form2) {
        super(validationPanel);
        this.form1 = form1;
        this.form2 = form2;   
        this.validationPanel.scanOnAttach(true);
        initWidget(binder.createAndBindUi(this));
    }
}
