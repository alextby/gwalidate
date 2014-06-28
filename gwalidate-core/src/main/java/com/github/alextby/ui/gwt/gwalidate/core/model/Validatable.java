package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Any validation-ready element must conform to this contract.
 */
public interface Validatable extends HasRequired {

    /**
     * Returns the _actual_ value of target
     *
     * @return - value
     */
    Object getSourceValue();

    /**
     * Returns whether the validated target has textual source
     *
     * @return - true/false
     */
    boolean isTextual();

    /**
     * Returns the label this validatable comes with
     *
     * @return - string label
     */
    String getLabel();
}
