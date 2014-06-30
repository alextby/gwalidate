package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BasicUseUseCaseView extends Composite implements IBasicUseCaseView {

    interface SimpleUseCaseBinder extends UiBinder<Widget, BasicUseUseCaseView> {
    }

    private Presenter presenter;

    @Inject
    public BasicUseUseCaseView(SimpleUseCaseBinder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
