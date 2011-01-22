package com.commsen.sample.portlet.chatrooms.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("chatroom_service")
public interface ChatroomService extends RemoteService {
	
	/**
	 * Sends message to server 
	 * 
	 * @param room the chatroom message belongs to
	 * @param message the actual message
	 * @throws ChatroomException if something goes wrong on server side
	 */
	public void sendMessage(String room, String message) throws ChatroomException;
	
	/**
	 * Gets last messages form the server 
	 * 
	 * @param room the chatroom name
	 * @param lastTime the timestamp of the last received message
	 * @return list of all messages newest than <code>lastTime</code>
	 * @throws ChatroomException if something goes wrong on server side
	 */
	public List<MessageDTO> getMessages(String room, Date lastTime) throws ChatroomException;
}
