package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.google.gwt.user.client.ui.TextBox;

/**
 * A simple shortcut for {@code ValidatedField} with a {@code TextBox}
 */
public class TextBoxField extends ValidatedField<String> {

    public TextBoxField() {
        super(new TextBox());
    }

    public TextBox getSourceWidget() {
        return (TextBox) super.getSourceWidget();
    }
}
