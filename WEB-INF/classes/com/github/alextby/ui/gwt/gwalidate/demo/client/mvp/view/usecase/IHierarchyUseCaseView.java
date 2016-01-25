package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.HasAnalyticsPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface IHierarchyUseCaseView extends IsWidget {

    void setPresenter(Presenter presenter);

    interface Presenter extends HasAnalyticsPresenter {
    }
}
