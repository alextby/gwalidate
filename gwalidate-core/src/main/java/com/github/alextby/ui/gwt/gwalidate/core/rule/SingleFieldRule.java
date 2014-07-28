package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

/**
 * Base class for all field-level rules (single field rules).
 */
public abstract class SingleFieldRule implements ExecutableRule {

    // message
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Executes this rule with the given rule executor
     *
     * @param executor - {@code RuleExecutor}
     * @param target   - target
     */
    @Override
    public abstract void execute(RuleExecutor executor, ValidatableWidget target);

    /**
     * Derives the final message sent out to the front-end
     *
     * @param msgResolver - message resolver
     * @param key         - message key
     * @param params      - message parameters
     * @return - message text
     */
    protected String deriveMessage(MessagesResolver msgResolver, String key, Object... params) {

        if (message != null) {
            // use the given message
            return StringUtils.formatIndexed(message, params);

        } else {
            // get by key (converter type)
            String msg = msgResolver.getMessage(key, params);
            return msg != null ? msg : "";
        }
    }

    protected String getLabelSafely(Validatable target) {
        return target != null && target.getLabel() != null ? target.getLabel() : "";
    }
}
