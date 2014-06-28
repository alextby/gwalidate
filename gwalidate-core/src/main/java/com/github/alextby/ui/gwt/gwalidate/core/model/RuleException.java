package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Checked exception for the failed {@code ValidationRule} cases.
 */
public class RuleException extends Exception {

    private Violation.Severity severity;

    public RuleException() {
    }

    public RuleException(String s) {
        super(s);
        this.severity = Violation.Severity.ERROR;
    }

    public RuleException(String message, Violation.Severity severity) {
        super(message);
        this.severity = severity;
    }

    public Violation.Severity getSeverity() {
        return severity;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }
}
