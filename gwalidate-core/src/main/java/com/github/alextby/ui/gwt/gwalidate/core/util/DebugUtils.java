package com.github.alextby.ui.gwt.gwalidate.core.util;

import com.google.gwt.core.shared.GWT;

/**
 * Helper methods for tracing out debug information.
 */
public class DebugUtils {

    /**
     * Traces out the given message to the Dev Mode console
     *
     * @param msg - message
     */
    public static void traceDev(String msg) {
        traceDev(msg, null);
    }

    /**
     * Traces out the given message to the Dev Mode console
     *
     * @param msg - message
     * @param t   - Throwable
     */
    public static void traceDev(String msg, Throwable t) {
        if (!StringUtils.isBlank(msg)) {
            if (t != null) GWT.log(msg, t);
            else GWT.log(msg);
        }
    }

    /**
     * Traces out the given message to the browser console.
     *
     * @param msg - message
     */
    public static void trace(String msg) {
        if (!GWT.isProdMode() && !StringUtils.isBlank(msg)) {
            traceNative(msg);
        }
    }

    private static native void traceNative(String msg) /*-{
        if (console && console.log) console.log(msg);
    }-*/;
}
