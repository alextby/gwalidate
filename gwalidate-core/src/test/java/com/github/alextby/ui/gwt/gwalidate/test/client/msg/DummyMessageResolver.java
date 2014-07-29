package com.github.alextby.ui.gwt.gwalidate.test.client.msg;

import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

/**
 * Test {@code com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver}
 */
public class DummyMessageResolver implements MessagesResolver {

    public static final String DEFAULT_MESSAGE = "test-message: {0}, {1}, {2}, {3}";

    @Override
    public String getMessage(String messageId) {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getMessage(String key, Object... params) {
        return StringUtils.formatIndexed(getMessage(key), params);
    }

    @Override
    public String getDetails(String key) {
        return DEFAULT_MESSAGE;
    }

    @Override
    public String getDetails(String key, Object... params) {
        return StringUtils.formatIndexed(getDetails(key), params);
    }
}
