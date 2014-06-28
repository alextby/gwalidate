package com.github.alextby.ui.gwt.gwalidate.core.dom;

import com.github.alextby.ui.gwt.gwalidate.core.convert.BigDecimalConverter;
import com.github.alextby.ui.gwt.gwalidate.core.convert.ConverterProvider;
import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.rule.BigRangeRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RangeRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RegexpRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.SingleFieldRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.SizeRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConverterRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.UrlRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRuleFactory;
import com.github.alextby.ui.gwt.gwalidate.core.util.StringUtils;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Scans {@code ValidationPlan} for the given {@code ValidatableWidget}
 */
public class DomPlanScanner {

    private final ValidationRuleFactory ruleFactory;

    private final ConverterProvider converterProvider;

    private ValidationPlan.Builder builder;

    @Inject
    public DomPlanScanner(ValidationRuleFactory ruleFactory, ConverterProvider converterProvider) {
        this.ruleFactory = ruleFactory;
        this.converterProvider = converterProvider;
    }

    public ValidationPlan scan(ValidatableWidget target, ValidationPlan.Builder builder) {

        checkNonNull(target, builder);
        this.builder = builder;
        if (target instanceof HasWidgets) {
            HasWidgets targetHasWidgets = (HasWidgets) target;
            Iterator<Widget> children = targetHasWidgets.iterator();
            while (children.hasNext()) {
                Widget child = children.next();
                if (child instanceof HasDomConfiguration) {
                    ((HasDomConfiguration) child).read(this);
                    // make sure to remove this element from DOM
                    children.remove();
                }
            }
        }
        return builder.done();
    }

    public void accept(Range range) {
        checkNonNull(range);
        RangeRule rangeRule = ruleFactory.range();
        rangeRule.setMin(range.getMin());
        rangeRule.setMax(range.getMax());
        rangeRule.setMinOut(range.isMinOut());
        rangeRule.setMaxOut(range.isMaxOut());
        adaptRule(range, rangeRule);
    }

    public void accept(Size size) {
        checkNonNull(size);
        SizeRule sizeRule = ruleFactory.size((int) size.getMax());
        sizeRule.setMin((int) size.getMin());
        sizeRule.setExclusive(size.isExclusive());
        adaptRule(size, sizeRule);
    }

    public void accept(Convert convert) {
        checkNonNull(convert);
        final TextConverter converter = converterProvider.byClazz(convert.getType());
        if (converter != null) {
            // this is safe enough
            @SuppressWarnings("unchecked")
            TextConverterRule rule = new TextConverterRule(converter);
            rule.setMessage(convert.getMsg());
            builder.convert(rule);
        }
    }

    public void accept(BigConvert bigConvert) {
        checkNonNull(bigConvert);
        final Integer roundingMode = bigConvert.getRoundingMode();
        final BigDecimalConverter converter = converterProvider.byClazz(bigConvert.getType());
        if (converter != null) {
            converter.setScale(bigConvert.getScale());
            if (roundingMode != null) converter.setRoundingMode(roundingMode);
            TextConverterRule<BigDecimal> rule = new TextConverterRule<BigDecimal>(converter);
            rule.setMessage(bigConvert.getMsg());
            builder.convert(rule);
        }
    }

    public void accept(Marker marker) {
        checkNonNull(marker);
        builder.alias(marker.getAlias());
    }

    public void accept(Category category) {
        checkNonNull(category);
        builder.category(StringUtils.tokenize(category.getIn(), Category.DELIM));
    }

    public void accept(BigRange bigRange) {
        checkNonNull(bigRange);
        BigRangeRule baseRule = ruleFactory.bigRange();
        baseRule.setMin(new BigDecimal(bigRange.getMin()));
        baseRule.setMax(new BigDecimal(bigRange.getMax()));
        baseRule.setMinOut(bigRange.isMinOut());
        baseRule.setMaxOut(bigRange.isMaxOut());
        adaptRule(bigRange, baseRule);
    }

    public void accept(Regexp regexp) {
        checkNonNull(regexp);
        RegexpRule baseRule = ruleFactory.regexp(regexp.getPattern());
        adaptRule(regexp, baseRule);
    }

    public void accept(Url url) {
        checkNonNull(url);
        UrlRule urlRule = ruleFactory.url();
        adaptRule(url, urlRule);
    }

    public void accept(Match match) {
        checkNonNull(match);
        adaptRule(match, ruleFactory.match(match.getPattern()));
    }

    public void accept(Digits digits) {
        checkNonNull(digits);
        adaptRule(digits, ruleFactory.digits(digits.getInt(), digits.getFraction()));
    }

    private void adaptRule(Rule domRule, SingleFieldRule baseRule) {
        assert domRule != null;
        assert baseRule != null;
        String[] categories = {};
        if (!StringUtils.isBlank(domRule.getCategories())) {
            categories = StringUtils.tokenize(domRule.getCategories(), Category.DELIM);
        }
        baseRule.setMessage(domRule.getMsg());
        builder.rule(baseRule, categories);
    }

    private void checkNonNull(Object... args) {
        if (args == null) throw new IllegalArgumentException();
        for (Object obj : args) {
            if (obj == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}
