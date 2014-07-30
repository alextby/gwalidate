package com.github.alextby.ui.gwt.gwalidate.core.config;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigIntegerConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterProvider;
import com.github.alextby.ui.gwt.gwalidate.core.convert.DoubleConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.IntegerConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.LongConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.NativeConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextToStringConverter;
import com.github.alextby.ui.gwt.gwalidate.core.dom.DomPlanScanner;
import com.github.alextby.ui.gwt.gwalidate.core.engine.CompositeAdapter;
import com.github.alextby.ui.gwt.gwalidate.core.engine.InactiveValidator;
import com.github.alextby.ui.gwt.gwalidate.core.engine.NativeCompositeAdapter;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationDriver;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationServices;
import com.github.alextby.ui.gwt.gwalidate.core.engine.Validator;
import com.github.alextby.ui.gwt.gwalidate.core.msg.DefaultMessageResolver;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RequiredRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRuleFactory;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

/**
 * GWalidate GIN Module.<br/>
 * <ul>
 *   <li>binds {@code MessagesResolver}</li>
 *   <li>binds {@code CompositeAdapter}</li>
 *   <li>binds converters</li>
 *   <li>installs {@code ValidationRuleFactory}</li>
 *   <li>binds validations rules</li>
 *   <li>binds various validation services</li>
 *   <li>binds {@code Validator} and a {@code ValidationPanel}</li>
 * </ul>
 */
public class GWalidateModule extends AbstractGinModule {

    // validator turned off?
    private boolean off;

    public GWalidateModule() {
    }

    public GWalidateModule(boolean off) {
        this.off = off;
    }

    @Override
    protected void configure() {

        bindMessageResolver();

        bindConverters();

        bindConverterPlugin();

        // validation rules factory
        install(new GinFactoryModuleBuilder().build(ValidationRuleFactory.class));

        // -- singleton rules
        bind(RequiredRule.class).in(Singleton.class);

        bind(DomPlanScanner.class).in(Singleton.class);

        bind(ValidationServices.class).in(Singleton.class);

        bindCompositeAdapter();

        if (off) {
            bind(Validator.class).to(InactiveValidator.class);
        } else {
            bind(Validator.class).to(ValidationDriver.class);
        }

        bind(ValidationPanel.class);
    }

    /**
     * Configures converters:<br/>
     * <ul>
     *  <li>binds {@code TextConverter}s</li>
     *  <li>binds {@code ConverterPlugin}</li>
     *  <li>binds {@code ConverterProvider}</li>
     *  <li>installed the default {@code ConverterFactory}</li>
     * </ul>
     * Allows children to override this configuration.
     */
    protected void bindConverters() {

        // 1 instance of converter per type
        bind(TextToStringConverter.class).in(Singleton.class);
        bind(LongConverter.class).in(Singleton.class);
        bind(IntegerConverter.class).in(Singleton.class);
        bind(BigIntegerConverter.class).in(Singleton.class);
        bind(DoubleConverter.class).in(Singleton.class);

        bind(ConverterProvider.class).in(Singleton.class);

        install(new GinFactoryModuleBuilder().build(ConverterFactory.class));
    }

    /**
     * Binds {@code ConverterPlugin} to the default {@code NativeConverterPlugin} impl.
     */
    protected void bindConverterPlugin() {
        bind(ConverterPlugin.class).to(NativeConverterPlugin.class).in(Singleton.class);
    }

    protected void bindMessageResolver() {
        bind(MessagesResolver.class).to(DefaultMessageResolver.class).in(Singleton.class);
    }

    protected void bindCompositeAdapter() {
        bind(CompositeAdapter.class).to(NativeCompositeAdapter.class).in(Singleton.class);
    }
}
