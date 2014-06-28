package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.google.inject.assistedinject.Assisted;

/**
 * GIN-powered validation rule factory.
 */
public interface ValidationRuleFactory {

    RequiredRule required();

    RangeRule range();

    BigRangeRule bigRange();

    SizeRule size(@Assisted Integer size);

    UrlRule url();

    RegexpRule regexp(@Assisted String pattern);

    MatchRule match(@Assisted com.github.alextby.ui.gwt.gwalidate.core.rule.Patterns pattern);

    DigitsRule digits(@Assisted("integer") Integer integer, @Assisted("fraction") Integer fraction);
}
