package com.github.alextby.ui.gwt.gwalidate.test.cases;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RangeRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.SizeRule;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.ValidatedTextField;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import com.google.gwt.user.client.ui.Widget;
import org.junit.Before;
import org.junit.Test;

import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.*;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm.*;
import static org.junit.Assert.*;

/**
 * Simple validator test
 */
public class SimpleFormViewTest extends GWalidateFormViewTest {

    private SimpleTestForm simpleTestForm;

    public static class SimpleFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected Widget createTestCaseForm() {
        simpleTestForm = new SimpleTestForm();
        return simpleTestForm;
    }

    @Before
    @Override
    public void beforeTestCase() throws Exception {
        super.beforeTestCase();
        assertNotNull(simpleTestForm.getNameWidget().getValidatorDelegate());
        assertNotNull(simpleTestForm.getAgeWidget().getValidatorDelegate());
        validatorDelegate = simpleTestForm.getDelegate();
        assertNotNull(validatorDelegate);
    }

    @Test
    public void mustRunNormallyWithAllFieldsProperlySet() {
        assertValid();
    }

    @Test
    public void mustFailOnTooLongName() {
        simpleTestForm.getNameBox().setText(longerString(NAME_LENGTH_MAX));
        assertNotValid(simpleTestForm.getNameWidget());
    }

    @Test
    public void mustFailOnMissingName() {
        simpleTestForm.getNameBox().setText("");
        assertNotValid(simpleTestForm.getNameWidget());
        simpleTestForm.getNameBox().setText(null);
        assertNotValid(simpleTestForm.getNameWidget());
    }

    @Test
    public void mustFailOnOutOfBoundsAge() {

        // negative values
        simpleTestForm.getAgeBox().setText(sLess(AGE_MIN));
        assertNotValid(simpleTestForm.getAgeWidget());

        simpleTestForm.getAgeBox().setText(sGreater(AGE_MAX));
        assertNotValid(simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustFailOnNotANumberAge() {
        simpleTestForm.getAgeBox().setText(S_NAN);
        assertNotValid(simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustFailIfMultipleWidgetsAreWrong() {
        simpleTestForm.getNameBox().setText("");
        simpleTestForm.getAgeBox().setText(S_NAN);
        assertNotValid(simpleTestForm.getNameWidget(), simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustReactToRequiredStatusChange() {
        // set optional
        ValidatedTextField nameWidget = simpleTestForm.getNameWidget();
        nameWidget.setRequired(false);
        // check empty
        simpleTestForm.getNameBox().setText("");
        assertValid();
        // check null
        simpleTestForm.getNameBox().setText(null);
        assertValid();
        // set required again
        nameWidget.setRequired(true);
        assertNotValid(nameWidget);
    }

    @Test
    public void mustDetectFullDomConfiguration() {
        assertTrue(validatorDelegate.isValidated(simpleTestForm.getNameWidget()));
        assertTrue(validatorDelegate.isValidated(simpleTestForm.getAgeWidget()));
        ValidatableWidget lookedUpByAlias = validatorDelegate.fields().byAlias(SimpleTestForm.AGE_ALIAS);
        assertNotNull(lookedUpByAlias);
        assertEquals(lookedUpByAlias, simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustAcceptCustomMessagesInDomConfiguration() {

        String msg = "Custom name message with size {0}";
        SizeRule sizeRuleWithMsg = validatorDelegate.rules().size(NAME_LENGTH_MAX);
        sizeRuleWithMsg.setMessage(msg);
        final ValidatedTextField nameField = simpleTestForm.getNameWidget();
        validatorDelegate.evict(nameField);
        validatorDelegate.planFor(nameField).rule(sizeRuleWithMsg).done();
        simpleTestForm.getNameBox().setText(longerString(NAME_LENGTH_MAX));
        ValidationStatus vStatus = assertNotValid(nameField);
        Violation firstViolation = vStatus.getViolations().get(0);
        String msgExpected = "Custom name message with size " + NAME_LENGTH_MAX;
        assertEquals(firstViolation.getMessage(), msgExpected);
        simpleTestForm.getNameBox().setText(SimpleTestForm.DEFAULT_NAME);

        msg = "Custom age message with min {0} and max {1}";
        RangeRule rangeRuleWithMsg = validatorDelegate.rules().range(AGE_MIN, AGE_MAX);
        rangeRuleWithMsg.setMessage(msg);
        final ValidatedTextField ageField = simpleTestForm.getAgeWidget();
        validatorDelegate.evict(ageField);
        validatorDelegate.planFor(ageField).rule(rangeRuleWithMsg).done();
        simpleTestForm.getAgeBox().setText(sGreater(AGE_MAX));
        vStatus = assertNotValid(ageField);
        firstViolation = vStatus.getViolations().get(0);

        msgExpected = "Custom age message with min " + AGE_MIN + " and max " + AGE_MAX;
        assertEquals(firstViolation.getMessage(), msgExpected);
    }
}
