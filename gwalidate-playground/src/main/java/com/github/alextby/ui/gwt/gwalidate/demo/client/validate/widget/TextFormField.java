package com.github.alextby.ui.gwt.gwalidate.demo.client.validate.widget;

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
 * {@code TextBox} with {@code ValidatableWidget} features
 */
public class TextFormField extends Composite implements ValidatableWidget, HasWidgets {

    interface TextFormFieldBinder extends UiBinder<HTMLPanel, TextFormField> { }

    private final TextFormFieldBinder BINDER = GWT.create(TextFormFieldBinder.class);

    private boolean isRequired;

    private final String CSS_CLASS_ERROR = "error";

    private final String REQUIRED_MARK = "*";

    private final String REQUIRED_MARK_MASK = "\\" + REQUIRED_MARK;

    @UiField
    HTMLPanel wrapper;

    @UiField
    Label label;

    @UiField
    TextBox input;

    @UiField
    SpanElement help;

    @UiField
    FlowPanel vKeeper;

    @UiConstructor
    public TextFormField() {
        initWidget(BINDER.createAndBindUi(this));
    }

    @Override
    public Widget getSourceWidget() {
        return this;
    }

    @Override
    public void putViolations(List<Violation> violations) {
        if (violations == null || violations.isEmpty()) {
            wrapper.getElement().removeClassName(CSS_CLASS_ERROR);
            help.setInnerText("");
        } else {
            wrapper.getElement().addClassName(CSS_CLASS_ERROR);
            Violation firstViolation = violations.iterator().next();
            help.setInnerText(firstViolation.getMessage());
        }
    }

    @Override
    public void sinkViolationsTo(ShowsViolations sink) {
    }

    @Override
    public Object getSourceValue() {
        return input.getText();
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

    public void setPlaceholder(String placeholder) {
        input.getElement().setAttribute("placeholder", placeholder);
    }

    public void setRequired(boolean required) {
        String labelText = label.getText();
        if (required && !isRequired()) {
            label.setText(labelText + REQUIRED_MARK);
        } else {
            label.setText(labelText.replaceAll(REQUIRED_MARK_MASK, ""));
        }
        isRequired = required;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
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
