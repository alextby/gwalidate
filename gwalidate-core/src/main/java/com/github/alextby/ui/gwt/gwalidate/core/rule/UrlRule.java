package com.github.alextby.ui.gwt.gwalidate.core.rule;

import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.Validatable;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationRule;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * Implements basic url validation
 */
public class UrlRule extends SingleFieldRule implements ValidationRule<String> {

    public static final String ID = "gwt_client_validate_Url";

    public static final String ID_SCHEME = ID + "_Scheme";

    private final RegExp urlPattern = RegExp.compile("^(([\\w]+)://[\\w@.\\-\\_]+(\\.[a-zA-Z]{2,})*(:\\d{1,5})?(/[\\w\\d#!:.?+=&%@!\\_\\-/]+)*)$");

    private Config config;

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void check(String value, Validatable target, RuleContext context) throws RuleException {

        if (value == null || value.length() == 0) {
            // empties are considered valid
            return;
        }

        MatchResult matcher;
        if ((matcher = urlPattern.exec(value)) == null) {
            throw new RuleException(deriveMessage(context.messages(), ID));

        } else if (config != null && config.getScheme() != null) {
            String scheme = matcher.getGroup(2);
            if (scheme != null && !scheme.equals(config.getScheme())) {
                throw new RuleException(deriveMessage(context.messages(), ID_SCHEME, config.getScheme()));
            }
        }
    }

    @Override
    public void execute(RuleExecutor executor, ValidatableWidget target) {
        executor.execute(this, target);
    }

    public interface Config {

        String getScheme();
    }
}
