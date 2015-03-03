package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.google.gwt.user.client.ui.IsWidget;

public interface IBasicUseCaseView extends IsWidget {

    public void setPresenter(Presenter presenter);

    public interface Presenter {
    }
}
