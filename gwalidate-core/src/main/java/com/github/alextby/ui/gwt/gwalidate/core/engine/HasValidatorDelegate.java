package com.github.alextby.ui.gwt.gwalidate.core.engine;


/**
 * Contract for those elements wishing to have the parent {@code ValidatorDelegate} injected
 */
public interface HasValidatorDelegate {

    /**
     * Injects the {@code ValidatorDelegate} instance
     *
     * @param delegate - {@code ValidatorDelegate}
     */
    void setValidatorDelegate(ValidatorDelegate delegate);
}
