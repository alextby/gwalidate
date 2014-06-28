package com.github.alextby.ui.gwt.gwalidate.core.rule;

/**
 * Index of the built-in regexp patterns.
 */
public enum Patterns {

    NO_SPACES("[^\\s]+", "gwt_client_validate_NoSpaces"),

    EMAIL("(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,5})", "gwt_client_validate_Email"),

    PHONE("^[\\d\\(\\) \\-\\+\\#]{5,}$", "gwt_client_validate_Phone"),

    ZIP("^\\d{5}(?:[-\\s]\\d{4})?$", "gwt_client_validate_Zip");

    private String pattern;

    private String flags;

    private String msgKey;

    Patterns(String pattern, String msgKey) {
        this(pattern, msgKey, null);
    }

    Patterns(String pattern, String msgKey, String flags) {
        this.pattern = pattern;
        this.msgKey = msgKey;
        this.flags = flags;
    }

    public String get() {
        return pattern;
    }

    public String getFlags() {
        return flags;
    }

    String msgKey() {
        return msgKey;
    }
}
