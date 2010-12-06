package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Chatroom {

	private String portletInstanceId = "";
	private String currentRoom = null;

	public Chatroom(String instanceId) {
		this.portletInstanceId = instanceId;

		FlowPanel roomSelectionPanel = new FlowPanel();
		RootPanel.get("chatrooms-portlet-" + portletInstanceId).add(roomSelectionPanel);

		final TextBox roomNameBox = new TextBox();
		final Button enterTheRoomButton = new Button("enter the room");
		enterTheRoomButton.setEnabled(false);
		final Button leaveTheRoomButton = new Button("leave the room");
		leaveTheRoomButton.setEnabled(false);

		roomSelectionPanel.add(roomNameBox);
		roomSelectionPanel.add(enterTheRoomButton);
		roomSelectionPanel.add(leaveTheRoomButton);

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
				currentRoom = roomNameBox.getValue();
				leaveTheRoomButton.setEnabled(true);
				enterTheRoomButton.setEnabled(false);
				roomNameBox.setEnabled(false);
			}
		});

		leaveTheRoomButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				currentRoom = null;
				roomNameBox.setEnabled(true);
				leaveTheRoomButton.setEnabled(false);
				enterTheRoomButton.setEnabled(true);
			}
		});
	}
}
