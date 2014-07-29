package com.github.alextby.ui.gwt.gwalidate.test;

import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.DynamicPlanTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import org.junit.Before;
import org.junit.Test;

import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.sGreater;
import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.sLess;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm.NAME_LENGTH_MAX;
import static org.junit.Assert.assertNotNull;

/**
 * Dynamic rules test
 */
public class DynamicPlanFormViewTest extends GWalidateFormViewTest {

    private DynamicPlanTestForm testForm;

    public static class DynamicPlanFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected DynamicPlanTestForm createTestCaseForm() {
        testForm = new DynamicPlanTestForm();
        return testForm;
    }

    @Before
    @Override
    public void beforeTestCase() throws Exception {
        super.beforeTestCase();
        validatorDelegate = testForm.getDelegate();
        assertNotNull(validatorDelegate);
    }

    @Test
    public void mustEvictWidgetRulesOnDemand() {

        // the rules apply to the name field
        testForm.getNameBox().setText(null);
        assertNotValid(testForm.getNameWidget());
        // evict the name widget
        validatorDelegate.evict(testForm.getNameWidget());
        assertValid();

        // the rules apply to the age field
        testForm.getAgeBox().setText(S_NAN);
        assertNotValid(testForm.getAgeWidget());
        // evit the age widget
        validatorDelegate.evict(testForm.getAgeWidget());
        assertValid();
    }

    @Test
    public void mustAddRulesToWidgetOnDemand() {

        final int MAX_NAME_LENGTH = 100;

        testForm.getNameBox().setText(S_NAN);
        assertValid();
        validatorDelegate.planFor(testForm.getNameWidget()).rule(validatorDelegate.rules().range(MAX_NAME_LENGTH)).done();
        assertNotValid(testForm.getNameWidget());

        testForm.getNameBox().setText(sGreater(MAX_NAME_LENGTH));
        assertNotValid(testForm.getNameWidget());

        testForm.getNameBox().setText(sLess(MAX_NAME_LENGTH));
        assertValid();
    }

    @Test
    public void mustApplyCategoriesToWidgetOnDemand() {

        final String[] TEST_CATEGORIES = new String[] { "category", "another_category" };
        testForm.getNameBox().setText(null);
        testForm.getNameWidget().setRequired(true);
        assertNotValid(testForm.getNameWidget());

        // widget has categories; none of them are active - widget is skipped
        validatorDelegate.planFor(testForm.getNameWidget()).category(TEST_CATEGORIES).done();
        assertValid();

        // widge is validated since one of its categories is active
        validatorDelegate.categories().ensureCategories(true, TEST_CATEGORIES[0]);
        assertNotValid(testForm.getNameWidget());

        // widget is validated since all its categories are active
        validatorDelegate.categories().ensureCategories(true, TEST_CATEGORIES);
        assertNotValid(testForm.getNameWidget());

        // widget is valid since it's value is valid again
        testForm.getNameBox().setText(SimpleTestForm.DEFAULT_NAME);
        assertValid();

        // widget is validated since it has no categories and it's value is not valid
        testForm.getNameBox().setText(null);
        validatorDelegate.evict(testForm.getNameWidget()); // this will clear the categories
        // restore the origin size rule
        validatorDelegate.planFor(testForm.getNameWidget()).rule(validatorDelegate.rules().size(NAME_LENGTH_MAX)).done();
        assertNotValid(testForm.getNameWidget());
    }
}
