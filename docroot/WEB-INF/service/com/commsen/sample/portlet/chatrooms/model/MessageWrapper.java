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

/**
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author    Milen Dyankov
 * @see       Message
 * @generated
 */
public class MessageWrapper implements Message {
	public MessageWrapper(Message message) {
		_message = message;
	}

	public long getPrimaryKey() {
		return _message.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_message.setPrimaryKey(pk);
	}

	public long getId() {
		return _message.getId();
	}

	public void setId(long id) {
		_message.setId(id);
	}

	public java.util.Date getTime() {
		return _message.getTime();
	}

	public void setTime(java.util.Date time) {
		_message.setTime(time);
	}

	public java.lang.String getChatroom() {
		return _message.getChatroom();
	}

	public void setChatroom(java.lang.String chatroom) {
		_message.setChatroom(chatroom);
	}

	public java.lang.String getUser() {
		return _message.getUser();
	}

	public void setUser(java.lang.String user) {
		_message.setUser(user);
	}

	public java.lang.String getMessage() {
		return _message.getMessage();
	}

	public void setMessage(java.lang.String message) {
		_message.setMessage(message);
	}

	public com.commsen.sample.portlet.chatrooms.model.Message toEscapedModel() {
		return _message.toEscapedModel();
	}

	public boolean isNew() {
		return _message.isNew();
	}

	public void setNew(boolean n) {
		_message.setNew(n);
	}

	public boolean isCachedModel() {
		return _message.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_message.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _message.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_message.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _message.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _message.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_message.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _message.clone();
	}

	public int compareTo(
		com.commsen.sample.portlet.chatrooms.model.Message message) {
		return _message.compareTo(message);
	}

	public int hashCode() {
		return _message.hashCode();
	}

	public java.lang.String toString() {
		return _message.toString();
	}

	public java.lang.String toXmlString() {
		return _message.toXmlString();
	}

	public Message getWrappedMessage() {
		return _message;
	}

	private Message _message;
}