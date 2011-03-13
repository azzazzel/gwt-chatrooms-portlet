package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ChatroomJsObject extends JavaScriptObject {

	protected ChatroomJsObject() {
    }


    public final native String getId() /*-{ return this.id }-*/;


    public final native String getRenderURL() /*-{ return this.rederURL }-*/;


    public final native String getActionURL() /*-{ return this.actionURL }-*/;


    public final native String getResourceURL() /*-{ return this.resourceURL }-*/;

}
