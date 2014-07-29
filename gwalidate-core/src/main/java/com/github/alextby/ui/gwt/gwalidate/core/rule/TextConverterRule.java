package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterException;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.text.ParseException;

/**
 * {@code SingleFieldRule} wrapper for {@code TextConversionRule}s.
 *
 * @param <T> conversion result type
 *
 * @see TextConversionRule
 * @see SingleFieldRule
 */
public class TextConverterRule<T> extends SingleFieldRule implements TextConversionRule<T> {

    private final TextConverter<T> converter;

    @Inject
    public TextConverterRule(@Assisted TextConverter<T> converter) {
        this.converter = converter;
    }

    public T convert(String value, Validatable target, RuleContext context) throws RuleException {

        try {
            return converter.parse(value);

        } catch (ConverterException e) {
            // this means the error message is already there
            throw new RuleException(e.getMessage());

        } catch (ParseException e) {

            if (converter.getId() == null && getMessage() == null) {
                // use the error given with the parse exception
                throw new RuleException(e.getMessage());

            } else {
                throw new RuleException(
                    // derive the message based on the converter id or the given static message
                    deriveMessage(context.messages(), converter.getId())
                );
            }
        }
    }

    @Override
    public TextConverter<T> getConverter() {
        return converter;
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }
}
