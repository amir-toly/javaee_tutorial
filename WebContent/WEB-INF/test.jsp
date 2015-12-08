<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Test</title>
	</head>
	
	<body>
		<p>This is a page generated from a JSP.</p>
		<p>
			<% String attribute = (String) request.getAttribute("test"); %>
			<%= attribute %>
			
			<% String parameter = request.getParameter("author"); %>
			<%= "URL request parameter from inside the JSP: " + parameter %>
		</p>
		<p>
			Get bean:
			<jsp:useBean id="coyote" class="com.sdzee.beans.Coyote" scope="request" />
			<jsp:getProperty name="coyote" property="firstName" />
			<jsp:getProperty name="coyote" property="lastName" />
		</p>
	</body>
</html>
