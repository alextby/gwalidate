package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.GWalidateDemoActivityMapper;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.GWalidateDemoPlaceHistoryMapper;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class GWalidateDemoMvpFactory implements Provider<EventBus> {

    private final EventBus eventBus = new SimpleEventBus();

    private final PlaceController placeController = new PlaceController(eventBus);

    private final PlaceHistoryHandler placeHistoryHandler;

    private final ActivityManager activityManager;

    private final GWalidateDemoActivityMapper activityMapper;

    private final GWalidateDemoPlaceHistoryMapper placeHistoryMapper;

    @Inject
    public GWalidateDemoMvpFactory(GWalidateDemoPlaceHistoryMapper historyMapper,
                                   GWalidateDemoActivityMapper activityMapper,
                                   GWalidateDemoPlaceHistoryMapper placeHistoryMapper) {
        this.placeHistoryHandler = new PlaceHistoryHandler(historyMapper);
        this.activityMapper = activityMapper;
        this.placeHistoryMapper = placeHistoryMapper;
        this.activityManager = new ActivityManager(activityMapper, eventBus);
    }

    public EventBus eventBus() { return eventBus; }

    public PlaceController placeController() { return placeController; }

    public PlaceHistoryHandler placeHistoryHandler() { return placeHistoryHandler; }

    public ActivityManager activityManager() { return activityManager; }

    public GWalidateDemoPlaceHistoryMapper placeHistoryMapper() { return placeHistoryMapper; }

    public GWalidateDemoActivityMapper activityMapper() { return activityMapper; }

    @Override
    public EventBus get() {
        return eventBus;
    }
}
