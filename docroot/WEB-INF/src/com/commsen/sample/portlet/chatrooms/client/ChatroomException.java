package com.commsen.sample.portlet.chatrooms.client;

public class ChatroomException extends Exception {

	
	public ChatroomException() {
		super();
	}

	public ChatroomException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ChatroomException(String message) {
		super(message);
	}

	public ChatroomException(Throwable throwable) {
		super(throwable);
	}

}
