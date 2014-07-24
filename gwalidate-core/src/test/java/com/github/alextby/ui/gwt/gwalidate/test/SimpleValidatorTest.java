package com.github.alextby.ui.gwt.gwalidate.test;

import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationDriver;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.msg.MessageResolver;
import com.github.alextby.ui.gwt.gwalidate.core.msg.ValidationMessages;
import com.github.alextby.ui.gwt.gwalidate.test.client.config.GWalidateTestModule;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.SimpleValidatorTestView;
import com.google.inject.Inject;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.jukito.JukitoGwtTestRunner;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Performs a simple validation run over a panel with a few simple widgets
 *
 * @author Alex Tsikhanovich
 */
@RunWith(JukitoGwtTestRunner.class)
@GwtModule("com.github.alextby.ui.gwt.gwalidate.test.GWalidateTestModule")
public class SimpleValidatorTest extends GwtTestWithMockito {

    private SimpleValidatorTestView simpleValidatorTestView;

    @Inject
    private ValidationDriver driver;

    @Inject
    private ValidationPanel validationPanel;

    @Inject
    private MessageResolver messageResolver;

    public static class SimpleValidatorTestModule extends GWalidateTestModule {
    }

    @Before
    public void init() throws Exception {

        simpleValidatorTestView = new SimpleValidatorTestView(validationPanel);
        ValidationMessages messages = mock(ValidationMessages.class);
        when(messages.getString(Matchers.anyString())).thenReturn("A message");
        GwtReflectionUtils.setPrivateFieldValue(messageResolver, "messages", messages);

        // rescan the panel
        validationPanel.rescan();
        // restore the correct values
        simpleValidatorTestView.getNameField().getSourceWidget().setText("name");
        simpleValidatorTestView.getAgeField().getSourceWidget().setText("10");
    }

    @Test
    public void mustRunNormallyWithAllFieldsProperlySet() {

        ValidationStatus validationStatus = validationPanel.validate();
        assertTrue(validationStatus.isValid());
        assertTrue(validationStatus.getViolations().size() == 0);
    }

    @Test
    public void mustFailOnTooLongName() {

        simpleValidatorTestView.getNameField().getSourceWidget().setText("asdasdasdasdasdasdasdasdasdasdasda");
        ValidationStatus validationStatus = validationPanel.validate();
        assertFalse(validationStatus.isValid());
        assertTrue(validationStatus.getViolations().size() == 1);
        assertTrue(validationStatus.getViolations().get(0).getCause() == simpleValidatorTestView.getNameField());
    }

    @Test
    public void mustFailOnMissingName() {

        simpleValidatorTestView.getNameField().getSourceWidget().setText("");
        ValidationStatus validationStatus = validationPanel.validate();
        assertFalse(validationStatus.isValid());
        assertTrue(validationStatus.getViolations().size() == 1);
        assertTrue(validationStatus.getViolations().get(0).getCause() == simpleValidatorTestView.getNameField());
    }

    @Test
    public void mustFailOnOutOfBoundsAge() {

        // negative values
        simpleValidatorTestView.getAgeField().getSourceWidget().setText("-1");
        ValidationStatus validationStatus = validationPanel.validate();
        assertFalse(validationStatus.isValid());
        assertTrue(validationStatus.getViolations().size() == 1);
        assertTrue(validationStatus.getViolations().get(0).getCause() == simpleValidatorTestView.getAgeField());

        simpleValidatorTestView.getAgeField().getSourceWidget().setText("151");
        validationStatus = validationPanel.validate();
        assertFalse(validationStatus.isValid());
        assertTrue(validationStatus.getViolations().size() == 1);
        assertTrue(validationStatus.getViolations().get(0).getCause() == simpleValidatorTestView.getAgeField());
    }
}
