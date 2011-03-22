package com.commsen.sample.portlet.chatrooms.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.History;

public class ChatroomJsObject extends JavaScriptObject {

	protected ChatroomJsObject() {
    }


    public final native String getId() /*-{ return this.id }-*/;


    public final native String getRenderURL() /*-{ return this.rederURL }-*/;


    public final native String getActionURL() /*-{ return this.actionURL }-*/;


    public final native String getResourceURL() /*-{ return this.resourceURL }-*/;


    public final native String getState() 
    /*-{ 
        return this.getState();
    }-*/;

    public final native void setState(String state) 
    /*-{ 
        this.setState(state)
    }-*/;
    
}
