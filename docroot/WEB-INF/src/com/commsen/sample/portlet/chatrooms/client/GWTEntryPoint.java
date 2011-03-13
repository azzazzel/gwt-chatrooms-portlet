package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;

public class GWTEntryPoint implements EntryPoint {

	public static native JsArray<ChatroomJsObject> getPortletInstances()
	/*-{
	    return $wnd.chatroomPortletInstances;
	}-*/;

	@Override
	public void onModuleLoad() {
		JsArray<ChatroomJsObject> portletInstances = getPortletInstances();
		for (int i = 0; i < portletInstances.length(); i++) {
			ChatroomJsObject instance = portletInstances.get(i);
			new Chatroom(instance);
		}
	}
}
