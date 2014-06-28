package com.github.alextby.ui.gwt.gwalidate.core.model;

/**
 * Defines the rule priorities (order in which the rules are executed).
 */
public enum RulePriority {

    // before anything happens
    REQUIRED,

    // checks the type
    CONVERSION,

    // validation
    DEFAULT,

    // cross-field validation
    CROSS_FIELD,

    // after all
    I_DONT_CARE
}
