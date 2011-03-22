package com.commsen.sample.portlet.chatrooms.client;

import java.util.Date;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Chatroom implements ValueChangeHandler<String> {

	public static final String STATE_DELIMITER = ":";
	public static final String STATE_INIT = "INIT";
	public static final String STATE_JOINED_GROUP = "GROUP";
	
	private ChatroomJsObject portletInstance = null;
	private String currentRoom = null;
	private long lastMessageTime = 0;

	private final TextBox roomNameBox = new TextBox();
	private final Button enterTheRoomButton = new Button("enter the room");
	private final Button leaveTheRoomButton = new Button("leave the room");

	private final VerticalPanel messageArea = new VerticalPanel();

	private final TextBox messageBox = new TextBox();
	private final Button sendMessageButton = new Button("send");

	private Timer messageUpdater;
	private boolean updateInProgress = false;
	
	private RequestBuilder requestBuilder = null;
	
	public Chatroom(ChatroomJsObject portletInstance) {
	
		/*
		 * Remember the portlet instance
		 */
		this.portletInstance = portletInstance;

		/*
		 * Prepare RequestBuilder
		 */
		requestBuilder = new RequestBuilder(RequestBuilder.POST, portletInstance.getResourceURL());

		/*
		 * Prepare GUI
		 */
		drawGUI();
		prepareHandlers();
		
		History.addValueChangeHandler(this);
 		
		String state = portletInstance.getState();
		if (state == null || state.isEmpty()) state = STATE_INIT;
		handleState(state);
		
		/*
		 * Configure the timer to retrieve messages every second
		 */
		messageUpdater = new Timer() {
			@Override
			public void run() {
				if (currentRoom != null && !updateInProgress) {
					getMessages();
				}
			}
		};
		messageUpdater.scheduleRepeating(1000);
	}
	
	
	/**
	 * Draws all GUI components
	 */
	private void drawGUI () {
		FlowPanel roomSelectionPanel = new FlowPanel();
		roomSelectionPanel.add(roomNameBox);
		roomSelectionPanel.add(enterTheRoomButton);
		roomSelectionPanel.add(leaveTheRoomButton);

		FlowPanel messagePanel = new FlowPanel();
		messagePanel.add(messageBox);
		messagePanel.add(sendMessageButton);

		ScrollPanel messageAreaScrollPanel = new ScrollPanel(messageArea);
		messageAreaScrollPanel.setHeight("200px");
		
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(roomSelectionPanel);
		mainPanel.add(messageAreaScrollPanel);
		mainPanel.add(messagePanel);
		
		RootPanel.get("chatrooms-portlet-" + portletInstance.getId()).add(mainPanel);
	}

	
	/**
	 * Prepare all event handlers 
	 */
	private void prepareHandlers() {
		roomNameBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent keyPressEvent) {
				if (roomNameBox.getValue().trim().isEmpty()) {
					enterTheRoomButton.setEnabled(false);
				} else {
					enterTheRoomButton.setEnabled(true);
				}
			}
		});

		enterTheRoomButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				String room = roomNameBox.getValue();
				displayChatroomState(room);
				saveState(STATE_JOINED_GROUP + STATE_DELIMITER + room);
			}
		});

		leaveTheRoomButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				displayInitialState();
				saveState(STATE_INIT);
			}
		});
		
		sendMessageButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				sendMessageToServer(messageBox.getValue());
				messageBox.setValue(null);
			}
		});
		
	}
	
	
	/**
	 * Set the properties of GUI components when user enters chatroom
	 */
	private void displayChatroomState (String room) {
		currentRoom = room;
		leaveTheRoomButton.setEnabled(true);
		enterTheRoomButton.setEnabled(false);
		roomNameBox.setEnabled(false);
		roomNameBox.setText(room);
		messageBox.setEnabled(true);
		sendMessageButton.setEnabled(true);
	}

	/**
	 * Set the properties of GUI components to their initial state.
	 */
	private void displayInitialState () {
		if (currentRoom == null) {
			enterTheRoomButton.setEnabled(false);
			leaveTheRoomButton.setEnabled(true);
		} else {
			enterTheRoomButton.setEnabled(true);
			leaveTheRoomButton.setEnabled(false);
		}
		roomNameBox.setText(currentRoom);
		roomNameBox.setEnabled(true);
		currentRoom = null;
		messageArea.clear();
		lastMessageTime = 0;
		messageBox.setEnabled(false);
		sendMessageButton.setEnabled(false);
	}

	private void sendMessageToServer (String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action", new JSONString("saveMessage"));
		jsonObject.put("currentRoom", new JSONString(currentRoom));
		jsonObject.put("message", new JSONString(message));
		try {
	        requestBuilder.sendRequest(jsonObject.toString(), new RequestCallback() {
	        	
	        	@Override
	        	public void onResponseReceived(Request request, Response response) {
	        		if (200 == response.getStatusCode()) {
	        			// do nothing
	        		} else {
	        			Window.alert("Error with JSON communication! Got response: " + response.getStatusCode() + " "
	        			        + response.getStatusText());
	        		}
	        	}
	        
	        	@Override
	        	public void onError(Request request, Throwable throwable) {
	        		Window.alert("Error: " + throwable.getMessage());
	        	}
	        });
        } catch (RequestException e) {
        	Window.alert("Error: " + e.getMessage());
        }
	}
	
	private void getMessages() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("action", new JSONString("getMessages"));
		jsonObject.put("currentRoom", new JSONString(currentRoom));
		jsonObject.put("lastMessageTime", new JSONNumber(lastMessageTime));

		try {
	        requestBuilder.sendRequest(jsonObject.toString(), new RequestCallback() {
	        	
	        	@Override
	        	public void onResponseReceived(Request request, Response response) {
	        		if (200 == response.getStatusCode()) {
	        			JsArray<ChatroomMessageJsObject> messages = json2ChatroomMessages(response.getText());
	        			updateInProgress = true;
	        			if (messages != null) {
	        				for (int i = 0; i < messages.length(); i++) {
	        					ChatroomMessageJsObject message = messages.get(i);
	        					messageArea.add(new MessagePanel(message));
	        					lastMessageTime = (long)message.getTime();
	        				}
	        			}
	        			updateInProgress = false;
	        		} else {
	        			Window.alert("Error with JSON communication! Got response: " + response.getStatusCode() + " "
	        			        + response.getStatusText());
	        		}
	        	}
	        	
	        
	        	@Override
	        	public void onError(Request request, Throwable throwable) {
	        		Window.alert("Error: " + throwable.getMessage());
	        	}
	        });
        } catch (RequestException e) {
    		Window.alert("Error: " + e.getMessage());
        }
	}

	public static final native JsArray<ChatroomMessageJsObject> json2ChatroomMessages(
			String json)
	/*-{ 
	  	return eval(json); 
	}-*/;
	
	public void saveState(String state) {
		portletInstance.setState(state);
		History.newItem(portletInstance.getId() + STATE_DELIMITER + state);
	}
		 
	@Override
	public void onValueChange(ValueChangeEvent<String> paramValueChangeEvent) {
		String token = paramValueChangeEvent.getValue();
		if (token == null || token.trim().isEmpty()) {
			handleState(STATE_INIT);
		} else {
			String state = getStateFromToken(paramValueChangeEvent.getValue());
			if (state != null) {
				handleState(state);
			}
		}
	}
	
	private String getStateFromToken(String token) {
		if (token == null)
			return null;
		int index = token.indexOf(STATE_DELIMITER);
		if (index > 0) {
			if (token.substring(0, index).equals(portletInstance.getId())) {
				return token.substring(index + 1, token.length());
			}
		}
		return null;
	}
	
	private void handleState(String state) {
		if (state.startsWith(STATE_JOINED_GROUP)) {
			int index = state.indexOf(STATE_DELIMITER);
			if (index > 0) {
				String room = state.substring(index + 1, state.length());
				displayChatroomState(room);
			}
		}
		if (state.equals(STATE_INIT)) {
			displayInitialState();
		}
	}
		 	
	
	/**
	 * This class represents a single message in message area
	 * 
	 * @author Milen Daynkov
	 *
	 */
	private static class MessagePanel extends HorizontalPanel {
		private static final DateTimeFormat TIME_FORMAT = DateTimeFormat.getFormat("dd-MM-yy HH:mm:ss");

		public MessagePanel(ChatroomMessageJsObject message) {
			super();
			this.addStyleName("message-entry");
			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.addStyleName("message-entry-author");
			verticalPanel.setWidth("150px");
			verticalPanel.add(new Label(message.getUser()));
			verticalPanel.add(new Label(TIME_FORMAT.format(new Date((long)message.getTime()))));

			add(verticalPanel);
			
			Label text = new Label(message.getMessage());
			text.setWidth("100%");
			text.addStyleName("message-entry-text");
			add(text);
		}
	}

}
