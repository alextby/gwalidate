package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;

/**
 * Text converter rule contract.
 */
public interface TextConversionRule<R> {

    /**
     * Converts the source value of the given {@code Validatable}
     *
     * @param value   - source value
     * @param target  - {@code Validatable}
     * @param context - {@code RuleContext}
     * @return - converted value
     * @throws com.github.alextby.ui.gwt.gwalidate.core.model.RuleException
     *          - if conversion fails
     */
    R convert(String value, Validatable target, RuleContext context) throws RuleException;

    /**
     * Returns converter accosiated with this rule
     *
     * @return - {@code GenericTextConverter} of type R
     */
    TextConverter<R> getConverter();
}
