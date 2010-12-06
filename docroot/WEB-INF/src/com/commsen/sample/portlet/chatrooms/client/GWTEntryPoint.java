package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayString;

public class GWTEntryPoint implements EntryPoint {

	public static native JsArrayString getPortletInstances()
	/*-{
	    return $wnd.chatroomPortletInstances;
	}-*/;

	@Override
	public void onModuleLoad() {
		JsArrayString portletInstances = getPortletInstances();
		for (int i = 0; i < portletInstances.length(); i++) {
			String instanceID = portletInstances.get(i);
			new Chatroom(instanceID);
		}
	}
}
