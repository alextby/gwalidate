package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigDecimalConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Checks whether the given {@code BigDecimal} number resides in the given interval.
 *
 * @see IntervalRule
 */
public class BigRangeRule extends IntervalRule<Object, BigDecimal> {

    public static final BigDecimal DEFAULT_MIN = BigDecimal.ZERO;

    BigRangeRule() {
        super(DEFAULT_MIN, BigDecimal.ONE);
    }

    @Inject
    private BigDecimalConverter converter;

    @Override
    public void check(Object value, Validatable target, RuleContext context) throws RuleException {

        BigDecimal bdValue;
        MessagesResolver messageResolver = context.messages();

        if (value instanceof String) {

            BigDecimalConverter converter = context.converters().forBigDecimal();
            try {
                bdValue = converter.parse((String) value);

            } catch (ParseException excpt) {
                throw new RuleException(messageResolver.getMessage(BigDecimalConverter.ID, value));
            }

        } else if (value instanceof BigDecimal) {
            bdValue = ((BigDecimal) value);
        } else {
            // put a warn here
            return;
        }

        if (bdValue.compareTo(getMin()) < (isMinOut() ? 1 : 0) ||
            bdValue.compareTo(getMax()) > (isMaxOut() ? -1 : 0)) {

            throw new RuleException(messageResolver.getMessage(
                    getMessageKey(),
                    getLabelSafely(target),
                    converter.render(getMin()),
                    converter.render(getMax())));
        }
    }
}
