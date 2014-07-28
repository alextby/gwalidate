package com.github.alextby.ui.gwt.gwalidate.core.util;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * String-related helper methods
 */
public final class StringUtils {

    /**
     * Checks whether the given string is either null or empty
     *
     * @param value - tested candidate
     * @return - true/false
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Replaces key-marked placeholders with the given value in the given template string
     *
     * @param template - string template
     * @param key      - key
     * @param value    - value
     * @param <T>      - type of the value
     * @return - formated string
     */
    public static <T> String format(String template, String key, T value) {
        return template.replaceAll("\\{" + key + "\\}", value.toString());
    }

    public static String format(String value, String... items) {
        final Map<String, Object> mapOfItems = new HashMap<String, Object>();
        for (int i = 0; i < items.length - 1; i++) {
            mapOfItems.put(items[i], items[i + 1]);
        }
        return format(value, mapOfItems);
    }

    public static String format(String value, Map<String, Object> params) {
        String result = value;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            result = result.replaceAll("\\{" + entry.getKey() + "\\}", entry.getValue().toString());
        }
        return result;
    }

    /**
     * Replaces the given parameters in the index-based manner.
     * This implementation is rather naive and inefficient; one should not consider using it
     * for number of arguments > 3!
     *
     * @param template - original template string
     * @param params   - parameters
     * @return - formatted string
     */
    public static String formatIndexed(String template, Object... params) {
        checkNotNull(template);
        checkNotNull(params);
        String result = template;
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) continue;
            result = result.replaceAll("\\{" + i + "\\}", params[i].toString());
        }
        return result;
    }

    /**
     * Tokenizes the given string with the given token regexp.<br/>
     * Returns an empty array for an empty string.
     *
     * @param value - value
     * @param token - token
     * @return - array of tokens
     * @throws IllegalArgumentException if either value or token is null
     */
    public static String[] tokenize(String value, String token) {
        checkNotNull(value);
        checkNotNull(token);
        if (value.trim().length() == 0) {
            return new String[] { };
        }
        return value.split(token);
    }

    /**
     * Greps a substring at a given group index.
     * By default assumes the group index = 1 (the very first match) and "i" (ignore case) as a match flag.
     *
     * @param text    - source text
     * @param pattern - match pattern
     * @return matched substring or null if nothing found
     */
    public static String grepGroup(String text, String pattern) {
        return grepGroup(checkNotNull(text), checkNotNull(pattern), 1, "i");
    }

    public static String grepGroup(String text, String pattern, int groupIndex) {
        return grepGroup(text, pattern, groupIndex, "i");
    }

    /**
     * Greps a substring at a given group index.
     *
     * @param text       - source text
     * @param pattern    - match pattern
     * @param groupIndex - group index (0 for the full match)
     * @param flags      - match flags
     * @return - matched substring or null if nothing found
     */
    public static String grepGroup(String text, String pattern, int groupIndex, String flags) {
        checkNotNull(text);
        checkNotNull(pattern);
        RegExp regExp;
        if (flags != null) {
            regExp = RegExp.compile(pattern, flags);
        } else {
            regExp = RegExp.compile(pattern);
        }
        MatchResult matcher = regExp.exec(text);
        if (matcher != null && matcher.getGroupCount() > groupIndex) {
            return matcher.getGroup(groupIndex);
        } else {
            return null;
        }
    }
}
