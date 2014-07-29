package com.github.alextby.ui.gwt.gwalidate.test.client.view.form;

import com.github.alextby.ui.gwt.gwalidate.core.dom.Category;
import com.google.gwt.uibinder.client.UiField;

/**
 * Form for {@code SimpleFormViewTest}.
 */
public class CategoriesTestForm extends SimpleTestForm {

    public static final String CATEGORY_ADULTS = "adults";

    public static final String CATEGORY_MEN = "men";

    @UiField
    Category ageCategories;

    public Category getAgeCategories() {
        return ageCategories;
    }
}
