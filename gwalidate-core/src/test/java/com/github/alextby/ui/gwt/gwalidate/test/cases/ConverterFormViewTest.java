package com.github.alextby.ui.gwt.gwalidate.test.cases;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigDecimalConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.BigIntegerConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterException;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterPlugin;
import com.github.alextby.ui.gwt.gwalidate.core.convert.DoubleConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.IntegerConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.LongConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextToStringConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.ConverterTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.BigDecimalBox;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.BigIntegerBox;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

import static com.googlecode.gwt.test.utils.GwtReflectionUtils.getPrivateFieldValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Converters Test
 */
public class ConverterFormViewTest extends GWalidateFormViewTest {

    private ConverterTestForm testForm;

    public static class ConverterFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected ConverterTestForm createTestCaseForm() {
        testForm = new ConverterTestForm();
        return testForm;
    }

    @Test
    public void mustConvertIntegersCorrectly() {

        IntegerBox integerBox = testForm.getIntegerBox();
        assertConverter(integerBox, IntegerConverter.class);

        int sampleValue = 2536;
        integerBox.setText(String.valueOf(sampleValue));
        assertValid();
        assertTrue(sampleValue == integerBox.getValue());

        integerBox.setValue(sampleValue);
        assertTrue(integerBox.getText().length() > 0);

        integerBox.setText(S_NAN);
        assertNotValid(testForm.getIntegerField());
    }

    @Test
    public void mustConvertLongsCorrectly() {

        LongBox longBox = testForm.getLongBox();
        assertConverter(longBox, LongConverter.class);

        long sampleValue = 253635453463463L;
        testForm.getLongBox().setText(String.valueOf(sampleValue));
        assertValid();
        assertTrue(sampleValue == testForm.getLongBox().getValue());

        longBox.setValue(sampleValue);
        assertTrue(longBox.getText().length() > 0);

        testForm.getLongBox().setText(S_NAN);
        assertNotValid(testForm.getLongField());
    }

    @Test
    public void mustConvertDoublesCorrectly() {

        double sampleValue = 25.5632;
        DoubleBox doubleBox = testForm.getDoubleBox();
        assertConverter(doubleBox, DoubleConverter.class);

        doubleBox.setText(String.valueOf(sampleValue));
        assertValid();
        assertTrue(sampleValue == doubleBox.getValue());

        doubleBox.setValue(sampleValue);
        assertTrue(doubleBox.getText().length() > 0);

        doubleBox.setText(S_NAN);
        assertNotValid(testForm.getDoubleField());
    }

    @Test
    public void mustConvertBigIntegersCorrectly() {

        String sampleValue = "532423545345394583475349583475394583746928357293582";
        BigIntegerBox bigIntegerBox = testForm.getBigIntegerBox();
        assertConverter(bigIntegerBox, BigIntegerConverter.class);

        bigIntegerBox.setText(sampleValue);
        assertValid();
        assertTrue(new BigInteger(sampleValue).compareTo(bigIntegerBox.getValue()) == 0);

        bigIntegerBox.setValue(new BigInteger(sampleValue));
        assertTrue(bigIntegerBox.getText().length() > 0);

        bigIntegerBox.setText(S_NAN);
        assertNotValid(testForm.getBigIntegerField());
    }

    @Test
    public void mustConveterBigDecimalsCorrectly() {

        String sampleValue = "3445346565464563423.56456452235235233";
        BigDecimalBox bigDecimalBox = testForm.getBigDecimalBox();
        assertConverter(bigDecimalBox, BigDecimalConverter.class);

        bigDecimalBox.setText(sampleValue);
        assertValid();
        assertTrue(new BigDecimal(sampleValue).compareTo(bigDecimalBox.getValue()) == 0);

        bigDecimalBox.setValue(new BigDecimal(sampleValue));
        assertTrue(bigDecimalBox.getText().length() > 0);

        bigDecimalBox.setText(S_NAN);
        assertNotValid(testForm.getBigDecimalField());
    }

    @Test
    public void mustAcceptDynamicConveters() {

        TextBox stringBox = testForm.getStringBox();
        assertConverter(stringBox, TextToStringConverter.class);

        String sampleValue = "       this is a sample value   ";
        testForm.getStringBox().setText(sampleValue);
        assertValid();
        assertEquals(sampleValue, testForm.getStringBox().getValue());
        validatorDelegate.planFor(testForm.getStringField()).convert(new TestTrimmingConverter()).done();
        assertConverter(stringBox, TestTrimmingConverter.class);
        assertValid();
        // not the new converter will trim the sampled value - assert that
        assertEquals(sampleValue.trim(), testForm.getStringBox().getValue());
    }

    @Test
    public void mustTranslateCustomConverterExceptionsIntoRuleExceptions() {

        TextBox stringBox = testForm.getStringBox();
        validatorDelegate.planFor(testForm.getStringField()).convert(new TestTrimmingConverter()).done();
        assertConverter(stringBox, TestTrimmingConverter.class);
        stringBox.setText("a");
        ValidationStatus status = assertNotValid(testForm.getStringField());
        assertEquals(status.getViolations().get(0).getMessage(), TestTrimmingConverter.CONVERTER_ERROR_MSG);
    }

    private <T> void assertConverter(ValueBoxBase<T> widget, Class<? extends TextConverter<T>> classOfConverter) {
        // make sure we've got the converter auto-inserted
        assertTrue(getPrivateFieldValue(widget, ConverterPlugin.FIELD_PARSER).getClass() == classOfConverter);
        assertTrue(getPrivateFieldValue(widget, ConverterPlugin.FIELD_RENDERER).getClass() == classOfConverter);
    }

    private static class TestTrimmingConverter extends TextToStringConverter {

        public static final String CONVERTER_ERROR_MSG = "something went wrong";

        @Override
        public String parse(CharSequence text) throws ParseException {
            if (text != null && text.length() > 3) {
                return text.toString().trim();
            } else {
                throw new ConverterException(CONVERTER_ERROR_MSG);
            }
        }
    }
}
