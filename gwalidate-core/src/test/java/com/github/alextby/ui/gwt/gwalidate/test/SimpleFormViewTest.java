package com.github.alextby.ui.gwt.gwalidate.test;

import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import com.google.gwt.user.client.ui.Widget;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Simple validator test
 */
public class SimpleFormViewTest extends GWalidateFormViewTest {

    private SimpleTestForm simpleTestForm;

    public static class SimpleFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected Widget createTestForm() {
        simpleTestForm = new SimpleTestForm();
        return simpleTestForm;
    }

    @Before
    @Override
    public void beforeTestCase() throws Exception {
        super.beforeTestCase();
        assertNotNull(simpleTestForm.getNameWidget().getValidatorDelegate());
        assertNotNull(simpleTestForm.getAgeWidget().getValidatorDelegate());
    }

    @Test
    public void mustRunNormallyWithAllFieldsProperlySet() {
        assertValid();
    }

    @Test
    public void mustFailOnTooLongName() {
        simpleTestForm.getNameBox().setText("asdasdasdasdasdasdasdasdasdasdasda");
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
        simpleTestForm.getAgeBox().setText("-1");
        assertNotValid(simpleTestForm.getAgeWidget());

        simpleTestForm.getAgeBox().setText("151");
        assertNotValid(simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustFailOnNotANumberAge() {
        simpleTestForm.getAgeBox().setText("nan");
        assertNotValid(simpleTestForm.getAgeWidget());
    }

    @Test
    public void mustFailIfMultipleWidgetsAreWrong() {
        simpleTestForm.getNameBox().setText("");
        simpleTestForm.getAgeBox().setText("nan");
        assertNotValid(simpleTestForm.getNameWidget(), simpleTestForm.getAgeWidget());
    }
}
