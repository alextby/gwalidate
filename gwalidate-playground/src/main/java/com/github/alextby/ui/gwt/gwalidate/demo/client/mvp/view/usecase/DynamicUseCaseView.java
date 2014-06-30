package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DynamicUseCaseView extends Composite implements IDynamicUseCaseView {

    interface DynamicUseCaseBinder extends UiBinder<Widget, DynamicUseCaseView> {
    }

    private Presenter presenter;

    @Inject
    public DynamicUseCaseView(DynamicUseCaseBinder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
