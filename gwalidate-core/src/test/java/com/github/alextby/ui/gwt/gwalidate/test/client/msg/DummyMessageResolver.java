package com.github.alextby.ui.gwt.gwalidate.test.client.msg;

import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;

/**
 * Test {@code com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver}
 */
public class DummyMessageResolver implements MessagesResolver {

    public static final String DEFAULT_MESSAGE = "test-message";

    @Override
    public String getMessage(String messageId) {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getMessage(String key, Object... params) {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getDetails(String key) {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getDetails(String key, Object... params) {
        return DEFAULT_MESSAGE;
    }
}
