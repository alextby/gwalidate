package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Wrapper around {@code RegexpRule} giving a limited choice of predefined regexp patterns.
 */
public final class MatchRule extends RegexpRule {

    private final String msgKey;

    @Inject
    MatchRule(@Assisted Patterns pattern) {
        super(pattern.get());
        msgKey = pattern.msgKey();
    }

    @Override
    protected String getMessageKey() {
        return msgKey;
    }
}
