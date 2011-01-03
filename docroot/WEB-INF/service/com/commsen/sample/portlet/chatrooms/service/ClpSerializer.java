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

import com.commsen.sample.portlet.chatrooms.model.MessageClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static final String SERVLET_CONTEXT_NAME = "gwt-chatrooms-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(MessageClp.class.getName())) {
			MessageClp oldCplModel = (MessageClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.commsen.sample.portlet.chatrooms.model.impl.MessageImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setTime",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getTime();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setChatroom",
							new Class[] { String.class });

					String value2 = oldCplModel.getChatroom();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUser",
							new Class[] { String.class });

					String value3 = oldCplModel.getUser();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setMessage",
							new Class[] { String.class });

					String value4 = oldCplModel.getMessage();

					method4.invoke(newModel, value4);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.commsen.sample.portlet.chatrooms.model.impl.MessageImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					MessageClp newModel = new MessageClp();

					Method method0 = oldModelClass.getMethod("getId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setId(value0);

					Method method1 = oldModelClass.getMethod("getTime");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setTime(value1);

					Method method2 = oldModelClass.getMethod("getChatroom");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setChatroom(value2);

					Method method3 = oldModelClass.getMethod("getUser");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUser(value3);

					Method method4 = oldModelClass.getMethod("getMessage");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setMessage(value4);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}