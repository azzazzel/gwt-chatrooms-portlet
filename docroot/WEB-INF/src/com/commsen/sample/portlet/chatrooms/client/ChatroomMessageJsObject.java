package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ChatroomMessageJsObject extends JavaScriptObject {

	protected ChatroomMessageJsObject() {
    }


    public final native String getId() /*-{ return this.id}-*/;

    public final native String getUser() /*-{ return this.user }-*/;


    public final native String getMessage() /*-{ return this.message }-*/;


    public final native double getTime() /*-{ return this.time }-*/;


}
