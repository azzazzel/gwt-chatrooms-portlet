package com.commsen.sample.portlet.chatrooms.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageDTO implements IsSerializable {

	private String user;
	
	private String message;
	
	private Date time;
	
	private long id;

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
