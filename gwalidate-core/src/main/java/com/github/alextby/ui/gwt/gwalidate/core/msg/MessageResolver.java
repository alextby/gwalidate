package com.github.alextby.ui.gwt.gwalidate.core.msg;

import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.inject.Inject;
import org.hibernate.validator.ValidationMessages;

import java.util.MissingResourceException;

/**
 * Helper class for accessing the validation messages.
 *
 */
public class MessageResolver {

    public static final String KEY_INFO_SUFFIX = ".info";

    @Inject
    private ValidationMessages messages;

    public String getMessage(String messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException();
        }
        return getMessageSafely(messageId);
    }

    public String getMessage(String key, Object... params) {
        return StringUtils.formatIndexed(getMessage(key), params);
    }

    public String withGiven(String given, Object... params) {
        return StringUtils.formatIndexed(given, params);
    }

    public String getDetails(String messageKey) {
        if (messageKey == null) {
            throw new IllegalArgumentException();
        }
        return getMessageSafely(messageKey + KEY_INFO_SUFFIX);
    }

    public String getDetails(String messageId, Object... params) {
        return StringUtils.formatIndexed(getDetails(messageId), params);
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
