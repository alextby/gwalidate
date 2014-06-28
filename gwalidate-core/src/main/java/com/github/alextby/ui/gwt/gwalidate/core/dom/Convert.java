package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;

/**
 * <p>
 * DOM-level {@code TextConverter} config element.
 * </p>
 *
 *
 */
public class Convert extends SimplePanel implements HasDomConfiguration {

    private Class<? extends TextConverter> type;

    // optional custom message
    private String msg;

    @UiConstructor
    public Convert(Class<? extends TextConverter> type) {
        this.type = type;
        setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        // ignore
    }

    public Class<? extends TextConverter> getType() {
        return type;
    }

    @Override
    public void read(DomPlanScanner planBuilder) {
        planBuilder.accept(this);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
