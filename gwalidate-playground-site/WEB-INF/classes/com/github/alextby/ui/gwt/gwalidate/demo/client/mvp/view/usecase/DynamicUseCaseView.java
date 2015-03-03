package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view.usecase;

import com.github.alextby.ui.gwt.gwalidate.core.convert.ToStringRenderer;
import com.github.alextby.ui.gwt.gwalidate.core.engine.ValidationPanel;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleContext;
import com.github.alextby.ui.gwt.gwalidate.core.model.RuleException;
import com.github.alextby.ui.gwt.gwalidate.core.model.ValidatableWidget;
import com.github.alextby.ui.gwt.gwalidate.core.rule.CrossFieldRule;
import com.github.alextby.ui.gwt.gwalidate.demo.client.validate.widget.ValidatedField;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Arrays;
import java.util.LinkedList;

@Singleton
public class DynamicUseCaseView extends BaseUseCaseView implements IDynamicUseCaseView {

    public static final String CATEGORY_PHONE_AND_EMAIL = "PHONE_AND_EMAIL";

    interface DynamicUseCaseBinder extends UiBinder<Widget, DynamicUseCaseView> {
    }

    private Presenter presenter;

    @UiField
    ValidatedField<String> nameField;

    @UiField
    CheckBox nameRequiredCheckBox;

    @UiField
    CheckBox phoneAndEmailValidatedCheckBox;

    @UiField
    ValidatedField<Gender> genderField;

    @UiField(provided = true)
    ValueListBox<Gender> genderListBox;

    @UiField
    ValidatedField<Category> categoryField;

    @UiField(provided = true)
    ValueListBox<Category> categoryListBox;

    @UiField
    FlowPanel urlsPanel;

    private LinkedList<ValidatedField<String>> urlFields = new LinkedList<ValidatedField<String>>();

    @UiField
    Button addUrlButton;

    @UiField
    Button minusUrlButton;
    
    @UiField
    Button restoreBikiniRuleBtn;

    private final CrossFieldRule NON_BIKINI_RULE = new CrossFieldRule() {
        @Override
        public void check(ValidatableWidget target, RuleContext context) throws RuleException {

            if (isValid(genderField) &&
                genderListBox.getValue() == Gender.MALE && categoryListBox.getValue() == Category.BIKINI) {
                throw new RuleException("Men are not supposed to wear bikini");
            }
        }
    };
    
    @Inject
    public DynamicUseCaseView(DynamicUseCaseBinder binder, ValidationPanel validationPanel) {
        super(validationPanel);
        this.validationPanel.scanOnAttach(true);
        this.genderListBox = new ValueListBox<Gender>(new ToStringRenderer<Gender>());
        this.categoryListBox = new ValueListBox<Category>(new ToStringRenderer<Category>());
        initWidget(binder.createAndBindUi(this));
        this.genderListBox.setAcceptableValues(Arrays.asList(Gender.values()));
        this.genderListBox.setValue(Gender.MALE);
        this.categoryListBox.setAcceptableValues(Arrays.asList(Category.values()));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("nameRequiredCheckBox")
    void onNameCheckBoxClick(ClickEvent clickEvent) {
        nameField.setRequired(nameRequiredCheckBox.getValue());
    }

    @UiHandler("phoneAndEmailValidatedCheckBox")
    void onPhoneAndEmailBoxClick(ClickEvent event) {
        validationPanel.getDelegate().categories().ensureCategories(
            phoneAndEmailValidatedCheckBox.getValue(), CATEGORY_PHONE_AND_EMAIL
        );
    }
    
    @UiHandler("restoreBikiniRuleBtn")
    void onRestoreBikiniRuleClick(ClickEvent event) {
        validationPanel.getDelegate().evict(categoryField);
        validationPanel.getDelegate().planFor(categoryField).crossrule(NON_BIKINI_RULE).done();
    }

    @Override
    protected void onValidationReady() {
        delegate.planFor(categoryField).crossrule(NON_BIKINI_RULE).done();
    }

    @UiHandler("addUrlButton")
    void onAddUrlButtonClick(ClickEvent event) {
        ValidatedField<String> urlField = new ValidatedField<String>();
        urlField.setLabel("No_spaces");
        urlField.addValidatedWidget(new TextBox());
        urlField.setRequired(true);
        urlsPanel.add(urlField);
        urlFields.add(urlField);
        delegate.planFor(urlField).rule(delegate.rules().noSpaces()).done();
    }

    @UiHandler("minusUrlButton")
    void onMinusUrlButtonClick(ClickEvent event) {
        ValidatedField<String> latest = urlFields.getLast();
        if (latest != null) {
            urlsPanel.remove(latest);
            urlFields.removeLast();
            delegate.evict(latest);
        }
    }
}
