package com.github.alextby.ui.gwt.gwalidate.demo.client.validate.widget;

import com.github.alextby.ui.gwt.gwalidate.core.model.ShowsViolations;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.*;

import java.util.Iterator;
import java.util.List;

/**
 * Form field with {@code ValidatableWidget} features
 */
public class ValidatedField<T>
        extends Composite
        implements ValidatableWidget, HasWidgets {

    interface FormFieldBinder extends UiBinder<HTMLPanel, ValidatedField> {
    }

    private final FormFieldBinder BINDER = GWT.create(FormFieldBinder.class);

    private boolean isRequired;

    private final String CSS_CLASS_ERROR = "error";

    private final String REQUIRED_MARK = "*";

    private final String REQUIRED_MARK_MASK = "\\" + REQUIRED_MARK;

    @UiField
    HTMLPanel wrapper;

    @UiField
    Label label;

    TakesValue<T> field;

    @UiField
    HTMLPanel fieldPlaceholder;

    @UiField
    SpanElement help;

    @UiField
    FlowPanel vKeeper;

    @UiConstructor
    public ValidatedField() {
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
        return isTextual() ? ((HasText) field).getText() : field.getValue();
    }

    @Override
    public boolean isTextual() {
        return field instanceof HasText;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    @Override
    public String getLabel() {
        return label.getText();
    }

    @UiChild(limit = 1, tagname = "widget")
    public void addValidatedWidget(TakesValue<T> widget) {
        this.field = widget;
        if (field instanceof IsWidget) {
            fieldPlaceholder.clear();
            fieldPlaceholder.add((IsWidget) field);
        }
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
