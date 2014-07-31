package com.github.alextby.ui.gwt.gwalidate.test.cases;

import com.github.alextby.ui.gwt.gwalidate.core.rule.MatchRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RegexpRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.UrlRule;
import com.github.alextby.ui.gwt.gwalidate.test.client.view.form.RulesTestForm;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.BigDecimalBox;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.ValidatedField;
import com.github.alextby.ui.gwt.gwalidate.test.config.GWalidateTestModule;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.*;
import static com.github.alextby.ui.gwt.gwalidate.test.client.view.form.RulesTestForm.*;

/**
 * Rules Form View Test
 */
public class RulesFormViewTest extends GWalidateFormViewTest {

    private RulesTestForm testForm;

    public static class RulesFormViewTestModule extends GWalidateTestModule {
    }

    @Override
    protected RulesTestForm createTestCaseForm() {
        testForm = new RulesTestForm();
        return testForm;
    }

    @Override
    public void beforeTestCase() throws Exception {
        super.beforeTestCase();
        assertValid();
    }

    public void mustApplyRequiredRuleProperly() {

        final ValidatedField<String> sizeRuleField = testForm.getSizeRuleField();
        final TextBox sizeRuleBox = testForm.getSizeRuleBox();
        sizeRuleField.setRequired(true);
        sizeRuleBox.setText("");
        assertNotValid(sizeRuleField);
        sizeRuleField.setRequired(false);
        assertValid();
    }

    @Test
    public void mustApplyRangeRuleProperly() {

        final ValidatedField<Long> rangeField = testForm.getRangeRuleField();
        final LongBox rangeBox = testForm.getRangeRuleBox();

        rangeBox.setText(S_NAN);
        assertNotValid(rangeField);

        rangeBox.setText(sLess(RANGE_MIN));
        assertNotValid(rangeField);

        rangeBox.setText(sGreater(RANGE_MAX));
        assertNotValid(rangeField);

        rangeBox.setText(String.valueOf(RANGE_MIN));
        assertValid();

        rangeBox.setText(String.valueOf(RANGE_MAX));
        assertValid();

        rangeBox.setText( String.valueOf(RANGE_MIN + (RANGE_MAX - RANGE_MIN) / 2) );
        assertValid();
    }

    @Test
    public void mustApplySizeRuleProperly() {

        final ValidatedField<String> sizeField = testForm.getSizeRuleField();
        final TextBox sizeBox = testForm.getSizeRuleBox();

        sizeBox.setText(longerString(SIZE_MAX));
        assertNotValid(sizeField);

        sizeBox.setText(longerString(SIZE_MAX - 10));
        assertValid();
    }

    @Test
    public void mustApplyBigRangeRuleProperly() {

        final ValidatedField<BigDecimal> bigRangeField = testForm.getBigRangeRuleField();
        final BigDecimalBox bigRangeBox = testForm.getBigRangeRuleBox();

        bigRangeBox.setText(bdLess(BIG_DECIMAL_MIN).toPlainString());
        assertNotValid(bigRangeField);

        bigRangeBox.setText(bdGreater(BIG_DECIMAL_MAX).toPlainString());
        assertNotValid(bigRangeField);

        bigRangeBox.setText(new BigDecimal(BIG_DECIMAL_MIN).toPlainString());
        assertValid();

        bigRangeBox.setText(new BigDecimal(BIG_DECIMAL_MAX).toPlainString());
        assertValid();

        bigRangeBox.setText(bdGreater(BIG_DECIMAL_MIN).toPlainString());
        assertValid();
    }

    @Test
    public void mustApplyDigitsRuleProperly() {

        final ValidatedField<Double> digitsField = testForm.getDigitsRuleField();
        final DoubleBox digitsBox = testForm.getDigitsRuleBox();

        String sample1 = "4452.54";
        String sample2 = "42.52533";
        String sample3 = "98.87";
        String sample4 = "-98.84";

        digitsBox.setText(S_NAN);
        assertNotValid(digitsField);

        digitsBox.setText(sample1);
        assertNotValid(digitsField);

        digitsBox.setText(sample2);
        assertNotValid(digitsField);

        digitsBox.setText(sample3);
        assertValid();

        digitsBox.setText(sample4);
        assertValid();
    }

    @Test
    public void mustApplyRegexpRuleProperly() {

        RegexpRule regexpRule = validatorDelegate.rules().regexp("[^\\s]+");
        validatorDelegate.planFor(testForm.getRegexpRuleField()).rule(regexpRule).done();

        testForm.getRegexpRuleBox().setText("this on has spaces ");
        assertNotValid(testForm.getRegexpRuleField());

        testForm.getRegexpRuleBox().setText("thisonehasnospaces");
        assertValid();
    }

    @Test
    public void mustApplyKnownRegexpPattersProperly() {

        final ValidatedField<String> regexpRuleField = testForm.getRegexpRuleField();
        final TextBox regexpBox = testForm.getRegexpRuleBox();

        MatchRule emailRule = validatorDelegate.rules().email();
        validatorDelegate.planFor(regexpRuleField).rule(emailRule).done();
        regexpBox.setText("this#is.not.email");
        assertNotValid(regexpRuleField);
        regexpBox.setText("thisis@valid.email");
        assertValid();
        validatorDelegate.evict(regexpRuleField);

        UrlRule urlRule = validatorDelegate.rules().url();
        validatorDelegate.planFor(regexpRuleField).rule(urlRule).done();
        regexpBox.setText("htt_p:/no-a-url%com");
        assertNotValid(regexpRuleField);
        regexpBox.setText("http://validurl.com");
        assertValid();
        validatorDelegate.evict(regexpRuleField);

        MatchRule zipRule = validatorDelegate.rules().zip();
        validatorDelegate.planFor(regexpRuleField).rule(zipRule).done();
        regexpBox.setText("s23455");
        assertNotValid(regexpRuleField);
        regexpBox.setText("10001");
        assertValid();
        validatorDelegate.evict(regexpRuleField);

        MatchRule noSpacesRule = validatorDelegate.rules().noSpaces();
        validatorDelegate.planFor(regexpRuleField).rule(noSpacesRule).done();
        regexpBox.setText("this on has spaces");
        assertNotValid(regexpRuleField);
        regexpBox.setText("thisonehasnospaces");
        assertValid();
    }

    @Test
    public void mustApplyCustomRulesProperly() {

    }

    @Test
    public void mustApplyCrossFieldRulesProperly() {

    }
}
