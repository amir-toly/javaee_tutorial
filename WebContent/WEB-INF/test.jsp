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
			<%
			String attribute = (String) request.getAttribute("test");
			out.println(attribute);
			
			String parameter = request.getParameter("author");
			out.println("URL request parameter from inside the JSP: " + parameter);
			%>
		</p>
		<p>
			Get bean:
			<%
			com.sdzee.beans.Coyote ourBean = (com.sdzee.beans.Coyote) request.getAttribute("coyote");
			out.println(ourBean.getFirstName());
			out.println(ourBean.getLastName());
			%>
		</p>
	</body>
</html>
