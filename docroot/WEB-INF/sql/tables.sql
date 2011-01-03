create table Chatrooms_Message (
	id_ LONG not null primary key,
	time DATE null,
	chatroom VARCHAR(75) null,
	user VARCHAR(75) null,
	message VARCHAR(1000) null
);