package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AboutView extends Composite implements IAboutView {

	interface AboutViewBinder extends UiBinder<Widget, AboutView> { }
	
	private Presenter presenter;

    @Inject
	public AboutView(AboutViewBinder binder) {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
