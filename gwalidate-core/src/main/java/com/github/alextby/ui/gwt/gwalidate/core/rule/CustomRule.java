package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;

/**
 * Base abstract single-field custom validation rule.<br/>
 * To be overriden by the clients wishing to drop-in some custom validators.
 *
 * @param <T> - field value type
 */
public abstract class CustomRule<T> extends SingleFieldRule implements ValidationRule<T> {

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }

    @Override
    public abstract void check(T value, Validatable target, RuleContext context) throws RuleException;
}
