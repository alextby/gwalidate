package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.AboutView;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.DemoMainLayoutView;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.BasicUseUseCaseView;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase.DynamicUseCaseView;

public interface ViewFactory {

    DemoMainLayoutView mainLayoutView();

    AboutView aboutView();

    BasicUseUseCaseView simpleUseCaseView();

    DynamicUseCaseView dynamicUseCaseView();
}
