<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>


<liferay-theme:defineObjects />

<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
<noscript>
  <div style="color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
    Your web browser must have JavaScript enabled in order for this portlet to display correctly.
  </div>
</noscript>

<%
String portletId = portletDisplay.getId();
%>

<script type="text/javascript" language="javascript" >
	chatroomPortletInstances[chatroomPortletInstances.length] = "<%=portletId %>";
</script>

<div id="chatrooms-portlet-<%=portletId %>"></div>