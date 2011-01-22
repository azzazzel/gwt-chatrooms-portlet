package com.commsen.sample.portlet.chatrooms.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Chatroom {

	private String portletInstanceId = "";
	private String currentRoom = null;
	private Date lastMessageTime = null;
	private final ChatroomServiceAsync chatroomService = GWT.create(ChatroomService.class);

	private final TextBox roomNameBox = new TextBox();
	private final Button enterTheRoomButton = new Button("enter the room");
	private final Button leaveTheRoomButton = new Button("leave the room");

	private final VerticalPanel messageArea = new VerticalPanel();

	private final TextBox messageBox = new TextBox();
	private final Button sendMessageButton = new Button("send");

	private Timer messageUpdater;
	private boolean updateInProgress = false;
	
	
	public Chatroom(String instanceId) {
	
		/*
		 * Remember the portlet instance
		 */
		this.portletInstanceId = instanceId;

		/*
		 * Change the URL of chatroomSevice servlet
		 */
		((ServiceDefTarget) chatroomService).setServiceEntryPoint("/delegate/chatroomService");
		
		/*
		 * Prepare GUI
		 */
		drawGUI();
		prepareHandlers();
		displayInitialState();
		
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
		
		RootPanel.get("chatrooms-portlet-" + portletInstanceId).add(mainPanel);
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
				displayChatroomState(roomNameBox.getValue());
			}
		});

		leaveTheRoomButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				displayInitialState();
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
		roomNameBox.setValue(currentRoom);
		currentRoom = null;
		roomNameBox.setEnabled(true);
		messageArea.clear();
		lastMessageTime = null;
		messageBox.setEnabled(false);
		sendMessageButton.setEnabled(false);
	}

	private void sendMessageToServer (String message) {
		chatroomService.sendMessage(currentRoom, message, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String message) {
				// do nothing
			}
			
			@Override
			public void onFailure(Throwable paramThrowable) {
				Window.alert("Error: " + paramThrowable.getMessage());
			}
		});
	}
	
	private void getMessages() {
		chatroomService.getMessages(currentRoom, lastMessageTime, new AsyncCallback<List<MessageDTO>>() {
			@Override
			public void onSuccess(List<MessageDTO> messages) {
				updateInProgress = true;
				if (messages != null) {
					for (MessageDTO messageDTO : messages) {
						messageArea.add(new MessagePanel(messageDTO));
						lastMessageTime = messageDTO.getTime();
					}
				}
				updateInProgress = false;
			}
			
			@Override
			public void onFailure(Throwable paramThrowable) {
				Window.alert("Error: " + paramThrowable.getMessage());
			}
		});
	}

	
	/**
	 * This class represents a single message in message area
	 * 
	 * @author Milen Daynkov
	 *
	 */
	private static class MessagePanel extends HorizontalPanel {
		private static final DateTimeFormat TIME_FORMAT = DateTimeFormat.getFormat("dd-MM-yy HH:mm:ss");

		public MessagePanel(MessageDTO messageDTO) {
			super();
			this.addStyleName("message-entry");
			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.addStyleName("message-entry-author");
			verticalPanel.setWidth("150px");
			verticalPanel.add(new Label(messageDTO.getUser()));
			verticalPanel.add(new Label(TIME_FORMAT.format(messageDTO.getTime())));

			add(verticalPanel);
			
			Label text = new Label(messageDTO.getMessage());
			text.setWidth("100%");
			text.addStyleName("message-entry-text");
			add(text);
		}
	}

}
