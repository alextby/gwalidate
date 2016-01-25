package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity;

import com.arcbees.analytics.shared.Analytics;
import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.IAboutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AboutActivity extends AbstractActivity implements IAboutView.Presenter {

    private final ViewFactory viewFactory;
    
    private final Analytics analytics;

    @Inject
    public AboutActivity(ViewFactory viewFactory, Analytics analytics) {
        this.viewFactory = viewFactory;
        this.analytics = analytics;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        final IAboutView view = viewFactory.aboutView();
        view.setPresenter(this);
        container.setWidget(view.asWidget());
    }

    @Override
    public void onSendPageView() {
        analytics.sendPageView().documentPath("/about").go();
    }
}
