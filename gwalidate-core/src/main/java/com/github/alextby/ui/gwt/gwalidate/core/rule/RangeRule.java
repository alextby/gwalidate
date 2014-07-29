package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.convert.LongConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.google.inject.Inject;

import java.text.ParseException;
import java.util.Collection;

/**
 * Defines a numeric {@code IntervalRule}.
 */
public final class RangeRule extends IntervalRule<Object, Long> {

    @Inject
    private LongConverter converter;

    RangeRule() {
        super(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    @Override
    public void check(Object value, Validatable target, RuleContext context) throws RuleException {

        Long longValue;
        MessagesResolver messageResolver = context.messages();

        if (value instanceof String) {
            LongConverter converter = context.converters().forLong();
            try {
                longValue = converter.parse((String) value);

            } catch (ParseException excpt) {
                throw new RuleException(messageResolver.getMessage(LongConverter.ID, value));
            }

        } else if (value instanceof Number) {
            longValue = ((Number) value).longValue();

        } else if (value instanceof Collection) {
            longValue = (long) ((Collection) value).size();

        } else {
            // warn this
            return;
        }

        final long min = getMin();
        final long max = getMax();

        if (min == max && (isMinOut() || isMaxOut())) {
            throw new RuleException(
                deriveMessage(
                    messageResolver,
                    getMessageKey(),
                    converter.render(getMin()),
                    converter.render(getMax())
                )
            );

        } else if (longValue < (!isMinOut() ? min : min + 1) ||
                   longValue > (!isMaxOut() ? max : max - 1)) {

            throw new RuleException(
                deriveMessage(
                    messageResolver,
                    getMessageKey(),
                    converter.render(getMin()),
                    converter.render(getMax())
                )
            );
        }
    }

    public final boolean isMinOut() {
        final long minimum = getMin();
        return !(minimum == Long.MAX_VALUE || minimum == getMax()) && super.isMinOut();
    }

    public final boolean isMaxOut() {
        final long maximum = getMax();
        return !(maximum == Long.MIN_VALUE || maximum == getMin()) && super.isMaxOut();
    }

    public final Long getMin() {
        if (super.getMin() > getMax()) {
            return getMax();
        }
        return super.getMin();
    }
}
