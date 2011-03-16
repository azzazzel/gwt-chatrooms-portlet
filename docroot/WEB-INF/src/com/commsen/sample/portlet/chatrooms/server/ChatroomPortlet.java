package com.commsen.sample.portlet.chatrooms.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;

import com.commsen.sample.portlet.chatrooms.model.Message;
import com.commsen.sample.portlet.chatrooms.service.MessageLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;


public class ChatroomPortlet extends MVCPortlet {
	private static final Logger logger = Logger.getLogger(ChatroomPortlet.class.getName());

	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		logger.info("got resource request!");
		
		String jsonString = resourceRequest.getReader().readLine();
		JSONObject jsonObject = null;
		try {
	        jsonObject = JSONFactoryUtil.createJSONObject(jsonString);
        } catch (JSONException e) {
	        logger.error("Failed to parse JSON request", e);
	        return;
        }
        
        
        if ("getMessages".equals(jsonObject.getString("action"))) {
        	logger.info("It's getMessages request !!!");
        	String room = jsonObject.getString("currentRoom");
        	long timeAsLong = jsonObject.getLong("lastMessageTime");
        	Date time = new Date(timeAsLong);
    		try {
    			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Message.class);
    			dynamicQuery.add(PropertyFactoryUtil.forName("chatroom").eq(room));
    			if (time != null) {
    				dynamicQuery.add(PropertyFactoryUtil.forName("time").gt(time));
    			}
    			dynamicQuery.addOrder(OrderFactoryUtil.asc("time"));
    			@SuppressWarnings("unchecked")
                List<Message> messages = MessageLocalServiceUtil.dynamicQuery(dynamicQuery);
    			if (messages == null || messages.isEmpty()) return;
    			JSONArray result = JSONFactoryUtil.createJSONArray();
    			for (Message message : messages) {
    				JSONObject jsonMessage = JSONFactoryUtil.createJSONObject();
    				jsonMessage.put("id", message.getId());
    				jsonMessage.put("user", message.getUser());
    				jsonMessage.put("time", message.getTime().getTime());
    				jsonMessage.put("message", message.getMessage());
    				result.put(jsonMessage);
    			}
    			resourceResponse.getWriter().print(result.toString());
    		} catch (SystemException e) {
    			logger.error(e);
    		} catch (Throwable e) { 
    			logger.error(e);
    		}
        	
        }

        if ("saveMessage".equals(jsonObject.getString("action"))) {
        	logger.info("It's saveMessage request !!!");
    		User user = null;
    		try {
    			user = PortalUtil.getUser(resourceRequest);
    		} catch (PortalException e) {
    			logger.error(e);
    			return;
    		} catch (SystemException e) {
    			logger.error(e);
    			return;
    		}

    		String userName = (user == null) ? "Unknown" : user.getFullName(); 
        	String room = jsonObject.getString("currentRoom");
        	String messageText = jsonObject.getString("message");
    		
    		try {
    			Message message = MessageLocalServiceUtil.createMessage(CounterLocalServiceUtil.increment(Message.class.getName()));
    			message.setChatroom(room);
    			message.setMessage(messageText);
    			message.setTime(new Date());
    			message.setUser(userName);
    			MessageLocalServiceUtil.addMessage(message);
    		} catch (SystemException e) {
    			logger.error(e);
    		}
        	
        }
	}
}
