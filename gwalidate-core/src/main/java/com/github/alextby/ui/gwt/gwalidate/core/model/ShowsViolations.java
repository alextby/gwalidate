package com.github.alextby.ui.gwt.gwalidate.core.model;

import java.util.List;

/**
 * Shows a set of given validation {@code Violation}s.
 */
public interface ShowsViolations {

    /**
     * Pushes the list of {@code Violation}s to the element
     *
     * @param violations - violations
     */
    void putViolations(List<Violation> violations);

    /**
     * Delegates showing validation errors to the given sink
     *
     * @param sink - sink
     */
    void sinkViolationsTo(ShowsViolations sink);
}
