package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class GWTEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get("chatrooms-portlet").add(new HTML("This is the <b>GWT chat rooms</b> portlet."));
	}

}
