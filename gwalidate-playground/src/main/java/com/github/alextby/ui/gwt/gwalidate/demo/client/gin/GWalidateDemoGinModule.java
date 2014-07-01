package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.core.config.ValidationModule;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.GWalidateDemoActivityMapper;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.GWalidateDemoPlaceHistoryMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.web.bindery.event.shared.EventBus;

/**
 * GWalidate GIN Module
 */
public class GWalidateDemoGinModule extends AbstractGinModule {

    protected void configure() {

        bind(GWalidateDemoMvpFactory.class).asEagerSingleton();
        bind(EventBus.class).toProvider(GWalidateDemoMvpFactory.class);

        bind(GWalidateDemoActivityMapper.class).asEagerSingleton();
        bind(GWalidateDemoPlaceHistoryMapper.class).asEagerSingleton();

        install(new GinFactoryModuleBuilder().build(ActivityFactory.class));
        bind(ActivityFactory.class).asEagerSingleton();
        install(new GinFactoryModuleBuilder().build(ViewFactory.class));
        bind(ViewFactory.class).asEagerSingleton();

        install(new ValidationModule());
    }
}
