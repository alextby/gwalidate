package com.github.alextby.ui.gwt.gwalidate.test.client.msg;

import com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Test {@code com.github.alextby.ui.gwt.gwalidate.core.msg.MessagesResolver}
 */
public class ResourceBundleMessageResolver implements MessagesResolver {

    private static final String KEY_INFO_SUFFIX = ".info";

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle(
        "com.github.alextby.ui.gwt.gwalidate.core.msg.ValidationMessages", Locale.ENGLISH
    );

    @Override
    public String getMessage(String key) {
        return MESSAGES.getString(normalizeKey(key));
    }

    @Override
    public String getMessage(String key, Object... params) {
        return StringUtils.formatIndexed(getMessage(key), params);
    }

    @Override
    public String getDetails(String key) {
        return getMessage(key + KEY_INFO_SUFFIX);
    }

    @Override
    public String getDetails(String key, Object... params) {
        return StringUtils.formatIndexed(getDetails(key), params);
    }

    private String normalizeKey(String key) {
        return key.replaceAll("_", ".");
    }
}
