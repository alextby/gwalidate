package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.IBasicUseCaseView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class BasicUseCaseActivity extends AbstractActivity implements IBasicUseCaseView.Presenter {

    private ViewFactory viewFactory;

    @Inject
    public BasicUseCaseActivity(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        final IBasicUseCaseView view = viewFactory.simpleUseCaseView();
        view.setPresenter(this);
        container.setWidget(view.asWidget());
    }
}
