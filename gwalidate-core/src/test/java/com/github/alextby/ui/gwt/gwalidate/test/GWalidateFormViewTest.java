package com.github.alextby.ui.gwt.gwalidate.test;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.ValidatorTestFormView;
import com.google.common.collect.Sets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.jukito.JukitoGwtTestRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Base GWalidate Test
 */
@RunWith(JukitoGwtTestRunner.class)
@GwtModule("com.github.alextby.ui.gwt.gwalidate.test.GWalidateTestModule")
public abstract class GWalidateFormViewTest extends GwtTestWithMockito {

    private ValidatorTestFormView view;

    @Inject
    protected ValidationPanel validationPanel;

    @Before
    public void beforeTestCase() throws Exception {
        this.view = new ValidatorTestFormView(createTestForm(), validationPanel);
        assertNotNull(validationPanel);
        assertNotNull(view);
        assertNotNull(view.getValidatorDelegate());
    }

    protected Widget createTestForm() {
        throw new IllegalStateException();
    }

    public ValidatorTestFormView getView() {
        return view;
    }

    protected void assertValid() {
        assertTrue(validationPanel.validate().isValid());
    }

    protected void assertNotValid(ValidatableWidget... widgets) {
        ValidationStatus status = validationPanel.validate();
        assertFalse(status.isValid());
        Set<ValidatableWidget> failedWidgets = Sets.newHashSet(widgets);
        for (Violation violation : status.getViolations()) {
            ValidatableWidget cause = violation.getCause();
            assertTrue(failedWidgets.remove(cause));
        }
        assertTrue(failedWidgets.isEmpty());
    }
}
