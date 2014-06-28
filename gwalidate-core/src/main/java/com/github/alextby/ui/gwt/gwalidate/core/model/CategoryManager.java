package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * API for managing validator categories.
 */
public interface CategoryManager extends HasCategories {

    void clearCategories();

    void unsetCategories(String... categories);

    void setCategories(String... categories);

    void ensureCategories(boolean set, String... categories);
}
