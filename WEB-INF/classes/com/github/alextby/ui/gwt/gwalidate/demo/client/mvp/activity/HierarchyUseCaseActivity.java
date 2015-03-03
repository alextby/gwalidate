package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.IDynamicUseCaseView;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.IHierarchyUseCaseView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HierarchyUseCaseActivity extends AbstractActivity implements IHierarchyUseCaseView.Presenter {

    private ViewFactory viewFactory;

    @Inject
    public HierarchyUseCaseActivity(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        final IHierarchyUseCaseView view = viewFactory.hierarchyUseCaseView();
        view.setPresenter(this);
        container.setWidget(view.asWidget());
    }
}
