package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Defines a regexp rule.
 */
public class RegexpRule extends SingleFieldRule implements ValidationRule<String> {

    public static final String ID = "gwt_client_validate_Regexp";

    private final String pattern;

    @Inject
    RegexpRule(@Assisted String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public void check(String value, Validatable target, RuleContext context) throws RuleException {

        if (StringUtils.isBlank(value)) {
            return;
        }
        if (!value.matches(pattern)) {
            throw new RuleException(deriveMessage(context.messages(), getMessageKey()));
        }
    }

    protected String getMessageKey() {
        return ID;
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }
}
