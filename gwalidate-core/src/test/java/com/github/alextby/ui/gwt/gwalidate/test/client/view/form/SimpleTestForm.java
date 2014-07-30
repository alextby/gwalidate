package com.github.alextby.ui.gwt.gwalidate.test.client.view.form;

import com.github.alextby.ui.gwt.gwalidate.core.dom.Range;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.TextBoxField;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.ValidatedField;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Form for {@code SimpleFormViewTest}.
 */
public class SimpleTestForm
       extends Composite
       implements ValidatedTestForm {

    public static final String DEFAULT_NAME = "default-test-name";

    public static final String DEFAULT_AGE = "89";

    public static final int NAME_LENGTH_MAX = 30;

    public static final String S_NAME_LENGTH_MAX = String.valueOf(NAME_LENGTH_MAX);

    public static final int AGE_MIN = 1;

    public static final int AGE_MAX = 150;

    public static final String AGE_ALIAS = "age_field_alias";

    interface TestPanelBinder extends UiBinder<FlowPanel, SimpleTestForm> {
    }

    private static TestPanelBinder UI_BINDER = GWT.create(TestPanelBinder.class);

    @UiField(provided = true)
    TextBoxField name;

    @UiField(provided = true)
    TextBoxField age;

    @UiField
    Range ageRange;

    private ValidatorDelegate delegate;

    public SimpleTestForm() {
        initFields();
        initWidget(UI_BINDER.createAndBindUi(this));
        initRules();
    }

    @Override
    public void initFields() {
        name = createAttached(new TextBoxField());
        age = createAttached(new TextBoxField());
        name.getSourceWidget().setText(DEFAULT_NAME);
        age.getSourceWidget().setText(DEFAULT_AGE);
    }

    @Override
    public void initRules() {
        name.setRequired(true);
        age.setRequired(true);
        ageRange.setMin(AGE_MIN);
        ageRange.setMax(AGE_MAX);
    }

    private static <T extends Widget> T createAttached(T widget) {
        T spiedWidget = spy(widget);
        when(spiedWidget.isAttached()).thenReturn(true);
        return spiedWidget;
    }

    public ValidatedField getNameWidget() {
        return name;
    }

    public ValidatedField getAgeWidget() {
        return age;
    }

    public TextBox getNameBox() {
        return name.getSourceWidget();
    }

    public TextBox getAgeBox() {
        return age.getSourceWidget();
    }

    @Override
    public void setValidatorDelegate(ValidatorDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ValidatorDelegate getValidatorDelegate() {
        return this.delegate;
    }
}
