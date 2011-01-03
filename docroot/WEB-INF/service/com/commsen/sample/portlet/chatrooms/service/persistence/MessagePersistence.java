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

package com.commsen.sample.portlet.chatrooms.service.persistence;

import com.commsen.sample.portlet.chatrooms.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the message service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link MessageUtil} to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Milen Dyankov
 * @see MessagePersistenceImpl
 * @see MessageUtil
 * @generated
 */
public interface MessagePersistence extends BasePersistence<Message> {
	/**
	* Caches the message in the entity cache if it is enabled.
	*
	* @param message the message to cache
	*/
	public void cacheResult(
		com.commsen.sample.portlet.chatrooms.model.Message message);

	/**
	* Caches the messages in the entity cache if it is enabled.
	*
	* @param messages the messages to cache
	*/
	public void cacheResult(
		java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> messages);

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param id the primary key for the new message
	* @return the new message
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message create(long id);

	/**
	* Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the message to remove
	* @return the message that was removed
	* @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message remove(long id)
		throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.commsen.sample.portlet.chatrooms.model.Message updateImpl(
		com.commsen.sample.portlet.chatrooms.model.Message message,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message with the primary key or throws a {@link com.commsen.sample.portlet.chatrooms.NoSuchMessageException} if it could not be found.
	*
	* @param id the primary key of the message to find
	* @return the message
	* @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message findByPrimaryKey(
		long id)
		throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the message to find
	* @return the message, or <code>null</code> if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the messages where chatroom = &#63;.
	*
	* @param chatroom the chatroom to search with
	* @return the matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findByChatroom(
		java.lang.String chatroom)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the messages where chatroom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param chatroom the chatroom to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @return the range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findByChatroom(
		java.lang.String chatroom, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the messages where chatroom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param chatroom the chatroom to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findByChatroom(
		java.lang.String chatroom, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first message in the ordered set where chatroom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param chatroom the chatroom to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching message
	* @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message findByChatroom_First(
		java.lang.String chatroom,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last message in the ordered set where chatroom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param chatroom the chatroom to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching message
	* @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message findByChatroom_Last(
		java.lang.String chatroom,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the messages before and after the current message in the ordered set where chatroom = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current message
	* @param chatroom the chatroom to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next message
	* @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.commsen.sample.portlet.chatrooms.model.Message[] findByChatroom_PrevAndNext(
		long id, java.lang.String chatroom,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the messages.
	*
	* @return the messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @return the range of messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the messages where chatroom = &#63; from the database.
	*
	* @param chatroom the chatroom to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByChatroom(java.lang.String chatroom)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages where chatroom = &#63;.
	*
	* @param chatroom the chatroom to search with
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByChatroom(java.lang.String chatroom)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages.
	*
	* @return the number of messages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}