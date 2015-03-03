package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.AboutActivity;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.BasicUseCaseActivity;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.DynamicUseCaseActivity;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.activity.HierarchyUseCaseActivity;

public interface  ActivityFactory {

    BasicUseCaseActivity basicUseCaseActivity();

    DynamicUseCaseActivity dynamicUseCaseActivity();

    HierarchyUseCaseActivity hierarchyUseCaseActivity();
    
    AboutActivity aboutActivity();
}
