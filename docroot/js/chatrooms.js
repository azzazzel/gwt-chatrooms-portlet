function ChatroomPortlet (id, rederURL, actionURL, resourceURL) {
        this.id = id;
        this.rederURL = rederURL;
        this.actionURL = actionURL;
        this.resourceURL = resourceURL;
        
        this.setState = function(state) {
        	if (sessvars.chatroom == undefined) {
        		sessvars.chatroom = {};
        	}
        	sessvars.chatroom[this.id] = state;
        };

        this.getState = function() {
        	if (sessvars.chatroom == undefined) {
        		return undefined;
        	}
        	return sessvars.chatroom[this.id];
        };
}

var chatroomPortletInstances = [];