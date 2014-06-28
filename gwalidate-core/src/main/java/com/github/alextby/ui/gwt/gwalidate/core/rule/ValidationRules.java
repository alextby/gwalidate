package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.google.inject.Inject;

import java.math.BigDecimal;

/**
 * Factory for the available validation rules (including parameter variants).
 */
public class ValidationRules {

    private final ValidationRuleFactory ruleFactory;

    @Inject
    public ValidationRules(ValidationRuleFactory factory) {
        this.ruleFactory = factory;
    }

    public RequiredRule required() {
        return ruleFactory.required();
    }

    public RangeRule range(long min, long max, boolean exclusive) {
        RangeRule rule = ruleFactory.range();
        rule.setMin(min);
        rule.setMax(max);
        rule.setExclusive(exclusive);
        return rule;
    }

    public RangeRule range(long min, long max) {
        return this.range(min, max, false);
    }

    public RangeRule range(long max) {
        return this.range(Long.MIN_VALUE, max);
    }

    public SizeRule size(int min, int max, boolean exclusive) {
        SizeRule rule = ruleFactory.size(max);
        rule.setMin(min);
        rule.setExclusive(exclusive);
        return rule;
    }

    public SizeRule size(int min, int max) {
        return this.size(min, max, false);
    }

    public SizeRule size(int max) {
        return this.size(SizeRule.DEFAULT_MIN, max);
    }

    public BigRangeRule bigRange(BigDecimal min, BigDecimal max, boolean exclusive) {
        BigRangeRule rule = ruleFactory.bigRange();
        rule.setMin(min);
        rule.setMax(max);
        rule.setExclusive(exclusive);
        return rule;
    }

    public BigRangeRule bigRange(BigDecimal max) {
        return bigRange(BigDecimal.ZERO, max, false);
    }

    public BigRangeRule bigRange(BigDecimal min, BigDecimal max) {
        return bigRange(min, max, false);
    }

    public DigitsRule digits(int integer, int fraction) {
        return new DigitsRule(integer, fraction);
    }

    public DigitsRule digits(int integer) {
        return digits(integer, DigitsRule.UNLIMITED);
    }

    public DigitsRule digitsFr(int fraction) {
        return digits(DigitsRule.UNLIMITED, fraction);
    }

    public RegexpRule regexp(String pattern) {
        return ruleFactory.regexp(pattern);
    }

    public UrlRule url() {
        return ruleFactory.url();
    }

    public MatchRule email() {
        return ruleFactory.match(Patterns.EMAIL);
    }

    public MatchRule zip() {
        return ruleFactory.match(Patterns.ZIP);
    }

    public MatchRule noSpaces() {
        return ruleFactory.match(Patterns.NO_SPACES);
    }
}
