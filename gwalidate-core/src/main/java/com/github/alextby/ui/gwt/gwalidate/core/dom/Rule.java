package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Base class for all declarative validation configuration rules
 */
public abstract class Rule extends SimplePanel implements HasDomConfiguration {

    // optional custom message
    private String msg;

    // optional categories
    private String categories;

    public Rule() {
        super.setVisible(false);
    }

    @Override
    public void setVisible(boolean visible) {
        // ignore
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
