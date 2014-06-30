package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ActivityFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.AboutPlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.BasicUseCasePlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.DynamicUseCasePlace;
import com.google.common.collect.Maps;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import java.util.Map;

public class GWalidateDemoActivityMapper implements ActivityMapper {

    private final ActivityFactory activityFactory;

    private final Map<Class<? extends Place>, Activity> placesMapping = Maps.newHashMap();

    @Inject
    public GWalidateDemoActivityMapper(ActivityFactory activityFactory) {
        this.activityFactory = activityFactory;
        placesMapping.put(null, null);
        placesMapping.put(BasicUseCasePlace.class, activityFactory.basicUseCaseActivity());
        placesMapping.put(DynamicUseCasePlace.class, activityFactory.dynamicUseCaseActivity());
        placesMapping.put(AboutPlace.class, activityFactory.aboutActivity());
    }

    @Override
    public Activity getActivity(Place place) {
        if (place != null) {
            return placesMapping.get(place.getClass());
        } else {
            return null;
        }
    }
}
