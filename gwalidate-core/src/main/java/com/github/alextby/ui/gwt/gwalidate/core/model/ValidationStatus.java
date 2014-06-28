package com.github.alextby.ui.gwt.gwalidate.core.model;

import java.util.List;

/**
 * Validation Status API.
 *
 */
public interface ValidationStatus {

    /**
     * Returns an unmodifiable list of violations (in order of appearance)
     *
     * @return - list of {@code Violation}s
     */
    List<Violation> getViolations();

    /**
     * Returns whether validation's been a success
     *
     * @return - true/false
     */
    boolean isValid();
}
