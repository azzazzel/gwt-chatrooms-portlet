package com.commsen.sample.portlet.chatrooms.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.commsen.sample.portlet.chatrooms.client.ChatroomException;
import com.commsen.sample.portlet.chatrooms.client.ChatroomService;
import com.commsen.sample.portlet.chatrooms.client.MessageDTO;
import com.commsen.sample.portlet.chatrooms.model.Message;
import com.commsen.sample.portlet.chatrooms.service.MessageLocalServiceUtil;
import com.commsen.sample.portlet.chatrooms.service.persistence.MessageUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class ChatroomServiceImpl extends RemoteServiceServlet implements
		ChatroomService {

	private static final Logger log = Logger.getLogger(ChatroomServiceImpl.class);
	
	@Override
	public void sendMessage(String room, String messageText) throws ChatroomException {
		User user = null;
		try {
			user = PortalUtil.getUser(getThreadLocalRequest());
		} catch (PortalException e) {
			log.error(e);
			throw new ChatroomException("Failed to save message");
		} catch (SystemException e) {
			log.error(e);
			throw new ChatroomException("Failed to save message");
		}

		String userName = (user == null) ? "Unknown" : user.getFullName(); 
		
		try {
			Message message = MessageLocalServiceUtil.createMessage(CounterLocalServiceUtil.increment(Message.class.getName()));
			message.setChatroom(room);
			message.setMessage(messageText);
			message.setTime(new Date());
			message.setUser(userName);
			MessageLocalServiceUtil.addMessage(message);
		} catch (SystemException e) {
			log.error(e);
			throw new ChatroomException("Failed to save message");
		}
	}

	@Override
	public List<MessageDTO> getMessages(String room, Date time)
			throws ChatroomException {
		try {
			System.out.println("getMessage called!!!");
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Message.class);
			dynamicQuery.add(PropertyFactoryUtil.forName("chatroom").eq(room));
			if (time != null) {
				dynamicQuery.add(PropertyFactoryUtil.forName("time").gt(time));
			}
			dynamicQuery.addOrder(OrderFactoryUtil.asc("time"));
			List<Message> messages = MessageLocalServiceUtil.dynamicQuery(dynamicQuery);
			if (messages == null || messages.isEmpty()) return null;
			List<MessageDTO> result = new ArrayList<MessageDTO>();
			for (Message message : messages) {
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setId(message.getId());
				messageDTO.setUser(message.getUser());
				messageDTO.setTime(message.getTime());
				messageDTO.setMessage(message.getMessage());
				result.add(messageDTO);
			}
			return result;
		} catch (SystemException e) {
			log.error(e);
			e.printStackTrace();
			throw new ChatroomException("Failed to load messages");
		} catch (Throwable e) { 
			e.printStackTrace();
			throw new ChatroomException("Failed to load messages");
		}
	}

}
