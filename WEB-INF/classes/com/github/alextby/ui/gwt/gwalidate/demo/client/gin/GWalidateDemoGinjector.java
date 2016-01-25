package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.arcbees.analytics.shared.Analytics;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * GWalidate Demo App {@code Ginjector}
 */
@GinModules(GWalidateDemoGinModule.class)
public interface GWalidateDemoGinjector extends Ginjector {

    GWalidateDemoMvpFactory mvpFactory();

    ViewFactory viewFactory();
    
    Analytics analytics();
}


