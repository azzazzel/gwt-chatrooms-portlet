package com.commsen.sample.portlet.chatrooms.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChatroomServiceAsync {

	public void sendMessage (String room, String message, AsyncCallback<String> callback);

	public void getMessages(String room, Date lastTime, AsyncCallback<List<MessageDTO>> callback);
}
