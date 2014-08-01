package com.github.alextby.ui.gwt.gwalidate.test.client.view.form;

import com.github.alextby.ui.gwt.gwalidate.core.dom.BigRange;
import com.github.alextby.ui.gwt.gwalidate.core.dom.Digits;
import com.github.alextby.ui.gwt.gwalidate.core.dom.Range;
import com.github.alextby.ui.gwt.gwalidate.core.dom.Size;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidatorDelegate;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.BigDecimalBox;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.BigIntegerBox;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.TextBoxField;
import com.github.alextby.ui.gwt.gwalidate.test.client.widget.ValidatedField;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.mockWidget;

/**
 * Converter Test Form
 */
public class RulesTestForm extends Composite
       implements ValidatedTestForm {

    public static final int RANGE_MIN = 8;

    public static final int RANGE_MAX = 16;

    public static final int SIZE_MAX = 15;

    public static final String BIG_DECIMAL_MIN = "-0.1562";

    public static final String BIG_DECIMAL_MAX = "9.536";

    public static final String BIG_INTEGER_MIN = "101010101010";

    public static final String BIG_INTEGER_MAX = "101010111110";

    public static final int DIGITS_INTEGER = 3;

    public static final int DIGITS_FRACTION = 4;

    interface RulesTestFormBuilder extends UiBinder<FlowPanel, RulesTestForm> {
    }

    private static RulesTestFormBuilder UI_BINDER = GWT.create(RulesTestFormBuilder.class);

    public RulesTestForm() {
        initFields();
        initWidget(UI_BINDER.createAndBindUi(this));
        initRules();
    }

    @UiField(provided = true)
    ValidatedField<Long> rangeRuleField;

    @UiField(provided = true)
    ValidatedField<String> sizeRuleField;

    @UiField(provided = true)
    ValidatedField<BigDecimal> bigRangeRuleField;

    @UiField(provided = true)
    ValidatedField<Double> digitsRuleField;

    @UiField(provided = true)
    ValidatedField<String> regexpRuleField;

    @UiField(provided = true)
    ValidatedField<BigInteger> bigIntegerField;

    private ValidatorDelegate validatorDelegate;

    @Override
    public void initFields() {
        rangeRuleField = mockWidget(new ValidatedField<Long>(new LongBox()));
        sizeRuleField = mockWidget(new TextBoxField());
        bigRangeRuleField = mockWidget(new ValidatedField<BigDecimal>(new BigDecimalBox()));
        digitsRuleField = mockWidget(new ValidatedField<Double>(new DoubleBox()));
        regexpRuleField = mockWidget(new TextBoxField());
        bigIntegerField = mockWidget(new ValidatedField<BigInteger>(new BigIntegerBox()));
    }

    @Override
    public void initRules() {
        Range range = new Range(RANGE_MIN, RANGE_MAX);
        rangeRuleField.add(range);
        Size size = new Size(SIZE_MAX);
        sizeRuleField.add(size);
        BigRange bigRange = new BigRange(BIG_DECIMAL_MIN, BIG_DECIMAL_MAX);
        bigRangeRuleField.add(bigRange);
        Digits digitsRule = new Digits(DIGITS_INTEGER, DIGITS_FRACTION);
        digitsRuleField.add(digitsRule);
        BigRange bigIntegerRangeRule = new BigRange(BIG_INTEGER_MIN, BIG_INTEGER_MAX);
        bigIntegerRangeRule.setMinOut(true);
        bigIntegerRangeRule.setMaxOut(true);
        bigIntegerField.add(bigIntegerRangeRule);
    }

    @Override
    public ValidatorDelegate getValidatorDelegate() {
        return validatorDelegate;
    }

    @Override
    public void setValidatorDelegate(ValidatorDelegate validatorDelegate) {
        this.validatorDelegate = validatorDelegate;
    }

    public ValidatedField<Long> getRangeRuleField() {
        return rangeRuleField;
    }

    public LongBox getRangeRuleBox() {
        return (LongBox) rangeRuleField.getSourceWidget();
    }

    public ValidatedField<String> getSizeRuleField() {
        return sizeRuleField;
    }

    public TextBox getSizeRuleBox() {
        return (TextBox) sizeRuleField.getSourceWidget();
    }

    public ValidatedField<BigDecimal> getBigRangeRuleField() {
        return bigRangeRuleField;
    }

    public BigDecimalBox getBigRangeRuleBox() {
        return (BigDecimalBox) bigRangeRuleField.getSourceWidget();
    }

    public ValidatedField<Double> getDigitsRuleField() {
        return digitsRuleField;
    }

    public DoubleBox getDigitsRuleBox() {
        return (DoubleBox) digitsRuleField.getSourceWidget();
    }

    public ValidatedField<String> getRegexpRuleField() {
        return regexpRuleField;
    }

    public TextBox getRegexpRuleBox() {
        return (TextBox) regexpRuleField.getSourceWidget();
    }

    public ValidatedField<BigInteger> getBigIntegerField() {
        return bigIntegerField;
    }

    public BigIntegerBox getBigIntegerBox() {
        return (BigIntegerBox) bigIntegerField.getSourceWidget();
    }
}
