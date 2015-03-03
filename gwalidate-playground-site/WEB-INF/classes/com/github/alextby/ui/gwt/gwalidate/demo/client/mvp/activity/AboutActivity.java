package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.IAboutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class AboutActivity extends AbstractActivity implements IAboutView.Presenter {

    private ViewFactory viewFactory;

    @Inject
    public AboutActivity(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        final IAboutView view = viewFactory.aboutView();
        view.setPresenter(this);
        container.setWidget(view.asWidget());
    }
}
