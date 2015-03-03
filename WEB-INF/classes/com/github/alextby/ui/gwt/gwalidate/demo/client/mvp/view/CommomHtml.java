package com.github.alextby.ui.gwt.gwalidate.demo.client.mvp.view;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * Re-usable static html pieces
 * 
 * @author Alex Tsikhanovich
 */
public class CommomHtml {
	
	public static SafeHtml functionsPanel() {
		return SafeHtmlUtils.fromTrustedString(
		"<h3>Panel Functions</h3>" +
		"<p>Clicking one of the following buttons will control the behaviour of the validation panel:</p>" +
		" <ul>" +
		"  <li><b>Nullify</b> will simply clear any validation errors (validation context)</li>" +
		"  <li>" +
		"   <b>Hard Rescan</b> will purge the existing validation config and rescan everything from scratch." +
		"      Any dynamically added validation rules (incl. cross-field rules) will go away and must be restored." +
		"   </li>" +
		"   <li><b>Rescan</b> simply scans the DOM for newly added widgets.</li>" +
		"</ul>" +
		"Arbitrary control button click combinations will lead to variable result. Make sure you understand the functionality behind each button"
		);
	}
}
