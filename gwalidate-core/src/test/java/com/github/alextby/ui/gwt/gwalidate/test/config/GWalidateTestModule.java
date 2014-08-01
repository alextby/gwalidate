package com.github.alextby.ui.gwt.gwalidate.test.config;

import com.github.alextby.ui.gwt.gwalidate.core.config.GWalidateModule;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.engine.CompositeAdapter;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRuleFactory;
import com.github.alextby.ui.gwt.gwalidate.test.client.msg.ResourceBundleMessageResolver;
import com.google.gwt.inject.rebind.adapter.GinModuleAdapter;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;
import org.jukito.JukitoModule;

/**
 * {@code JukitoModule} for GWalidate tests.
 */
public class GWalidateTestModule extends JukitoModule {

    @Override
    protected void configureTest() {

        install(new GinModuleAdapter(

            new GWalidateModule() {

                @Override
                protected void bindMessageResolver() {
                    bind(MessagesResolver.class).to(ResourceBundleMessageResolver.class).asEagerSingleton();
                }

                @Override
                protected void bindConverterPlugin() {
                    bind(ConverterPlugin.class).to(ReflectionConverterPlugin.class).asEagerSingleton();
                }

                @Override
                protected void bindCompositeAdapter() {
                    bind(CompositeAdapter.class).to(ReflectionCompositeAdapter.class).asEagerSingleton();
                }
            }
        ));

        // Guice requires factory module builders
        install(new FactoryModuleBuilder().build(ConverterFactory.class));
        install(new FactoryModuleBuilder().build(ValidationRuleFactory.class));
    }

    @Provides
    public ReflectionConverterPlugin reflectionConverterPlugin() {
        return new ReflectionConverterPlugin();
    }

    private static class ReflectionCompositeAdapter implements CompositeAdapter {

        @Override
        public Widget getCompositeWidget(Composite composite) {
            return GwtReflectionUtils.getPrivateFieldValue(composite, FIELD_WIDGET);
        }
    }

    public class ReflectionConverterPlugin implements ConverterPlugin {

        @Override
        public void plugIn(ValueBoxBase box, TextConverter converter) {

            GwtReflectionUtils.setPrivateFieldValue(box, FIELD_RENDERER, converter);
            GwtReflectionUtils.setPrivateFieldValue(box, FIELD_PARSER, converter);
        }
    }
}
