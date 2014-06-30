package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.AboutPlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.BasicUseCasePlace;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.place.DynamicUseCasePlace;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo Main Layout Panel
 */
@Singleton
public class DemoMainLayoutView extends Composite {

    interface Binder extends UiBinder<HTMLPanel, DemoMainLayoutView> {
    }

    private static final String CSS_ACTIVE = "active";

    private final Map<Class<? extends Place>, MenuHandler> menuHandlers =
            new HashMap<Class<? extends Place>, MenuHandler>();

    @Inject
    public DemoMainLayoutView(Binder binder, EventBus eventBus) {

        menuHandlers.put(AboutPlace.class, new MenuHandler() {
            @Override
            public void onPlaceChanged() {
                clearActive();
                aboutLink.addClassName(CSS_ACTIVE);
            }
        });

        menuHandlers.put(BasicUseCasePlace.class, new MenuHandler() {
            @Override
            public void onPlaceChanged() {
                clearActive();
                basicCaseLink.addClassName(CSS_ACTIVE);
            }
        });

        menuHandlers.put(DynamicUseCasePlace.class, new MenuHandler() {
            @Override
            public void onPlaceChanged() {
                clearActive();
                dynamicCaseLink.addClassName(CSS_ACTIVE);
            }
        });


        initWidget(binder.createAndBindUi(this));

        eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
            @Override
            public void onPlaceChange(PlaceChangeEvent placeChangeEvent) {
                final Class<? extends Place> placeClazz = placeChangeEvent.getNewPlace().getClass();
                MenuHandler menuHandler = menuHandlers.get(placeClazz);
                if (menuHandler != null) {
                    menuHandler.onPlaceChanged();
                }
            }
        });
    }

    @UiField
    HTMLPanel rootPanel;

    @UiField
    SimplePanel casesContainer;

    @UiField
    LIElement aboutLink;

    @UiField
    LIElement basicCaseLink;

    @UiField
    LIElement dynamicCaseLink;

    public SimplePanel getContainer() {
        return casesContainer;
    }

    private void clearActive() {
        aboutLink.removeClassName(CSS_ACTIVE);
        basicCaseLink.removeClassName(CSS_ACTIVE);
        dynamicCaseLink.removeClassName(CSS_ACTIVE);
    }

    private interface MenuHandler {
        void onPlaceChanged();
    }
}
