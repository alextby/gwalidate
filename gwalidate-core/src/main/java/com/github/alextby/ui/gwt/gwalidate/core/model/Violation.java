package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Constraint violation.
 */
public class Violation {

    public enum Severity {

        INFO,

        WARN,

        ERROR,

        CRITICAL
    }

    private String message;

    private String info;

    private Severity severity = Severity.ERROR;

    private ValidatableWidget cause;

    public Violation(ValidatableWidget cause, String message) {
        this.cause = cause;
        this.message = message;
    }

    public Violation(ValidatableWidget cause, String message, Severity severity) {
        this.cause = cause;
        this.message = message;
        this.severity = severity;
    }

    public Violation(ValidatableWidget cause, String message, String info) {
        this.cause = cause;
        this.message = message;
        this.info = info;
    }

    public Violation(ValidatableWidget cause, String message, String info, Severity severity) {
        this.cause = cause;
        this.message = message;
        this.info = info;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public String getInfo() {
        return info;
    }

    public Severity getSeverity() {
        return severity;
    }

    public ValidatableWidget getCause() {
        return cause;
    }
}
