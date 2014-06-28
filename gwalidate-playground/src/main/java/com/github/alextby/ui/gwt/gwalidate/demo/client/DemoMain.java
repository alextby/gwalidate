package com.github.alextby.ui.gwt.gwalidate.demo.client;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.GWalidateDemoGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The {@code EntryPoint} for the GWalidate Demo App.
 */
public class DemoMain implements EntryPoint {

    private final GWalidateDemoGinjector DI = GWT.create(GWalidateDemoGinjector.class);

    @Override
    public void onModuleLoad() {

        RootPanel rootPanel = RootPanel.get();
        rootPanel.add(DI.layoutPanel());
    }
}
