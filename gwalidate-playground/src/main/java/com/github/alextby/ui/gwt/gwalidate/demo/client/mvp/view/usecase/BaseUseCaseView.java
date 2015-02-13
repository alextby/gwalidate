package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.CommomHtml;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.PanelButtons;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base Use-Case View
 */
public abstract class BaseUseCaseView extends Composite {

    @UiField(provided = true)
    ValidationPanel validationPanel;

    ValidatorDelegate delegate;
    
    @UiField(provided = true)
    PanelButtons panelButtons;

    @UiField
    HTMLPanel functionsPanel;
    
    public BaseUseCaseView(ValidationPanel panel) {
        this.validationPanel = panel;
        this.panelButtons = new PanelButtons(panel);
    }

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);
        functionsPanel.setStyleName("alert alert-info");
        functionsPanel.getElement().setInnerHTML(CommomHtml.functionsPanel().asString());
    }
    
    @Override
    protected void onLoad() {
        super.onLoad();
        this.delegate = validationPanel.getDelegate();
        onValidationReady();
    }

    protected void onValidationReady() {
    }
}
