package com.github.alextby.ui.gwt.gwalidate.core.msg;

import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.inject.Inject;

import java.util.MissingResourceException;

/**
 * Helper class for accessing the validation messages.
 */
public class DefaultMessageResolver implements MessagesResolver {

    private static final String KEY_INFO_SUFFIX = "_info";

    @Inject
    private ValidationMessages messages;

    @Override
    public String getMessage(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getMessageSafely(key);
    }

    @Override
    public String getMessage(String key, Object... params) {
        return StringUtils.formatIndexed(getMessage(key), params);
    }

    @Override
    public String getDetails(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getMessageSafely(key + KEY_INFO_SUFFIX);
    }

    @Override
    public String getDetails(String key, Object... params) {
        return StringUtils.formatIndexed(getDetails(key), params);
    }

    private String getMessageSafely(String key) {
        try {
            return messages.getString(key);
        } catch (MissingResourceException excpt) {
            // warn this
        }
        return null;
    }
}
