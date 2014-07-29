package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.Collection;

/**
 * <p>
 *  Checks whether the given value (of an arbitrary type) has the expected size (int).<br/>
 *  The notion of size depends on the actual type checked at runtime:
 *  <ul>
 *      <li>String - length</li>
 *      <li>Collection - size</li>
 *      ...
 *  </ul>
 * </p>
 *
 * @see IntervalRule
 */
public final class SizeRule extends IntervalRule<Object, Integer> {

    public static final String MSG_STRING_SIZE = "gwt_client_validate_Size_String";

    public static final String MSG_COLLECTION_SIZE = "gwt_client_validate_Size_Collection";

    public static final int DEFAULT_MIN = 0;

    @Inject
    private com.github.alextby.ui.gwt.gwalidate.core.convert.IntegerConverter converter;

    @Inject
    SizeRule(@Assisted Integer size) {
        super(DEFAULT_MIN, size);
    }

    @Override
    public void check(Object value, Validatable target, RuleContext context) throws RuleException {

        if (value == null) {
            // nulls are considered VALID
            return;
        }

        MessagesResolver messageResolver = context.messages();
        if (value instanceof String) {
            if (sizeIsWrong(((String) value).length())) {
                throw new RuleException(deriveMessage(messageResolver, MSG_STRING_SIZE, converter.render(getMax())));
            }

        } else if (value instanceof Collection) {
            if (sizeIsWrong(((Collection) value).size())) {
                throw new RuleException(deriveMessage(messageResolver, MSG_COLLECTION_SIZE, converter.render(getMax())));
            }
        }
    }

    private boolean sizeIsWrong(int size) {
        return (size <= (isMinOut() ? getMin() : getMin() - 1) ||
                size >= (isMaxOut() ? getMax() : getMax() + 1));
    }
}
