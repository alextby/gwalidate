package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.CrossFieldContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;

/**
 * Cross-field rule: the one that implies testing multiple related fields
 * in a single rule.<br/>
 * A cross-field rule is typically executed at the very end of the validation plan.
 */
public abstract class CrossFieldRule implements ExecutableRule {

    private CrossFieldContext context;

    /**
     * Checks whether the given validatable widget is valid
     *
     * @param validatable - validatable widget
     * @return - true or false
     */
    protected final boolean isValid(ValidatableWidget validatable) {
        return context.isValid(validatable);
    }

    /**
     * Checks whether ALL the given validatable widgets are valid
     *
     * @param widgets - array of widgets
     * @return - true or false
     */
    protected final boolean isValidAll(ValidatableWidget... widgets) {
        if (widgets == null || widgets.length == 0) return true;
        for (ValidatableWidget widget : widgets) {
            if (!isValid(widget)) return false;
        }
        return true;
    }

    /**
     * Checks whether the given validatable widget is actually empty
     *
     * @param widget - {@code ValidatableWidget}
     * @return - true or false
     */
    protected final boolean isEmpty(ValidatableWidget widget) {
        return RequiredRule.isEmpty(widget.getSourceValue());
    }

    /**
     * Returns the converted value if:
     * <ul>
     * <li>the given validatable widget is not empty</li>
     * <li>the given validatable widget is not in error (any violations pending) </li>
     * </ul>
     * Important: this method returns a NULL in case if the field is an optional empty field (since convertion never
     * actually happens).</br>
     * Clients are advised to rely on {@link #isValid(com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget)} and
     * {@link #isEmpty(com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget)} for a more precise
     * state determination.
     *
     * @param validatable - validatable widget
     * @param nil         - null placeholder
     * @return - converted value
     */
    protected final Object getValue(ValidatableWidget validatable, Object nil) {
        // do not attempt to get a value for an in-error validatable
        if (!isValid(validatable)) throw new IllegalStateException();
        // attempt to get the converter value (if any)
        Object result = context.getConverted(validatable);
        return result != null ? result : nil;
    }

    protected final Object getValue(ValidatableWidget validatable) {
        return getValue(validatable, null);
    }

    /**
     * Looks up for a validatable widget aliased by the given string
     *
     * @param alias - string alias
     * @return - validatable widget
     */
    protected final ValidatableWidget byAlias(String alias) {
        return context.byAlias(alias);
    }

    public final void setContext(CrossFieldContext context) {
        this.context = context;
    }

    /**
     * Performs the actual rule check
     *
     * @param target  - validatable widget
     * @param context - rule context
     * @throws com.github.alextby.ui.gwt.gwalidate.core.model.RuleException
     *          - if the rule fails
     */
    public abstract void check(ValidatableWidget target, RuleContext context) throws RuleException;

    @Override
    public final void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }
}
