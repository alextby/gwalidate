package com.github.alextby.ui.gwt.gwalidate.test.cases;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.core.model.CategoryManager;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.CategoriesTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.CategoriesTestForm.CATEGORY_ADULTS;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.CategoriesTestForm.CATEGORY_MEN;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm.DEFAULT_AGE;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.SimpleTestForm.DEFAULT_NAME;
import static org.junit.Assert.assertNotNull;

/**
 * Validation Categories Test
 */
public class CatergoriesFormViewTest extends GWalidateFormViewTest {

    private ValidatorDelegate validatorDelegate;

    private CategoriesTestForm testForm;

    public static class CatergoriesFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected CategoriesTestForm createTestCaseForm() {
        testForm = new CategoriesTestForm();
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
    public void mustValidateWhenAtLeastOneCategoryActive() {
        validatorDelegate.categories().setCategories(CATEGORY_ADULTS);
        testForm.getNameBox().setText(DEFAULT_NAME);
        testForm.getAgeBox().setText(DEFAULT_AGE);
        assertValid();
    }

    @Test
    public void mustSkipWidgetsWithCategoriesWhenNoneSet() {
        testForm.getNameBox().setText(DEFAULT_NAME);
        assertValid();
    }

    @Test
    public void mustReactToClearCategoriesCommand() {

        testForm.getAgeBox().setText(null);
        validatorDelegate.categories().setCategories(CATEGORY_ADULTS);
        assertNotValid(testForm.getAgeWidget());
        // now clear and check if it's valid again
        validatorDelegate.categories().clearCategories();
        assertValid();
    }

    @Test
    public void mustReactToDynamicSetAndUnsetCommands() {

        testForm.getAgeBox().setText(null);
        validatorDelegate.categories().setCategories(CATEGORY_ADULTS, CATEGORY_MEN);
        assertNotValid(testForm.getAgeWidget());

        validatorDelegate.categories().unsetCategories(CATEGORY_MEN);
        assertNotValid(testForm.getAgeWidget());

        validatorDelegate.categories().unsetCategories(CATEGORY_ADULTS);
        assertValid();
    }

    @Test
    public void mustReactToEnsureCategoriesCommand() {

        CategoryManager categoryManager = validatorDelegate.categories();
        // 1 category is dynamically set or unset
        testForm.getAgeBox().setText(null);
        Random rand = new Random();
        categoryManager.clearCategories();
        boolean categorySet = rand.nextBoolean();
        categoryManager.ensureCategories(categorySet, CATEGORY_ADULTS);
        if (categorySet) {
            assertNotValid(testForm.getAgeWidget());
        } else {
            assertValid();
        }
        // 2nd case: there will be one active category in any case
        categoryManager.setCategories(CATEGORY_ADULTS, CATEGORY_MEN);
        categoryManager.ensureCategories(rand.nextBoolean(), CATEGORY_ADULTS);
        // the age is missing in any case since there is at least MEN category active
        assertNotValid(testForm.getAgeWidget());
    }

    @Test
    public void mustParseCategoriesStringTrimmed() {

        testForm.getNameBox().setText(null);
        testForm.getAgeBox().setText("");

        CategoryManager categoryManager = validatorDelegate.categories();
        categoryManager.setCategories(CATEGORY_MEN);
        assertNotValid(testForm.getNameWidget(), testForm.getAgeWidget());

        categoryManager.clearCategories();
        assertValid();
    }

    @Test
    public void mustNotReactToNonExistingCategories() {

        testForm.getNameBox().setText(null);
        testForm.getAgeBox().setText("");

        CategoryManager categoryManager = validatorDelegate.categories();
        categoryManager.setCategories(String.valueOf(System.currentTimeMillis()));
        assertValid();

    }
}
