package com.github.alextby.ui.gwt.gwalidate.demo.client;

import com.arcbees.analytics.shared.Analytics;
import com.arcbees.analytics.shared.AnalyticsPlugin;
import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.GWalidateDemoGinjector;
import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.GWalidateDemoMvpFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ViewFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.GWalidateDemoPlaceHistoryMapper;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.BasicUseCasePlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.DemoMainLayoutView;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The {@code EntryPoint} for the GWalidate Demo App.
 */
public class DemoMain implements EntryPoint {

    private final GWalidateDemoGinjector DI = GWT.create(GWalidateDemoGinjector.class);

    @Override
    public void onModuleLoad() {

        final ViewFactory viewFactory = DI.viewFactory();
        final GWalidateDemoMvpFactory gwalidateMvpFactory = DI.mvpFactory();
        
        initUniversalAnalytics(DI.analytics());

        final DemoMainLayoutView mainLayout = viewFactory.mainLayoutView();
        PlaceController placeController = gwalidateMvpFactory.placeController();

        ActivityManager activityManager = gwalidateMvpFactory.activityManager();
        activityManager.setDisplay(mainLayout.getContainer());

        GWalidateDemoPlaceHistoryMapper historyMapper = gwalidateMvpFactory.placeHistoryMapper();
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, gwalidateMvpFactory.eventBus(), new BasicUseCasePlace());

        RootPanel.get().add(mainLayout);

        historyHandler.handleCurrentHistory();
        
    }
    
    private void initUniversalAnalytics(Analytics analytics) {
        analytics.create().go();
        analytics.enablePlugin(AnalyticsPlugin.DISPLAY); // (optional) Provides demographics information.
        analytics.sendPageView().go(); // (recommended) track the initial pageview
    }
}
