package com.github.alextby.ui.gwt.gwalidate.test.client.view.form;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterType;
import com.github.alextby.ui.gwt.gwalidate.core.dom.Convert;
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
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.github.alextby.ui.gwt.gwalidate.test.client.util.TestUtils.mockWidget;

/**
 * Converter Test Form
 */
public class ConverterTestForm extends Composite
       implements ValidatedTestForm {

    interface ConverterTestFormBinder extends UiBinder<FlowPanel, ConverterTestForm> {
    }

    private static ConverterTestFormBinder UI_BINDER = GWT.create(ConverterTestFormBinder.class);

    public ConverterTestForm() {
        initFields();
        initWidget(UI_BINDER.createAndBindUi(this));
        initRules();
    }

    @UiField(provided = true)
    ValidatedField<String> stringField;

    @UiField(provided = true)
    ValidatedField<Integer> integerField;

    @UiField(provided = true)
    ValidatedField<Long> longField;

    @UiField(provided = true)
    ValidatedField<BigInteger> bigIntegerField;

    @UiField(provided = true)
    ValidatedField<Double> doubleField;

    @UiField(provided = true)
    ValidatedField<BigDecimal> bigDecimalField;

    private ValidatorDelegate validatorDelegate;

    @Override
    public void initFields() {
        stringField = mockWidget(new TextBoxField());
        integerField = mockWidget(new ValidatedField<Integer>(new IntegerBox()));
        longField = mockWidget(new ValidatedField<Long>(new LongBox()));
        doubleField = mockWidget(new ValidatedField<Double>(new DoubleBox()));
        bigIntegerField = mockWidget(new ValidatedField<BigInteger>(new BigIntegerBox()));
        bigDecimalField = mockWidget(new ValidatedField<BigDecimal>(new BigDecimalBox()));
    }

    @Override
    public void initRules() {
        stringField.add(makeConvert(ConverterType.STRING));
        integerField.add(makeConvert(ConverterType.INTEGER));
        longField.add(makeConvert(ConverterType.LONG));
        doubleField.add(makeConvert(ConverterType.DOUBLE));
        bigIntegerField.add(makeConvert(ConverterType.BIG_INTEGER));
        bigDecimalField.add(makeConvert(ConverterType.BIG_DECIMAL));
    }

    @Override
    public ValidatorDelegate getValidatorDelegate() {
        return validatorDelegate;
    }

    @Override
    public void setValidatorDelegate(ValidatorDelegate validatorDelegate) {
        this.validatorDelegate = validatorDelegate;
    }

    private Convert makeConvert(ConverterType type) {
        return new Convert(type);
    }

    public ValidatedField<Integer> getIntegerField() {
        return integerField;
    }

    public IntegerBox getIntegerBox() {
        return (IntegerBox) integerField.getSourceWidget();
    }

    public ValidatedField<Long> getLongField() {
        return longField;
    }

    public LongBox getLongBox() {
        return (LongBox) longField.getSourceWidget();
    }

    public ValidatedField<String> getStringField() {
        return stringField;
    }

    public TextBox getStringBox() {
        return (TextBox) stringField.getSourceWidget();
    }

    public ValidatedField<BigInteger> getBigIntegerField() {
        return bigIntegerField;
    }

    public BigIntegerBox getBigIntegerBox() {
        return (BigIntegerBox) bigIntegerField.getSourceWidget();
    }

    public ValidatedField<Double> getDoubleField() {
        return doubleField;
    }

    public DoubleBox getDoubleBox() {
        return (DoubleBox) doubleField.getSourceWidget();
    }

    public ValidatedField<BigDecimal> getBigDecimalField() {
        return bigDecimalField;
    }

    public BigDecimalBox getBigDecimalBox() {
        return (BigDecimalBox) bigDecimalField.getSourceWidget();
    }
}
