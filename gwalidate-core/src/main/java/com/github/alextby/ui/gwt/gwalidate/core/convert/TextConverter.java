package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

/**
 * Glues {@code Renderer} and {@code Parser} contracts together.
 *
 * @param <T> - field type
 */
public interface TextConverter<T> extends Renderer<T>, Parser<T> {

    Class<T> getTypeClass();

    String getId();
}
