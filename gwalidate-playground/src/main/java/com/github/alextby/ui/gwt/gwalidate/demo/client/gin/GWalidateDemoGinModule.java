package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.view.DemoMainLayoutView;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * GWalidate GIN Module
 */
public class GWalidateDemoGinModule extends AbstractGinModule {

    protected void configure() {

        bind(DemoMainLayoutView.class).in(Singleton.class);
    }
}
