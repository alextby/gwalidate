package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.github.alextby.ui.gwt.gwalidate.core.model.ShowsViolations;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;
import java.util.List;

/**
 * A simple form field with {@code ValidatableWidget} features
 */
public class ValidatedTextField
       extends Composite
       implements ValidatableWidget, HasWidgets {

    interface FormFieldBinder extends UiBinder<HTMLPanel, ValidatedTextField> {
    }

    private final FormFieldBinder BINDER = GWT.create(FormFieldBinder.class);

    private boolean required;

    @UiField
    HTMLPanel wrapper;

    @UiField
    Label label;

    @UiField
    TextBox textBox;

    @UiField
    SpanElement help;

    @UiField
    FlowPanel vKeeper;

    @UiConstructor
    public ValidatedTextField() {
        initWidget(BINDER.createAndBindUi(this));
    }

    @Override
    public TextBox getSourceWidget() {
        return textBox;
    }

    @Override
    public void putViolations(List<Violation> violations) {
        if (violations == null || violations.isEmpty()) {
            help.setInnerText("");
        } else {
            Violation firstViolation = violations.iterator().next();
            help.setInnerText(firstViolation.getMessage());
        }
    }

    @Override
    public void sinkViolationsTo(ShowsViolations sink) {
    }

    @Override
    public Object getSourceValue() {
        return textBox.getText();
    }

    @Override
    public boolean isTextual() {
        return true;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    @Override
    public String getLabel() {
        return label.getText();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public void add(Widget widget) {
        vKeeper.add(widget);
    }

    @Override
    public void clear() {
        vKeeper.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return vKeeper.iterator();
    }

    @Override
    public boolean remove(Widget widget) {
        return vKeeper.remove(widget);
    }
}
