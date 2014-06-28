package com.github.alextby.ui.gwt.gwalidate.core.convert;

import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;

import java.io.IOException;
import java.text.ParseException;

/**
 * Generic immutable wrapper for a custom parser-renderer pair.
 *
 * @param <T> converter type
 */
public class MyConverter<T> implements Parser<T>, Renderer<T> {

    private final Parser<T> parser;

    private final Renderer<T> renderer;

    public MyConverter(Parser<T> parser, Renderer<T> renderer) {
        this.parser = parser;
        this.renderer = renderer;
    }

    @Override
    public T parse(CharSequence text) throws ParseException {
        return parser.parse(text);
    }

    @Override
    public String render(T object) {
        return renderer.render(object);
    }

    @Override
    public void render(T object, Appendable appendable) throws IOException {
        renderer.render(object, appendable);
    }
}
