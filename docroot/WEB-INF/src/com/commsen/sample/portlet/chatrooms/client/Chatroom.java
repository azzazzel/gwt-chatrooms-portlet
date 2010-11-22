package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Chatroom {

	private String portletInstanceId = "";
	
	public Chatroom(String instanceId) {
		this.portletInstanceId = instanceId;
		RootPanel.get("chatrooms-portlet-" + portletInstanceId).add(new HTML("This is the <b>GWT chat rooms</b> portlet."));
	}
	
}
