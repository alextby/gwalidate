package com.github.alextby.ui.gwt.gwalidate.core.engine;

import com.github.alextby.ui.gwt.gwalidate.core.convert.TextConverter;
import com.github.alextby.ui.gwt.gwalidate.core.dom.DomPlanScanner;
import com.github.alextby.ui.gwt.gwalidate.core.model.CategoryManager;
import com.github.alextby.ui.gwt.gwalidate.core.model.FieldRegistry;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidationStatus;
import com.github.alextby.ui.gwt.gwalidate.core.model.Violation;
import com.github.alextby.ui.gwt.gwalidate.core.plan.IsPlanStep;
import com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan;
import com.github.alextby.ui.gwt.gwalidate.core.rule.RuleExecutor;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConversionRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.TextConverterRule;
import com.github.alextby.ui.gwt.gwalidate.core.rule.ValidationRules;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * <p>
 * Drives the widget validation process starting at the given scanpoint.<br/>
 * A scanpoint is typically a starting point (a panel, widget etc) where the
 * driver start scanning the declarative configuration from.<br/>
 * Scanning starts automatically when {@code AttachEvent} triggers for the scanpoint;
 * so this is essential that the scanning point gets attached to the DOM otherwise the validation
 * never sees the DOM configuration.</br>
 * It's always possible to set validation rules manually using
 * {@link #planFor(com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget)}<br/>
 * The driver supports validation categories. A category is a flag for turning on/off the validatable
 * widgets selectively. Once a validatable supports any of the currently active categories (or supports none)
 * it gets validated (otherwise not).<br/>
 * The driver automatically injects an instance of {@code ValidatorDelegate} into those widgets implementing
 * the {@code HasValidatorDelegate} contract (again, make sure you set the scanpoint correctly); besides such widgets
 * must be no deeper than any validatable widget since scanning in-depth stops at {@code ValidatableWidget}.
 * </p>
 *
 * @see Validator
 * @see ValidatorDelegate
 */
public final class ValidationDriver implements AttachEvent.Handler, Validator {

    // dom plan scanner
    private DomPlanScanner domPlanScanner;

    // validation rules factory
    private ValidationServices services;

    // validation context
    private ValidationContext context;

    // scanpoint
    private Panel scanpoint;

    // validation config
    private ValidationConfig config;

    // scanpoint attach/detach handler
    private HandlerRegistration attachHandler;

    // composite visitor
    private CompositeAdapter compositeAdapter;

    // should skip hidden fields ?
    private boolean skipHidden = false;

    private boolean scanOnAttach = false;

    // execution errors LOG
    private static Logger LOG = Logger.getLogger("ValidationDriver");

    @Inject
    public ValidationDriver(final DomPlanScanner domPlanScanner,
                            final ValidationServices validationServices,
                            final CompositeAdapter compositeAdapter) {

        this.config = new ValidationConfig();
        this.context = new ValidationContext(config, validationServices);
        this.domPlanScanner = domPlanScanner;
        this.services = validationServices;
        this.compositeAdapter = compositeAdapter;
    }

    // -------------------------------------------------------------------------------------
    //  Public Methods
    // -------------------------------------------------------------------------------------

    public void setScanpoint(Panel scanpoint) {

        if (scanpoint == null) {
            throw new IllegalArgumentException("Null scan starting point is not allowed");
        }
        // we've got this scan point already
        if (scanpoint == this.scanpoint) {
            return;
        }

        if (attachHandler != null) {
            attachHandler.removeHandler();
        }
        this.scanpoint = scanpoint;
        attachHandler = this.scanpoint.addAttachHandler(this);
    }

    @Override
    public void onAttachOrDetach(AttachEvent event) {

        if (scanpoint == null) {
            // this is acceptable
            return;
        }
        if (event.getSource() != scanpoint) {
            throw new IllegalStateException("Wrong scanpoint identified");
        }

        if (event.isAttached()) {
            LOG.fine("Attaching validator. Scanpoint = " + scanpoint.getElement().getTagName());
            reset(false);
            if (scanOnAttach) {
                // re-scan the DOM
                scanDom();
            }

        } else {
            LOG.fine("Detaching validator");
            reset(false);
        }
    }

    @Override
    public void reset() {
        reset(false);
    }

    /**
     * Kicks off the actual validation process based on the current configuration.<br/>
     * One should not attempt to perform validation until either having DOM scanned (onAttach)
     * or injecting some manual validation plans since it most likely won't do anything.
     *
     * @return - {@code ValidationStatus}
     */
    @Override
    public ValidationStatus run() {

        // reset the context
        context.reset();

        MultiValidationStatus totalResult = new MultiValidationStatus(context);
        Set<ValidationDriver> validators = config.getInnerValidators();
        // call the inner validators
        for (Validator validator : validators) {
            ValidationStatus result = validator.run();
            totalResult.violations.addAll(result.getViolations());
        }

        runLocal();

        // propogate field errors
        Map<ValidatableWidget, List<Violation>> violations = context.getViolationsGrouped();
        for (Map.Entry<ValidatableWidget, List<Violation>> entry : violations.entrySet()) {
            if (entry != null) {
                List<Violation> fieldViolations = entry.getValue();
                entry.getKey().putViolations(fieldViolations);
                totalResult.violations.addAll(fieldViolations);
            }
        }

        return totalResult;
    }

    public CategoryManager categories() {
        return context;
    }

    /**
     * Returns an instance of {@code ValidationPlan.Builder} for the given
     * target. Make sure to call {@link com.github.alextby.ui.gwt.gwalidate.core.plan.ValidationPlan.Builder#done()}
     * once setConverted building otherwise it will never be accepted.
     *
     * @param planFormTarget - target validatable
     * @return - plan builder
     */
    public ValidationPlan.Builder planFor(final ValidatableWidget planFormTarget) {

        if (planFormTarget == null) {
            throw new IllegalArgumentException("Can not build plan for a null target");
        }

        return new ValidationPlan.Builder(planFormTarget) {

            @Override
            public <T> ValidationPlan.Builder convert(TextConverter<T> converter) {
                if (converter == null) {
                    throw new IllegalArgumentException();
                }
                forceConverter(planFormTarget.getSourceWidget(), converter);
                super.convert(converter);
                return this;
            }

            @Override
            public ValidationPlan.Builder convert(TextConversionRule converterRule) {
                if (converterRule == null) {
                    throw new IllegalArgumentException();
                }
                forceConverter(planFormTarget.getSourceWidget(), converterRule.getConverter());
                super.convert(converterRule);
                return this;
            }

            @Override
            public ValidationPlan done() {
                ValidationPlan plan = super.done();
                config.mergePlanIn(plan, false);
                return plan;
            }
        };
    }

    @Override
    public boolean isValidated(ValidatableWidget target) {
        return config.isValidated(target);
    }

    /**
     * Unvalidates the given target by removing it from the
     * validation list. Nulls are silently ignored.
     *
     * @param target - validatable target
     */
    public void evict(ValidatableWidget target) {
        if (target != null) {
            config.unconfigure(target);
        }
    }

    /**
     * Returns factory for the built-in validation rules
     *
     * @return {@code ValidationRules}
     */
    public ValidationRules rules() {
        return services.getRules();
    }

    @Override
    public FieldRegistry fields() {
        return config;
    }

    @Override
    public void setSkipHidden(boolean skipHidden) {
        this.skipHidden = skipHidden;
    }

    public void setScanOnAttach(final boolean scanOnAttach) {
        this.scanOnAttach = scanOnAttach;
    }

    // -------------------------------------------------------------------------------------
    //  Protected Methods
    // -------------------------------------------------------------------------------------

    /**
     * Resets the driver state:<br/>
     * - soft mode only resets the context;<br/>
     * - hard mode resets both the context and the config;
     *
     * @param hard - soft or hard
     */
    protected void reset(boolean hard) {
        for (ValidationDriver validator : config.getInnerValidators()) {
            validator.reset(hard); // clear context for inner validators first
        }
        context.reset(hard); // clear self context
        if (hard) {
            config.reset(); // reset the config if in hard mode
        }
    }

    @Override
    public void rescan() {
        rescan(false);
    }

    /**
     * (Re)scans the validation configuration.<br/>
     *
     * @throws IllegalStateException if the the starting point is currently unattached.
     */
    @Override
    public void rescan(boolean force) {
        reset(force);
        scanDom();
    }

    /**
     * (Re)scans the validation configuration for the given starting scan point
     *
     * @param scanPoint - scan point
     */
    protected void rescan(Panel scanPoint) {
        setScanpoint(scanPoint);
        rescan(true);
    }

    protected ValidationConfig getConfig() {
        return config;
    }

    // -------------------------------------------------------------------------------------
    //  Private Methods
    // -------------------------------------------------------------------------------------

    private void runLocal() {
        // prepare the validatables
        prepare();
        List<ValidatableWidget> validatables = config.getValidatables();
        // process validatables one-by-one (in order of appearance)
        for (ValidatableWidget target : validatables) {
            validateRules(target);
        }
        // now check the cross-field rules
        for (ValidatableWidget crossTarget : validatables) {
            validateCrossRules(crossTarget);
        }
    }

    @SuppressWarnings("unchecked")
    private void prepare() {

        // process all targets one-by-one
        for (ValidatableWidget target : config.getValidatables()) {

            if ((skipHidden && !isVisible(target)) || !context.isCategoryActive(target)) {
                // stop processing this target early if:
                // - we skip hiddens and this one IS hidden
                // - this target is not category-active
                context.setFinished(target);
                continue;
            }

            final RuleExecutor ruleExecutor = context.getRuleExecutor();
            // execute the required field and check if not further processing is needed
            ruleExecutor.execute(services.getRules().required(), target);

            if (!context.isFinished(target)) {
                // run the converter
                TextConversionRule converterRule = config.getPlan(target).getConverterRule();
                if (converterRule != null) {
                    ruleExecutor.execute(converterRule, target);
                }
            }
        }
    }

    private void validateRules(ValidatableWidget target) {

        assert target != null;

        RuleExecutor ruleExecutor = context.getRuleExecutor();
        ValidationPlan plan = config.getPlan(target);

        // check the field-level rules
        for (IsPlanStep nextStep : plan.getPlanSteps()) {
            if (!context.isStepCategoryActive(nextStep)) {
                continue;
            }
            nextStep.getRule().execute(ruleExecutor, target);
        }
    }

    private void validateCrossRules(ValidatableWidget crossTarget) {

        assert crossTarget != null;

        ValidationPlan plan = config.getPlan(crossTarget);
        RuleExecutor ruleExecutor = context.getRuleExecutor();

        for (IsPlanStep nextStep : plan.getCrossPlanSteps()) {
            if (!context.isStepCategoryActive(nextStep)) {
                continue;
            }
            nextStep.getRule().execute(ruleExecutor, crossTarget);
        }
        context.setFinished(crossTarget);
    }

    /**
     * Checks if the contained widget is _indeed_ visibile or not.<br/>
     *
     * @return - visibile?
     */
    private boolean isVisible(ValidatableWidget target) {

        if (target instanceof HasVisibility) {

            // up-traverse
            HasVisibility vis = (HasVisibility) target;
            // quick win
            if (!vis.isVisible()) {
                return false;
            }

            Widget widget = target.asWidget();
            // unattached widgets are considered invisibile
            if (!widget.isAttached()) {
                return false;
            }

            while (widget.getParent() != null) {

                if (widget == scanpoint) {
                    // we've just reached the topmost parent - reflect its visibility
                    return scanpoint.isVisible();

                } else if (!widget.isVisible()) {
                    // invisible
                    return false;
                }
                widget = widget.getParent();
            }
        }

        return true;
    }

    private boolean isAttached(ValidatableWidget target) {
        assert target != null;
        return target.asWidget().isAttached();
    }

    /**
     * Scans DOM for the given scanpoint
     */
    private void scanDom() {

        LOG.fine("DOM scan started");
        LinkedList<Widget> queue = new LinkedList<Widget>();

        for (Widget widget : scanpoint) {
            queue.add(widget);
        }

        while (!queue.isEmpty()) {

            Widget widget = queue.removeFirst();

            if (widget instanceof HasValidatorDelegate) {
                config.injectDelegate(this, (HasValidatorDelegate) widget);
            }

            if (widget instanceof ValidationPanel) {
                acceptPanel((ValidationPanel) widget);
                // we wanna stop at this point - the internals are
                // handled by this validation panel
                continue;

            } else if (widget instanceof ValidatableWidget) {
                acceptWidget((ValidatableWidget) widget);
                // we still wanna scan in-depth since there may be inner validatables
            }

            // go inside
            if (widget instanceof HasWidgets) {
                for (Widget o : (HasWidgets) widget) {
                    queue.add(o);
                }

            } else if (widget instanceof Composite) {
                // special case for composites:
                // need to get its top-most widget in order to scan in-depth
                Widget topLevelWidget = compositeAdapter.getCompositeWidget((Composite) widget);
                if (topLevelWidget instanceof HasWidgets) {
                    for (Widget o : (HasWidgets) topLevelWidget) {
                        queue.add(o);
                    }
                }
            }
        }
    }


    @SuppressWarnings("unchecked")
    private void processConverter(ValidatableWidget validatableWidget) {

        if (validatableWidget == null || validatableWidget.getSourceWidget() == null) {
            return;
        }
        ValidationPlan plan = config.getPlan(validatableWidget);
        Widget sourceWidget = validatableWidget.getSourceWidget();
        if (plan != null && plan.getConverterRule() == null) {
            TextConverter converter = forceConverter(sourceWidget);
            if (converter != null) {
                ValidationPlan.extend(plan).convert(new TextConverterRule(converter)).done();
            }
        }
    }

    private TextConverter forceConverter(Widget sourceWidget) {
        return forceConverter(sourceWidget, null);
    }

    private TextConverter forceConverter(Widget sourceWidget, TextConverter givenConverter) {

        TextConverter<?> result = givenConverter;

        if (sourceWidget != null && sourceWidget instanceof ValueBoxBase) {
            ValueBoxBase valueBox = (ValueBoxBase) sourceWidget;

            if (givenConverter != null) {
                services.getConverterPlugin().plugIn(valueBox, givenConverter);

            } else {
                TextConverter autoConverter = services.getValueBoxConverters().forBox(valueBox);
                if (autoConverter != null) {
                    services.getConverterPlugin().plugIn(valueBox, autoConverter);
                    result = autoConverter;
                }
            }
        }
        return result;
    }

    private void acceptPanel(ValidationPanel validationPanel) {
        // stop scanning this sub-tree - we have an inner validator
        LOG.fine("Validaton panel detected");
        final Validator validator = validationPanel.validator;
        if (validator != null && validator instanceof ValidationDriver) {
            ValidationDriver validationDriver = (ValidationDriver) validator;
            config.acceptValidator(validationDriver);
            validationDriver.scanDom(); // force scan dom
        }
    }

    private void acceptWidget(ValidatableWidget target) {
        if (config.getPlan(target) == null || !config.isDomConfigured(target)) {
            // we never scan DOM configuration twice for the same target
            // since we know that frequent attach/unattach requests will take place
            LOG.fine("Validatable widget detected");
            config.mergePlanIn(domPlanScanner.scan(target, planFor(target)), true);
            processConverter(target);
        }
    }

    /**
     * {@code ValidationStatus} with multiple {@code ValidationDriver}s.
     */
    private class MultiValidationStatus implements ValidationStatus {

        private List<Violation> violations;

        private MultiValidationStatus(ValidationStatus rootStatus) {
            assert rootStatus != null;
            violations = new LinkedList<Violation>(rootStatus.getViolations());
        }

        @Override
        public List<Violation> getViolations() {
            return Collections.unmodifiableList(violations);
        }

        @Override
        public boolean isValid() {
            return violations.size() == 0;
        }
    }
}
