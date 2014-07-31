package com.github.alextby.ui.gwt.gwalidate.test.client.widget;

import com.github.alextby.ui.gwt.gwalidate.core.engine.HasValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.model.ShowsViolations;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;
import java.util.List;

/**
 * A simple form field with {@code ValidatableWidget} features
 */
public class ValidatedField<T>
       extends Composite
       implements ValidatableWidget, HasWidgets, HasValidatorDelegate {

    interface FormFieldBinder extends UiBinder<HTMLPanel, ValidatedField> {
    }

    private final FormFieldBinder BINDER = GWT.create(FormFieldBinder.class);

    private boolean required;

    @UiField
    HTMLPanel wrapper;

    @UiField
    Label label;

    @UiField
    SimplePanel valueBoxSpot;

    ValueBoxBase<T> valueBox;

    @UiField
    SpanElement help;

    @UiField
    FlowPanel body;

    ValidatorDelegate validatorDelegate;

    @UiConstructor
    public ValidatedField() {
        initWidget(BINDER.createAndBindUi(this));
    }

    public ValidatedField(ValueBoxBase<T> valueBox) {
        this();
        addValueBox(valueBox);
    }

    @Override
    public ValueBoxBase<T> getSourceWidget() {
        return valueBox;
    }

    @Override
    public void putViolations(List<Violation> violations) {
        // we not gonna test this since it's fully up to the widget
    }

    @Override
    public void sinkViolationsTo(ShowsViolations sink) {
    }

    public T getValue() {
        return valueBox.getValue();
    }

    @Override
    public String getSourceValue() {
        return valueBox.getText();
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

    @UiChild(tagname = "valuebox", limit = 1)
    public void addValueBox(ValueBoxBase<T> valueBox) {
        this.valueBox = valueBox;
        this.valueBoxSpot.setWidget(valueBox);
    }

    @Override
    public void add(Widget widget) {
        body.add(widget);
    }

    @Override
    public void clear() {
        body.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return body.iterator();
    }

    @Override
    public boolean remove(Widget widget) {
        return body.remove(widget);
    }

    @Override
    public void setValidatorDelegate(ValidatorDelegate delegate) {
        this.validatorDelegate = delegate;
    }

    public ValidatorDelegate getValidatorDelegate() {
        return validatorDelegate;
    }
}
