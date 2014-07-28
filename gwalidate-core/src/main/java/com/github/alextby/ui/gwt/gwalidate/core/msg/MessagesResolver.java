package com.github.alextby.ui.gwt.gwalidate.core.msg;

/**
 * Valiation messages resolution API
 */
public interface MessagesResolver {

    String getMessage(String key);

    String getMessage(String key, Object... params);

    String getDetails(String key);

    String getDetails(String key, Object... params);
}
