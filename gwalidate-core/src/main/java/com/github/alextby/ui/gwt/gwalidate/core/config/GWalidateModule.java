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
import com.github.alextby.ui.gwt.gwalidate.core.engine.InactiveValidator;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationDriver;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationServices;
import com.github.alextby.ui.gwt.gwalidate.core.engine.Validator;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessageResolver;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RequiredRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRuleFactory;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

/**
 * GWalidate GIN Module
 */
public class GWalidateModule extends AbstractGinModule {

    private boolean off;

    private GinModule converterModule;

    public GWalidateModule() {
    }

    public GWalidateModule(boolean off) {
        this.off = off;
    }

    public GWalidateModule(GinModule converterModule) {
        this.converterModule = converterModule;
    }

    public GWalidateModule(boolean off, GinModule converterModule) {
        this.off = off;
        this.converterModule = converterModule;
    }

    @Override
    protected void configure() {

        // validation messages resolver
        bind(MessageResolver.class).in(Singleton.class);

        // 1 instance of converter per type
        bind(TextToStringConverter.class).in(Singleton.class);
        bind(LongConverter.class).in(Singleton.class);
        bind(IntegerConverter.class).in(Singleton.class);
        bind(BigIntegerConverter.class).in(Singleton.class);
        bind(DoubleConverter.class).in(Singleton.class);

        bindConverterPlugin();
        bind(ConverterProvider.class).in(Singleton.class);

        if (converterModule == null) {
            install(new GinFactoryModuleBuilder().build(ConverterFactory.class));
        } else {
            // this is an external converter module
            install(converterModule);
        }

        // validation rules factory
        install(new GinFactoryModuleBuilder().build(ValidationRuleFactory.class));

        // -- singleton rules
        bind(RequiredRule.class).in(Singleton.class);

        bind(DomPlanScanner.class);

        bind(ValidationServices.class).in(Singleton.class);

        if (off) {
            bind(Validator.class).to(InactiveValidator.class);
        } else {
            bind(Validator.class).to(ValidationDriver.class);
        }

        bind(ValidationPanel.class);
    }

    protected void bindConverterPlugin() {
        bind(ConverterPlugin.class).to(NativeConverterPlugin.class).in(Singleton.class);
    }
}
