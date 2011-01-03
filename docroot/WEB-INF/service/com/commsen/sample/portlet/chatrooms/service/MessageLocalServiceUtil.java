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

package com.commsen.sample.portlet.chatrooms.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the message local service. This utility wraps {@link com.commsen.sample.portlet.chatrooms.service.impl.MessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.commsen.sample.portlet.chatrooms.service.impl.MessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Milen Dyankov
 * @see MessageLocalService
 * @see com.commsen.sample.portlet.chatrooms.service.base.MessageLocalServiceBaseImpl
 * @see com.commsen.sample.portlet.chatrooms.service.impl.MessageLocalServiceImpl
 * @generated
 */
public class MessageLocalServiceUtil {
	/**
	* Adds the message to the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to add
	* @return the message that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.commsen.sample.portlet.chatrooms.model.Message addMessage(
		com.commsen.sample.portlet.chatrooms.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addMessage(message);
	}

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param id the primary key for the new message
	* @return the new message
	*/
	public static com.commsen.sample.portlet.chatrooms.model.Message createMessage(
		long id) {
		return getService().createMessage(id);
	}

	/**
	* Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the message to delete
	* @throws PortalException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteMessage(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMessage(id);
	}

	/**
	* Deletes the message from the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteMessage(
		com.commsen.sample.portlet.chatrooms.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMessage(message);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the message with the primary key.
	*
	* @param id the primary key of the message to get
	* @return the message
	* @throws PortalException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.commsen.sample.portlet.chatrooms.model.Message getMessage(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessage(id);
	}

	/**
	* Gets a range of all the messages.
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
	public static java.util.List<com.commsen.sample.portlet.chatrooms.model.Message> getMessages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessages(start, end);
	}

	/**
	* Gets the number of messages.
	*
	* @return the number of messages
	* @throws SystemException if a system exception occurred
	*/
	public static int getMessagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessagesCount();
	}

	/**
	* Updates the message in the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to update
	* @return the message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.commsen.sample.portlet.chatrooms.model.Message updateMessage(
		com.commsen.sample.portlet.chatrooms.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMessage(message);
	}

	/**
	* Updates the message in the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to update
	* @param merge whether to merge the message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.commsen.sample.portlet.chatrooms.model.Message updateMessage(
		com.commsen.sample.portlet.chatrooms.model.Message message,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMessage(message, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static MessageLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					MessageLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new MessageLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MessageLocalService service) {
		_service = service;
	}

	private static MessageLocalService _service;
}