/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.commsen.sample.portlet.chatrooms.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Milen Dyankov
 */
public class MessageClp extends BaseModelImpl<Message> implements Message {
	public MessageClp() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_id);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public Date getTime() {
		return _time;
	}

	public void setTime(Date time) {
		_time = time;
	}

	public String getChatroom() {
		return _chatroom;
	}

	public void setChatroom(String chatroom) {
		_chatroom = chatroom;
	}

	public String getUser() {
		return _user;
	}

	public void setUser(String user) {
		_user = user;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public Message toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (Message)Proxy.newProxyInstance(Message.class.getClassLoader(),
				new Class[] { Message.class }, new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		MessageClp clone = new MessageClp();

		clone.setId(getId());
		clone.setTime(getTime());
		clone.setChatroom(getChatroom());
		clone.setUser(getUser());
		clone.setMessage(getMessage());

		return clone;
	}

	public int compareTo(Message message) {
		int value = 0;

		value = DateUtil.compareTo(getTime(), message.getTime());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MessageClp message = null;

		try {
			message = (MessageClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = message.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", time=");
		sb.append(getTime());
		sb.append(", chatroom=");
		sb.append(getChatroom());
		sb.append(", user=");
		sb.append(getUser());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.commsen.sample.portlet.chatrooms.model.Message");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>time</column-name><column-value><![CDATA[");
		sb.append(getTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>chatroom</column-name><column-value><![CDATA[");
		sb.append(getChatroom());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>user</column-name><column-value><![CDATA[");
		sb.append(getUser());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private Date _time;
	private String _chatroom;
	private String _user;
	private String _message;
}