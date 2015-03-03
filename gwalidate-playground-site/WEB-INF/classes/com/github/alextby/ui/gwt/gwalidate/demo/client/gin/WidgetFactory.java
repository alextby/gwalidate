package com.github.alextby.ui.gwt.gwalidate.demo.client.gin;

import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.ValidatedSimpleForm;
import com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.ValidatedCompositeForm;

public interface WidgetFactory {
    
    ValidatedCompositeForm validatedSubForm();
    
    ValidatedSimpleForm validatedSmallForm();
}
