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

import com.commsen.sample.portlet.chatrooms.NoSuchMessageException;
import com.commsen.sample.portlet.chatrooms.model.Message;
import com.commsen.sample.portlet.chatrooms.model.impl.MessageImpl;
import com.commsen.sample.portlet.chatrooms.model.impl.MessageModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the message service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link MessageUtil} to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Milen Dyankov
 * @see MessagePersistence
 * @see MessageUtil
 * @generated
 */
public class MessagePersistenceImpl extends BasePersistenceImpl<Message>
	implements MessagePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = MessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_CHATROOM = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByChatroom",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CHATROOM = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByChatroom", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the message in the entity cache if it is enabled.
	 *
	 * @param message the message to cache
	 */
	public void cacheResult(Message message) {
		EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey(), message);
	}

	/**
	 * Caches the messages in the entity cache if it is enabled.
	 *
	 * @param messages the messages to cache
	 */
	public void cacheResult(List<Message> messages) {
		for (Message message : messages) {
			if (EntityCacheUtil.getResult(
						MessageModelImpl.ENTITY_CACHE_ENABLED,
						MessageImpl.class, message.getPrimaryKey(), this) == null) {
				cacheResult(message);
			}
		}
	}

	/**
	 * Clears the cache for all messages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(MessageImpl.class.getName());
		EntityCacheUtil.clearCache(MessageImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the message.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Message message) {
		EntityCacheUtil.removeResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey());
	}

	/**
	 * Creates a new message with the primary key. Does not add the message to the database.
	 *
	 * @param id the primary key for the new message
	 * @return the new message
	 */
	public Message create(long id) {
		Message message = new MessageImpl();

		message.setNew(true);
		message.setPrimaryKey(id);

		return message;
	}

	/**
	 * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the message to remove
	 * @return the message that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the message to remove
	 * @return the message that was removed
	 * @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message remove(long id)
		throws NoSuchMessageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Message message = (Message)session.get(MessageImpl.class,
					new Long(id));

			if (message == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
				}

				throw new NoSuchMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					id);
			}

			return remove(message);
		}
		catch (NoSuchMessageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message removeImpl(Message message) throws SystemException {
		message = toUnwrappedModel(message);

		Session session = null;

		try {
			session = openSession();

			if (message.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MessageImpl.class,
						message.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(message);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey());

		return message;
	}

	public Message updateImpl(
		com.commsen.sample.portlet.chatrooms.model.Message message,
		boolean merge) throws SystemException {
		message = toUnwrappedModel(message);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, message, merge);

			message.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey(), message);

		return message;
	}

	protected Message toUnwrappedModel(Message message) {
		if (message instanceof MessageImpl) {
			return message;
		}

		MessageImpl messageImpl = new MessageImpl();

		messageImpl.setNew(message.isNew());
		messageImpl.setPrimaryKey(message.getPrimaryKey());

		messageImpl.setId(message.getId());
		messageImpl.setTime(message.getTime());
		messageImpl.setChatroom(message.getChatroom());
		messageImpl.setUser(message.getUser());
		messageImpl.setMessage(message.getMessage());

		return messageImpl;
	}

	/**
	 * Finds the message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the message to find
	 * @return the message
	 * @throws com.liferay.portal.NoSuchModelException if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the message with the primary key or throws a {@link com.commsen.sample.portlet.chatrooms.NoSuchMessageException} if it could not be found.
	 *
	 * @param id the primary key of the message to find
	 * @return the message
	 * @throws com.commsen.sample.portlet.chatrooms.NoSuchMessageException if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message findByPrimaryKey(long id)
		throws NoSuchMessageException, SystemException {
		Message message = fetchByPrimaryKey(id);

		if (message == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return message;
	}

	/**
	 * Finds the message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the message to find
	 * @return the message, or <code>null</code> if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the message to find
	 * @return the message, or <code>null</code> if a message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Message fetchByPrimaryKey(long id) throws SystemException {
		Message message = (Message)EntityCacheUtil.getResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
				MessageImpl.class, id, this);

		if (message == null) {
			Session session = null;

			try {
				session = openSession();

				message = (Message)session.get(MessageImpl.class, new Long(id));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (message != null) {
					cacheResult(message);
				}

				closeSession(session);
			}
		}

		return message;
	}

	/**
	 * Finds all the messages where chatroom = &#63;.
	 *
	 * @param chatroom the chatroom to search with
	 * @return the matching messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<Message> findByChatroom(String chatroom)
		throws SystemException {
		return findByChatroom(chatroom, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<Message> findByChatroom(String chatroom, int start, int end)
		throws SystemException {
		return findByChatroom(chatroom, start, end, null);
	}

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
	public List<Message> findByChatroom(String chatroom, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				chatroom,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Message> list = (List<Message>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CHATROOM,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_MESSAGE_WHERE);

				if (chatroom == null) {
					query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_1);
				}
				else {
					if (chatroom.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_3);
					}
					else {
						query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_2);
					}
				}

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(MessageModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (chatroom != null) {
					qPos.add(chatroom);
				}

				list = (List<Message>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Message>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CHATROOM,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

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
	public Message findByChatroom_First(String chatroom,
		OrderByComparator orderByComparator)
		throws NoSuchMessageException, SystemException {
		List<Message> list = findByChatroom(chatroom, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("chatroom=");
			msg.append(chatroom);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMessageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public Message findByChatroom_Last(String chatroom,
		OrderByComparator orderByComparator)
		throws NoSuchMessageException, SystemException {
		int count = countByChatroom(chatroom);

		List<Message> list = findByChatroom(chatroom, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("chatroom=");
			msg.append(chatroom);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMessageException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public Message[] findByChatroom_PrevAndNext(long id, String chatroom,
		OrderByComparator orderByComparator)
		throws NoSuchMessageException, SystemException {
		Message message = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Message[] array = new MessageImpl[3];

			array[0] = getByChatroom_PrevAndNext(session, message, chatroom,
					orderByComparator, true);

			array[1] = message;

			array[2] = getByChatroom_PrevAndNext(session, message, chatroom,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Message getByChatroom_PrevAndNext(Session session,
		Message message, String chatroom, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MESSAGE_WHERE);

		if (chatroom == null) {
			query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_1);
		}
		else {
			if (chatroom.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_3);
			}
			else {
				query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (chatroom != null) {
			qPos.add(chatroom);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(message);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Message> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the messages.
	 *
	 * @return the messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<Message> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	public List<Message> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<Message> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Message> list = (List<Message>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_MESSAGE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_MESSAGE.concat(MessageModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Message>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Message>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Message>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the messages where chatroom = &#63; from the database.
	 *
	 * @param chatroom the chatroom to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByChatroom(String chatroom) throws SystemException {
		for (Message message : findByChatroom(chatroom)) {
			remove(message);
		}
	}

	/**
	 * Removes all the messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Message message : findAll()) {
			remove(message);
		}
	}

	/**
	 * Counts all the messages where chatroom = &#63;.
	 *
	 * @param chatroom the chatroom to search with
	 * @return the number of matching messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByChatroom(String chatroom) throws SystemException {
		Object[] finderArgs = new Object[] { chatroom };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CHATROOM,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_MESSAGE_WHERE);

				if (chatroom == null) {
					query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_1);
				}
				else {
					if (chatroom.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_3);
					}
					else {
						query.append(_FINDER_COLUMN_CHATROOM_CHATROOM_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (chatroom != null) {
					qPos.add(chatroom);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CHATROOM,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the messages.
	 *
	 * @return the number of messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MESSAGE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the message persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.commsen.sample.portlet.chatrooms.model.Message")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Message>> listenersList = new ArrayList<ModelListener<Message>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Message>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = MessagePersistence.class)
	protected MessagePersistence messagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MESSAGE = "SELECT message FROM Message message";
	private static final String _SQL_SELECT_MESSAGE_WHERE = "SELECT message FROM Message message WHERE ";
	private static final String _SQL_COUNT_MESSAGE = "SELECT COUNT(message) FROM Message message";
	private static final String _SQL_COUNT_MESSAGE_WHERE = "SELECT COUNT(message) FROM Message message WHERE ";
	private static final String _FINDER_COLUMN_CHATROOM_CHATROOM_1 = "message.chatroom IS NULL";
	private static final String _FINDER_COLUMN_CHATROOM_CHATROOM_2 = "message.chatroom = ?";
	private static final String _FINDER_COLUMN_CHATROOM_CHATROOM_3 = "(message.chatroom IS NULL OR message.chatroom = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "message.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Message exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Message exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(MessagePersistenceImpl.class);
}