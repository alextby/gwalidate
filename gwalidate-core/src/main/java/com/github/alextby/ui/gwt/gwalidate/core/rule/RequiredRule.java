package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

import java.util.Collection;

/**
 * Checks for "presence" of the given value.<br/>
 * Presence means non-null AND non-empty.
 *
 * @see ValidationRule
 */
public final class RequiredRule extends SingleFieldRule implements ValidationRule<Object> {

    public static final String ID = "gwt_client_validate_Required";

    RequiredRule() {
    }

    @Override
    public void check(Object value, Validatable target, RuleContext context) throws RuleException {

        if (target.isRequired() && isEmpty(value)) {
            throw new RuleException(deriveMessage(context.messages(), ID, target.getLabel()));
        }
    }

    /**
     * Checks whether the given value is empty
     *
     * @param value - value to test
     * @return - true/false
     */
    public static boolean isEmpty(Object value) {
        return value == null ||
                (value instanceof String && StringUtils.isBlank((String) value)) ||
                (value instanceof Collection && ((Collection) value).isEmpty());
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }
}
