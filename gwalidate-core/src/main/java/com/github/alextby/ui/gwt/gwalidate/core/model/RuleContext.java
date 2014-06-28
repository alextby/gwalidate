package com.github.alextby.ui.gwt.gwalidate.core.model;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessageResolver;

import java.util.Set;

/**
 * Rule-visibile part of the validation context.
 */
public interface RuleContext extends HasCategories {

    /**
     * Returns {@code MessageResolver}
     *
     * @return - message resolver
     */
    MessageResolver messages();

    /**
     * Returns {@code ConverterFactory}
     *
     * @return - converter factory
     */
    ConverterFactory converters();

    /**
     * Returns a set of the currently active categories
     *
     * @return - active categories
     */
    Set<String> getCategories();
}
