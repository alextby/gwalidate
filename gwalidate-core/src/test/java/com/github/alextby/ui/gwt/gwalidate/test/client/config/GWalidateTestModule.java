package com.github.alextby.ui.gwt.gwalidate.test.client.config;

import com.github.alextby.ui.gwt.gwalidate.core.config.GWalidateModule;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRuleFactory;
import com.github.alextby.ui.gwt.gwalidate.test.client.convert.ReflectionConverterPlugin;
import com.google.gwt.inject.rebind.adapter.GinModuleAdapter;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jukito.JukitoModule;

/**
 * {@code JukitoModule} for GWalidate tests.
 *
 * @author Alex Tsikhanovich
 */
public class GWalidateTestModule extends JukitoModule {

    @Override
    protected void configureTest() {

        install(new GinModuleAdapter(

            new GWalidateModule() {

                @Override
                protected void bindConverterPlugin() {
                    // override the converter plugin binding
                    bind(ConverterPlugin.class).to(ReflectionConverterPlugin.class).in(Singleton.class);
                }
            }
        ));

        install(new FactoryModuleBuilder().build(ConverterFactory.class));
        install(new FactoryModuleBuilder().build(ValidationRuleFactory.class));
    }
}