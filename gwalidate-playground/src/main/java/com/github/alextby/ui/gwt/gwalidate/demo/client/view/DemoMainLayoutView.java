package com.github.alextby.ui.gwt.gwalidate.demo.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

/**
 * Demo Main Layout Panel
 *
 *
 */
public class DemoMainLayoutView extends Composite {

    interface Binder extends UiBinder<HTMLPanel, DemoMainLayoutView> {
    }

    @Inject
    public DemoMainLayoutView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    HTMLPanel rootPanel;
}
