package com.github.alextby.ui.gwt.gwalidate.test.client.view;

import com.github.alextby.ui.gwt.gwalidate.core.dom.Range;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.ValidatedTextField;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * View for {@code SimpleValidatorTest}.
 *
 * @author Alex Tsikhanovich
 */
public class SimpleValidatorTestView extends Composite {

    interface TestPanelBinder extends UiBinder<HTMLPanel, SimpleValidatorTestView> {
    }

    private static TestPanelBinder uiBinder = GWT.create(TestPanelBinder.class);

    @UiField(provided = true)
    ValidationPanel validationPanel;

    @UiField(provided = true)
    ValidatedTextField name;

    @UiField(provided = true)
    ValidatedTextField age;

    @UiField
    Range ageRange;

    @UiField
    Button validateButton;

    public SimpleValidatorTestView(ValidationPanel panel) {

        this.validationPanel = panel;
        initFields();
        initWidget(uiBinder.createAndBindUi(this));
        initRules();
    }

    private void initFields() {

        name = createAttached(new ValidatedTextField());
        name.setRequired(true);

        age = createAttached(new ValidatedTextField());
        age.setRequired(true);
    }

    private void initRules() {
        ageRange.setMin(0);
        ageRange.setMax(150);
    }

    private static <T extends Widget> T createAttached(T widget) {
        T spiedWidget = spy(widget);
        when(spiedWidget.isAttached()).thenReturn(true);
        return spiedWidget;
    }



    public ValidatedTextField getNameField() {
        return name;
    }

    public ValidatedTextField getAgeField() {
        return age;
    }
}
