package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.AboutActivity;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.BasicUseCaseActivity;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.DynamicUseCaseActivity;

public interface  ActivityFactory {

    BasicUseCaseActivity basicUseCaseActivity();

    DynamicUseCaseActivity dynamicUseCaseActivity();

    AboutActivity aboutActivity();
}
