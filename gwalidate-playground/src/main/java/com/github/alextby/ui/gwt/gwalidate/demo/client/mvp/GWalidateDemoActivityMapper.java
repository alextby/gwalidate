package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp;

import com.github.alextby.ui.gwt.gwalidate.demo.client.gin.ActivityFactory;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.AboutPlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.BasicUseCasePlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.DynamicUseCasePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class GWalidateDemoActivityMapper implements ActivityMapper {

    private final ActivityFactory activityFactory;

    @Inject
    public GWalidateDemoActivityMapper(ActivityFactory activityFactory) {
        this.activityFactory = activityFactory;
    }

    @Override
    public Activity getActivity(Place place) {

        if (place instanceof BasicUseCasePlace) {
            return activityFactory.simpleUseCaseActivity();

        } else if (place instanceof DynamicUseCasePlace) {
            return activityFactory.dynamicUseCaseActivity();

        } else if (place instanceof AboutPlace) {
            return activityFactory.aboutActivity();
        }

        return null;
    }
}
