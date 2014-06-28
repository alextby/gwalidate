package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;

/**
 * Client-side GWT Validator contract.
 *
 * @see ValidatorDelegate
 */
public interface Validator extends ValidatorDelegate {

    /**
     * Validates the previously set configuration
     *
     * @return - {@code ValidationStatus}
     */
    ValidationStatus run();

    /**
     * Sets the "skip hidden" flag
     *
     * @param skipHidden - skip hidden ?
     */
    void setSkipHidden(boolean skipHidden);

    /**
     * Clears all previous results
     */
    void reset();

    void setScanOnAttach(boolean scanOnAttach);

    void rescan();

    void rescan(boolean force);
}
