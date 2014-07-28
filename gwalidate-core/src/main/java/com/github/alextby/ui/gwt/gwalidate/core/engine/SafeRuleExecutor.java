package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterFactory;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.rule.CrossFieldRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RequiredRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RuleExecutor;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConversionRule;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Implements {@code RuleExecutor}.<br/>
 * Safely executes all types of rules allowing no exceptions to be thrown outside of the class.<br/>
 * In case of an unexpected processing failure a violation with a generic explanation is reported
 * (must not be a common case though) and typically identifies a bug.
 */
public class SafeRuleExecutor implements RuleExecutor {

    // generic error message key
    private static final String GENERIC_ERROR_ID = "gwt_client_validate_Generic";

    // private execution context
    private final ValidationContext context;

    // rule execution context
    private final SecureRuleContext secureContext;

    // execution errors logger
    private static Logger logger = Logger.getLogger("SafeRuleExecutor");

    SafeRuleExecutor(ValidationContext context) {
        this.context = context;
        this.secureContext = new SecureRuleContext();
    }

    /**
     * Executes the given {@code CrossFieldRule}
     *
     * @param crossFieldRule - cross field rule
     * @param target         - target
     */
    @Override
    public void execute(CrossFieldRule crossFieldRule, ValidatableWidget target) {
        if (!context.isValid(target)) return;
        crossFieldRule.setContext(context);
        try {
            crossFieldRule.check(target, secureContext);
        } catch (RuleException e) {
            context.reportViolation(new Violation(target, e.getMessage()));
        } catch (Exception t) {
            reportUnexpectedFailure(t, target);
        }
    }

    /**
     * Executes the given {@code ValidationRule}
     *
     * @param singleFieldRule - single field rule
     * @param target          - target
     */
    @Override
    public void execute(ValidationRule singleFieldRule, ValidatableWidget target) {
        if (context.isFinished(target)) return;
        check(singleFieldRule, target, secureContext);
    }

    /**
     * Executes the given {@code TextConversionRule}
     *
     * @param converterRule - converter rule
     * @param target        - target
     */
    @Override
    @SuppressWarnings("unchecked")
    public void execute(TextConversionRule converterRule, ValidatableWidget target) {
        try {
            context.setConverted(target, converterRule.convert((String) target.getSourceValue(), target, secureContext));
        } catch (RuleException e) {
            context.reportViolation(new Violation(target, e.getMessage()));
        } catch (Exception t) {
            reportUnexpectedFailure(t, target);
        }
    }

    /**
     * Executes the given {@code RequiredRule}
     *
     * @param requiredRule - required rule
     * @param target       - validatable target
     */
    @Override
    public void execute(RequiredRule requiredRule, ValidatableWidget target) {
        if (target.isRequired()) {
            // check for emptiness
            try {
                // requiredRule validates the source value
                requiredRule.check(target.getSourceValue(), target, context);
            } catch (RuleException e) {
                context.reportViolation(new Violation(target, e.getMessage()));
            } catch (Exception t) {
                reportUnexpectedFailure(t, target);
            }
        } else if (RequiredRule.isEmpty(target.getSourceValue())) {
            // this is an empty optional field - stop processing
            context.setFinished(target);
        }
    }

    /**
     * Checks the given {@code ValidationRule}
     *
     * @param rule        - validation rule
     * @param target      - validation target
     * @param ruleContext - rule execution context
     */
    @SuppressWarnings("unchecked")
    private void check(ValidationRule rule, ValidatableWidget target, RuleContext ruleContext) {
        try {
            Object converted = context.getConverted(target);
            rule.check(converted != null ? converted : target.getSourceValue(), target, ruleContext);
        } catch (RuleException e) {
            context.reportViolation(new Violation(target, e.getMessage()));
        } catch (Exception t) {
            reportUnexpectedFailure(t, target);
        }
    }

    /**
     * Logs an error and reports a violation with generic explanation.
     *
     * @param t      - throwable
     * @param target - validation target
     */
    private void reportUnexpectedFailure(Throwable t, ValidatableWidget target) {
        context.reportViolation(new Violation(target, context.messages().getMessage(GENERIC_ERROR_ID)));
        logger.log(Level.SEVERE, t.getMessage(), t);
    }

    /**
     * Cast-safe wrapper for {@code ValidationContext}.
     * The executables are NOT supposed to be able to cast to {@code ValidationContext}.
     */
    private class SecureRuleContext implements RuleContext {

        @Override
        public MessagesResolver messages() {
            return context.messages();
        }

        @Override
        public ConverterFactory converters() {
            return context.converters();
        }

        @Override
        public Set<String> getCategories() {
            return context.getCategories();
        }
    }
}
