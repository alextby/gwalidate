package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ValueBoxConverters;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRules;
import com.google.inject.Inject;

/**
 * Set of validation services and helpers.
 */
public class ValidationServices {

    private ConverterFactory converterFactory;

    private ConverterPlugin converterPlugin;

    private MessagesResolver messageResolver;

    private ValidationRules validationRules;

    private ValueBoxConverters valueBoxConverters;

    @Inject
    public ValidationServices(ConverterFactory converterFactory,
                              MessagesResolver messageResolver,
                              ValidationRules validationRules,
                              ValueBoxConverters valueBoxConverters,
                              ConverterPlugin converterPlugin) {
        this.converterFactory = converterFactory;
        this.messageResolver = messageResolver;
        this.validationRules = validationRules;
        this.valueBoxConverters = valueBoxConverters;
        this.converterPlugin = converterPlugin;
    }

    /**
     * Returns {@code DefaultMessageResolver}
     *
     * @return {@code DefaultMessageResolver}
     */
    public MessagesResolver getMessageResolver() {
        return messageResolver;
    }

    /**
     * Returns {@code ConverterFactory}
     *
     * @return {@code ConverterFactory}
     */
    public ConverterFactory getConverterFactory() {
        return converterFactory;
    }

    /**
     * Returns a factory for the built-in validation rules
     *
     * @return - {@code ValidationRules}
     */
    public ValidationRules getRules() {
        return validationRules;
    }

    /**
     * Return the core widget-to-converter mapping
     *
     * @return - {@code ValueBoxConverters}
     */
    public ValueBoxConverters getValueBoxConverters() {
        return valueBoxConverters;
    }

    /**
     * Returns the {@code ConverterPlugin}
     * @return - converter plugin
     */
    public ConverterPlugin getConverterPlugin() {
        return converterPlugin;
    }
}
