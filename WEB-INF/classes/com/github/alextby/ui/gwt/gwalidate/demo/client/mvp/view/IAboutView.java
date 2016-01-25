package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface IAboutView extends IsWidget {

    void setPresenter(Presenter presenter);

    interface Presenter extends HasAnalyticsPresenter {
    }
}
