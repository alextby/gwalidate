package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.shared.Renderer;

import java.io.IOException;

/**
 * Simply renders any object using its {@link Object#toString()} implementation
 * in a null-pointer-safe manner (which gives an empty string by default).
 * @param <T> - type of the value
 */
public class ToStringRenderer<T> implements Renderer<T> {

    @Override
    public String render(T object) {
        return object != null ? object.toString() : "";
    }

    @Override
    public void render(T object, Appendable appendable) throws IOException {
        appendable.append(render(object));
    }
}
