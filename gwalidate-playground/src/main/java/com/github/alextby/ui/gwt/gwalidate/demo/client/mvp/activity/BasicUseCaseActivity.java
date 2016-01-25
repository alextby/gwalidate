package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity;

import com.arcbees.analytics.shared.Analytics;
import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.IBasicUseCaseView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class BasicUseCaseActivity extends AbstractActivity implements IBasicUseCaseView.Presenter {

    private final ViewFactory viewFactory;

    private final Analytics analytics;
    
    @Inject
    public BasicUseCaseActivity(ViewFactory viewFactory, Analytics analytics) {
        this.viewFactory = viewFactory;
        this.analytics = analytics;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        final IBasicUseCaseView view = viewFactory.simpleUseCaseView();
        view.setPresenter(this);
        container.setWidget(view.asWidget());
    }

    @Override
    public void onSendPageView() {
        analytics.sendPageView().documentPath("/basic_use_case").go();
    }
}
